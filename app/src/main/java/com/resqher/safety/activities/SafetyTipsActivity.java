package com.resqher.safety.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.adapters.SafetyTipsAdapter;
import com.resqher.safety.models.SafetyTipSection;
import com.resqher.safety.utils.ShakeToSOSHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafetyTipsActivity extends AppCompatActivity {

    private RecyclerView tipsRecyclerView;
    private ShakeToSOSHelper shakeToSOSHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        shakeToSOSHelper = new ShakeToSOSHelper(this);
        tipsRecyclerView = findViewById(R.id.tipsRecyclerView);

        setupTipsList();
    }

        private void setupTipsList() {
        tipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tipsRecyclerView.setAdapter(new SafetyTipsAdapter(getSafetyTips()));
        }

        private List<SafetyTipSection> getSafetyTips() {
        List<SafetyTipSection> sections = new ArrayList<>();

        sections.add(new SafetyTipSection(
            "Awareness First",
            "Daily Situational Awareness",
            Arrays.asList(
                "Keep your phone accessible, not buried in a bag.",
                "Avoid distractions in unfamiliar places, especially at night.",
                "Trust early warning signs and move to a safer, busier area."
            )
        ));

        sections.add(new SafetyTipSection(
            "Travel & Commute",
            "When Using Public or Private Transport",
            Arrays.asList(
                "Share trip details/live location with a trusted contact.",
                "Before boarding a cab, verify vehicle number and driver profile.",
                "Prefer well-lit pickup/drop points and sit where you can exit quickly."
            )
        ));

        sections.add(new SafetyTipSection(
            "Night Safety",
            "Going Out After Dark",
            Arrays.asList(
                "Stick to main roads and avoid isolated shortcuts.",
                "Keep at least 20% battery before leaving a place.",
                "If followed, enter a public shop/hotel and call someone immediately."
            )
        ));

        sections.add(new SafetyTipSection(
            "Digital Safety",
            "Online & Social Media",
            Arrays.asList(
                "Turn on 2-factor authentication for key accounts.",
                "Do not share real-time location publicly on social platforms.",
                "Block/report harassment and preserve screenshots as evidence."
            )
        ));

        sections.add(new SafetyTipSection(
            "Home & Building Entry",
            "At Your Doorstep",
            Arrays.asList(
                "Verify identity before opening the door to unknown visitors.",
                "Inform a trusted person when arriving home late.",
                "Use peephole/camera and keep emergency contacts on speed dial."
            )
        ));

        sections.add(new SafetyTipSection(
            "Workplace Safety",
            "Professional Environments",
            Arrays.asList(
                "Document inappropriate behavior with date, time, and details.",
                "Escalate concerns through internal committee/HR channels.",
                "Do not attend late one-on-one meetings in isolated locations."
            )
        ));

        sections.add(new SafetyTipSection(
            "Emergency Preparedness",
            "Before an Incident Happens",
            Arrays.asList(
                "Keep emergency contacts updated in the app.",
                "Practice SOS activation and know local helpline numbers.",
                "Enable shake-to-SOS only if it fits your routine and environment."
            )
        ));

        sections.add(new SafetyTipSection(
            "If You Feel Unsafe Now",
            "Immediate Actions",
            Arrays.asList(
                "Move toward crowds, security desks, or a known safe point.",
                "Call helplines and trigger SOS with precise location details.",
                "After reaching safety, inform trusted contacts and document incident details."
            )
        ));

        return sections;
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
