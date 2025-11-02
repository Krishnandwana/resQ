package com.resqher.safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.resqher.safety.activities.EmergencyContactsActivity;
import com.resqher.safety.activities.HelplineActivity;
import com.resqher.safety.activities.LegalRightsActivity;
import com.resqher.safety.activities.SOSActivity;
import com.resqher.safety.activities.SafetyTipsActivity;
import com.resqher.safety.utils.PermissionManager;

public class MainActivity extends AppCompatActivity {

    private CardView sosCard, contactsCard, helplineCard, safetyTipsCard, legalRightsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        checkPermissions();
        setupClickListeners();
    }

    private void initializeViews() {
        sosCard = findViewById(R.id.sosCard);
        contactsCard = findViewById(R.id.contactsCard);
        helplineCard = findViewById(R.id.helplineCard);
        safetyTipsCard = findViewById(R.id.safetyTipsCard);
        legalRightsCard = findViewById(R.id.legalRightsCard);
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
}
