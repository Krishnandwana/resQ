package com.resqher.safety.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.adapters.HelplineAdapter;
import com.resqher.safety.models.Helpline;
import com.resqher.safety.utils.PermissionManager;

import java.util.ArrayList;
import java.util.List;

public class HelplineActivity extends AppCompatActivity {

    private RecyclerView helplineRecyclerView;
    private HelplineAdapter helplineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        initializeViews();
        setupRecyclerView();
        loadHelplineNumbers();
    }

    private void initializeViews() {
        helplineRecyclerView = findViewById(R.id.helplineRecyclerView);
    }

    private void setupRecyclerView() {
        helplineAdapter = new HelplineAdapter(new ArrayList<>(), this::onCallHelpline);
        helplineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        helplineRecyclerView.setAdapter(helplineAdapter);
    }

    private void loadHelplineNumbers() {
        List<Helpline> helplines = new ArrayList<>();

        // Emergency Services
        helplines.add(new Helpline("Police", "100",
                "Emergency police assistance", "Emergency"));
        helplines.add(new Helpline("Ambulance", "108",
                "Medical emergency services", "Emergency"));
        helplines.add(new Helpline("Fire Service", "101",
                "Fire and rescue services", "Emergency"));

        // Women Safety Helplines
        helplines.add(new Helpline("Women Helpline", "1091",
                "24x7 helpline for women in distress", "Women Safety"));
        helplines.add(new Helpline("National Commission for Women", "7827170170",
                "NCW helpline for women issues", "Women Safety"));
        helplines.add(new Helpline("Women in Distress", "181",
                "State helpline for women", "Women Safety"));

        // Child & Senior Helplines
        helplines.add(new Helpline("Child Helpline", "1098",
                "24x7 helpline for children in need", "Child Safety"));
        helplines.add(new Helpline("Senior Citizen Helpline", "14567",
                "Helpline for senior citizens", "Senior Safety"));

        // Mental Health
        helplines.add(new Helpline("Mental Health", "1800-599-0019",
                "Mental health support helpline", "Mental Health"));
        helplines.add(new Helpline("Vandrevala Foundation", "1860-2662-345",
                "24x7 mental health helpline", "Mental Health"));

        // Legal Aid
        helplines.add(new Helpline("Legal Aid", "15100",
                "Free legal aid services", "Legal"));
        helplines.add(new Helpline("Cybercrime", "1930",
                "National cybercrime helpline", "Legal"));

        helplineAdapter.updateHelplines(helplines);
    }

    private void onCallHelpline(Helpline helpline) {
        if (!PermissionManager.hasCallPermission(this)) {
            Toast.makeText(this, "Call permission required", Toast.LENGTH_SHORT).show();
            PermissionManager.requestPermissions(this);
            return;
        }

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + helpline.getNumber()));
        startActivity(callIntent);
    }
}
