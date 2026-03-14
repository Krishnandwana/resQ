package com.resqher.safety.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.adapters.LegalRightsAdapter;
import com.resqher.safety.models.LegalRightsSection;
import com.resqher.safety.utils.ShakeToSOSHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegalRightsActivity extends AppCompatActivity {

    private RecyclerView legalRecyclerView;
    private ShakeToSOSHelper shakeToSOSHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_rights);

        shakeToSOSHelper = new ShakeToSOSHelper(this);
        legalRecyclerView = findViewById(R.id.legalRecyclerView);

        setupLegalRightsList();
    }

        private void setupLegalRightsList() {
        legalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        legalRecyclerView.setAdapter(new LegalRightsAdapter(getLegalRightsSections()));
        }

        private List<LegalRightsSection> getLegalRightsSections() {
        List<LegalRightsSection> sections = new ArrayList<>();

        sections.add(new LegalRightsSection(
            "Constitutional Protections",
            "Foundational Rights",
            Arrays.asList(
                "Article 14 guarantees equality before law.",
                "Article 15 prohibits discrimination on grounds of sex.",
                "Article 21 protects life and personal liberty with dignity."
            )
        ));

        sections.add(new LegalRightsSection(
            "Domestic Violence Protection",
            "Protection of Women from Domestic Violence Act, 2005",
            Arrays.asList(
                "Covers physical, emotional, verbal, sexual, and economic abuse.",
                "You can seek protection orders, residence rights, and monetary relief.",
                "You can approach Protection Officers, police, or legal aid services."
            )
        ));

        sections.add(new LegalRightsSection(
            "Workplace Harassment",
            "POSH Act, 2013",
            Arrays.asList(
                "Every eligible workplace must have an Internal Complaints Committee.",
                "You can file a complaint for sexual harassment at workplace.",
                "Employers are required to provide a safe and non-hostile environment."
            )
        ));

        sections.add(new LegalRightsSection(
            "Criminal Law Protections",
            "Relevant IPC Provisions",
            Arrays.asList(
                "Section 354: assault to outrage modesty.",
                "Sections 375/376: rape and punishment.",
                "Section 498A: cruelty by husband or relatives.",
                "Section 509: insulting modesty through words/gestures."
            )
        ));

        sections.add(new LegalRightsSection(
            "Emergency Filing Rights",
            "When Immediate Help Is Needed",
            Arrays.asList(
                "You can file a Zero FIR at any police station, regardless of jurisdiction.",
                "Police cannot lawfully refuse to register a cognizable complaint.",
                "You can request a woman officer and safer recording conditions where possible."
            )
        ));

        sections.add(new LegalRightsSection(
            "Legal Aid & Support",
            "Access to Justice",
            Arrays.asList(
                "Women are eligible for free legal aid through legal services authorities.",
                "Keep key numbers handy: Women Helpline 1091, Emergency 112/100.",
                "Preserve evidence (messages, screenshots, medical records) for legal action."
            )
        ));

        sections.add(new LegalRightsSection(
            "Important Note",
            "Use This Information Responsibly",
            Arrays.asList(
                "These are general awareness tips and not a substitute for legal advice.",
                "For case-specific guidance, contact a qualified lawyer or legal aid clinic."
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
