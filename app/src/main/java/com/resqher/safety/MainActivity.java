package com.resqher.safety;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import com.resqher.safety.activities.EmergencyContactsActivity;
import com.resqher.safety.activities.HelplineActivity;
import com.resqher.safety.activities.LegalRightsActivity;
import com.resqher.safety.activities.SOSActivity;
import com.resqher.safety.activities.SafetyTipsActivity;
import com.resqher.safety.utils.LocationHelper;
import com.resqher.safety.utils.PermissionManager;
import com.resqher.safety.utils.ShakeToSOSHelper;

public class MainActivity extends AppCompatActivity {

    private CardView sosCard, contactsCard, helplineCard, safetyTipsCard, legalRightsCard;
    private CardView quickCallPoliceCard, quickShareLocationCard, quickAlertContactsCard;
    private ShakeToSOSHelper shakeToSOSHelper;
    private SwitchCompat shakeSosToggle;
    private LocationHelper locationHelper;

    private static final String POLICE_NUMBER = "100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shakeToSOSHelper = new ShakeToSOSHelper(this);
        locationHelper = new LocationHelper(this);
        initializeViews();
        checkPermissions();
        setupClickListeners();
        setupShakeToggle();
    }

    private void initializeViews() {
        sosCard = findViewById(R.id.sosCard);
        contactsCard = findViewById(R.id.contactsCard);
        helplineCard = findViewById(R.id.helplineCard);
        safetyTipsCard = findViewById(R.id.safetyTipsCard);
        legalRightsCard = findViewById(R.id.legalRightsCard);
        quickCallPoliceCard = findViewById(R.id.quickCallPoliceCard);
        quickShareLocationCard = findViewById(R.id.quickShareLocationCard);
        quickAlertContactsCard = findViewById(R.id.quickAlertContactsCard);
        shakeSosToggle = findViewById(R.id.shakeSosToggle);
    }

    private void setupShakeToggle() {
        if (shakeSosToggle == null) {
            return;
        }

        boolean isEnabled = ShakeToSOSHelper.isEnabled(this);
        shakeSosToggle.setChecked(isEnabled);

        shakeSosToggle.setOnCheckedChangeListener((buttonView, checked) -> {
            ShakeToSOSHelper.setEnabled(this, checked);
            if (checked) {
                shakeToSOSHelper.start();
                Toast.makeText(this, "Shake-to-SOS enabled", Toast.LENGTH_SHORT).show();
            } else {
                shakeToSOSHelper.stop();
                Toast.makeText(this, "Shake-to-SOS disabled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkPermissions() {
        if (!PermissionManager.checkPermissions(this)) {
            PermissionManager.requestPermissions(this);
        }
    }

    private void setupClickListeners() {
        sosCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SOSActivity.class);
            startActivity(intent);
        });

        contactsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EmergencyContactsActivity.class);
            startActivity(intent);
        });

        helplineCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelplineActivity.class);
            startActivity(intent);
        });

        safetyTipsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SafetyTipsActivity.class);
            startActivity(intent);
        });

        legalRightsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LegalRightsActivity.class);
            startActivity(intent);
        });

        quickCallPoliceCard.setOnClickListener(v -> callPolice());
        quickShareLocationCard.setOnClickListener(v -> shareCurrentLocation());
        quickAlertContactsCard.setOnClickListener(v -> openSOS());
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

    private void shareCurrentLocation() {
        if (!PermissionManager.hasLocationPermission(this)) {
            Toast.makeText(this, "Location permission required", Toast.LENGTH_SHORT).show();
            PermissionManager.requestPermissions(this);
            return;
        }

        Toast.makeText(this, "Fetching location...", Toast.LENGTH_SHORT).show();
        locationHelper.getCurrentLocation(new LocationHelper.LocationCallback() {
            @Override
            public void onLocationReceived(String location, double latitude, double longitude) {
                String shareMessage = "My current location:\n" + location +
                        "\n\nShared from ResQHer";

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                startActivity(Intent.createChooser(shareIntent, "Share location via"));
            }

            @Override
            public void onLocationError(String error) {
                Toast.makeText(MainActivity.this, "Unable to get location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openSOS() {
        Intent intent = new Intent(MainActivity.this, SOSActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionManager.PERMISSION_REQUEST_CODE) {
            if (PermissionManager.checkPermissions(this)) {
                Toast.makeText(this, "All permissions granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Some permissions are required for full functionality",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shakeToSOSHelper != null) {
            shakeToSOSHelper.start();
        }
    }

    @Override
    protected void onPause() {
        if (shakeToSOSHelper != null) {
            shakeToSOSHelper.stop();
        }
        super.onPause();
    }
}
