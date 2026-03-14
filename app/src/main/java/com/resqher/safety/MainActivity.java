package com.resqher.safety;

import android.content.Intent;
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
import com.resqher.safety.utils.PermissionManager;
import com.resqher.safety.utils.ShakeToSOSHelper;

public class MainActivity extends AppCompatActivity {

    private CardView sosCard, contactsCard, helplineCard, safetyTipsCard, legalRightsCard;
    private ShakeToSOSHelper shakeToSOSHelper;
    private SwitchCompat shakeSosToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shakeToSOSHelper = new ShakeToSOSHelper(this);
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
