package com.resqher.safety.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.resqher.safety.R;
import com.resqher.safety.database.AppDatabase;
import com.resqher.safety.models.EmergencyContact;
import com.resqher.safety.utils.LocationHelper;
import com.resqher.safety.utils.PermissionManager;
import com.resqher.safety.utils.SMSManager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SOSActivity extends AppCompatActivity {

    private Button sosButton, cancelButton, callPoliceButton, shareWhatsAppButton;
    private TextView countdownText, statusText;
    private CountDownTimer countDownTimer;
    private boolean isSOSActivated = false;
    private LocationHelper locationHelper;
    private Vibrator vibrator;
    private ExecutorService executorService;
    private String currentLocation = "";

    private static final String POLICE_NUMBER = "100"; // Change based on region
    private static final int COUNTDOWN_SECONDS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        initializeViews();
        locationHelper = new LocationHelper(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        executorService = Executors.newSingleThreadExecutor();

        setupClickListeners();
    }

    private void initializeViews() {
        sosButton = findViewById(R.id.sosButton);
        cancelButton = findViewById(R.id.cancelButton);
        callPoliceButton = findViewById(R.id.callPoliceButton);
        shareWhatsAppButton = findViewById(R.id.shareWhatsAppButton);
        countdownText = findViewById(R.id.countdownText);
        statusText = findViewById(R.id.statusText);

        cancelButton.setVisibility(View.GONE);
        shareWhatsAppButton.setVisibility(View.GONE);
    }

    private void setupClickListeners() {
        sosButton.setOnClickListener(v -> startSOSCountdown());
        cancelButton.setOnClickListener(v -> cancelSOS());
        callPoliceButton.setOnClickListener(v -> callPolice());
        shareWhatsAppButton.setOnClickListener(v -> shareOnWhatsApp());
    }

    private void startSOSCountdown() {
        if (isSOSActivated) return;

        isSOSActivated = true;
        sosButton.setEnabled(false);
        cancelButton.setVisibility(View.VISIBLE);
        statusText.setText("SOS will be activated in...");

        // Vibrate to alert user
        if (vibrator != null) {
            vibrator.vibrate(500);
        }

        countDownTimer = new CountDownTimer(COUNTDOWN_SECONDS * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                countdownText.setText(String.valueOf(seconds));
                countdownText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                countdownText.setVisibility(View.GONE);
                activateSOS();
            }
        }.start();
    }

    private void cancelSOS() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        resetUI();
        Toast.makeText(this, "SOS Cancelled", Toast.LENGTH_SHORT).show();
    }

    private void activateSOS() {
        statusText.setText("Activating SOS...");

        // Get current location
        locationHelper.getCurrentLocation(new LocationHelper.LocationCallback() {
            @Override
            public void onLocationReceived(String location, double latitude, double longitude) {
                currentLocation = location; // Store location for WhatsApp sharing
                sendEmergencyAlerts(location);
            }

            @Override
            public void onLocationError(String error) {
                currentLocation = "Location unavailable";
                sendEmergencyAlerts("Location unavailable");
                Toast.makeText(SOSActivity.this, "Could not get location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendEmergencyAlerts(String location) {
        executorService.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            List<EmergencyContact> contacts = db.emergencyContactDao().getPrimaryContacts();

            runOnUiThread(() -> {
                if (contacts.isEmpty()) {
                    statusText.setText("No emergency contacts found!");
                    Toast.makeText(this, "Please add emergency contacts first",
                            Toast.LENGTH_LONG).show();
                    resetUI();
                    return;
                }

                // Send SMS to all emergency contacts
                String message = "EMERGENCY ALERT! I need immediate help! This is an automated message from ResQHer app.";
                SMSManager.sendEmergencySMS(this, contacts, location, message);

                // Vibrate continuously
                if (vibrator != null) {
                    long[] pattern = {0, 1000, 500, 1000, 500};
                    vibrator.vibrate(pattern, -1);
                }

                statusText.setText("Emergency alerts sent!\nContacts notified with your location.");
                Toast.makeText(this, "Emergency alerts sent to " + contacts.size() + " contacts",
                        Toast.LENGTH_LONG).show();

                // Show WhatsApp button
                shareWhatsAppButton.setVisibility(View.VISIBLE);

                // Reset after some time
                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {}

                    @Override
                    public void onFinish() {
                        resetUI();
                    }
                }.start();
            });
        });
    }

    private void callPolice() {
        if (!PermissionManager.hasCallPermission(this)) {
            Toast.makeText(this, "Call permission required", Toast.LENGTH_SHORT).show();
            PermissionManager.requestPermissions(this);
            return;
        }

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + POLICE_NUMBER));
        startActivity(callIntent);
    }

    private void shareOnWhatsApp() {
        String message = "🚨 EMERGENCY ALERT! 🚨\n\n" +
                "I need immediate help!\n\n" +
                "My current location:\n" + currentLocation + "\n\n" +
                "This is an automated emergency message from ResQHer safety app.";

        // Get all primary contacts for WhatsApp sharing
        executorService.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            List<EmergencyContact> contacts = db.emergencyContactDao().getPrimaryContacts();

            runOnUiThread(() -> {
                if (contacts.isEmpty()) {
                    // No contacts - open WhatsApp to choose
                    openWhatsAppWithMessage(message, null);
                } else if (contacts.size() == 1) {
                    // One contact - send directly to them
                    String phone = contacts.get(0).getPhoneNumber();
                    openWhatsAppWithMessage(message, phone);
                } else {
                    // Multiple contacts - let user choose
                    showContactSelectionDialog(contacts, message);
                }
            });
        });
    }

    private void openWhatsAppWithMessage(String message, String phoneNumber) {
        try {
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, message);

            if (phoneNumber != null) {
                // Try to pre-select contact (requires phone number in international format)
                String cleanPhone = phoneNumber.replaceAll("[\\s\\-\\(\\)]", "");
                whatsappIntent.putExtra("jid", cleanPhone + "@s.whatsapp.net");
            }

            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showContactSelectionDialog(List<EmergencyContact> contacts, String message) {
        String[] contactNames = new String[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            contactNames[i] = contacts.get(i).getName() + " - " + contacts.get(i).getPhoneNumber();
        }

        new android.app.AlertDialog.Builder(this)
                .setTitle("Select Contact for WhatsApp")
                .setItems(contactNames, (dialog, which) -> {
                    String phone = contacts.get(which).getPhoneNumber();
                    openWhatsAppWithMessage(message, phone);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void resetUI() {
        isSOSActivated = false;
        sosButton.setEnabled(true);
        cancelButton.setVisibility(View.GONE);
        shareWhatsAppButton.setVisibility(View.GONE);
        countdownText.setVisibility(View.GONE);
        statusText.setText("Press the SOS button in case of emergency");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
