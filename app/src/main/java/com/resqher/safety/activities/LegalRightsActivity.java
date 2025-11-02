package com.resqher.safety.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.resqher.safety.R;

public class LegalRightsActivity extends AppCompatActivity {

    private TextView legalContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_rights);

        legalContent = findViewById(R.id.legalContent);
        loadLegalRights();
    }

    private void loadLegalRights() {
        StringBuilder legal = new StringBuilder();

        legal.append("WOMEN'S LEGAL RIGHTS IN INDIA\n\n");

        legal.append("KEY LEGISLATION\n\n");

        legal.append("1. THE CONSTITUTION OF INDIA\n");
        legal.append("• Article 14: Right to Equality\n");
        legal.append("• Article 15: Prohibition of discrimination on grounds of sex\n");
        legal.append("• Article 21: Right to Life and Personal Liberty\n\n");

        legal.append("2. PROTECTION AGAINST VIOLENCE\n\n");

        legal.append("DOMESTIC VIOLENCE ACT, 2005\n");
        legal.append("• Protection from physical, sexual, verbal, emotional, and economic abuse\n");
        legal.append("• Right to residence and monetary relief\n");
        legal.append("• Protection orders and custody of children\n\n");

        legal.append("DOWRY PROHIBITION ACT, 1961\n");
        legal.append("• Demanding or giving dowry is illegal\n");
        legal.append("• Punishment: 5 years imprisonment and fine up to Rs. 15,000\n\n");

        legal.append("3. SEXUAL HARASSMENT & ASSAULT\n\n");

        legal.append("SEXUAL HARASSMENT AT WORKPLACE ACT, 2013\n");
        legal.append("• Every workplace must have Internal Complaints Committee\n");
        legal.append("• Employers must provide safe working environment\n");
        legal.append("• Complaint can be filed within 3 months\n\n");

        legal.append("SECTIONS UNDER IPC\n");
        legal.append("• Section 354: Assault with intent to outrage modesty\n");
        legal.append("• Section 375/376: Rape and punishment\n");
        legal.append("• Section 509: Word, gesture to insult modesty of woman\n");
        legal.append("• Section 498A: Cruelty by husband or relatives\n\n");

        legal.append("4. WORKPLACE RIGHTS\n");
        legal.append("• Equal pay for equal work\n");
        legal.append("• Maternity leave: 26 weeks (before 12 weeks, after 14 weeks)\n");
        legal.append("• No night shift without consent (6 PM to 6 AM)\n");
        legal.append("• Creche facilities in establishments with 50+ employees\n\n");

        legal.append("5. PROPERTY RIGHTS\n");
        legal.append("• Equal right to ancestral property\n");
        legal.append("• Right to inherit property\n");
        legal.append("• Right to own and dispose of property\n\n");

        legal.append("6. MARRIAGE & DIVORCE RIGHTS\n");
        legal.append("• Minimum age for marriage: 21 years (proposed)\n");
        legal.append("• Right to divorce under various personal laws\n");
        legal.append("• Right to maintenance and alimony\n");
        legal.append("• Child custody rights\n\n");

        legal.append("7. EMERGENCY MEASURES\n\n");

        legal.append("ZERO FIR\n");
        legal.append("• Can be filed at any police station regardless of jurisdiction\n");
        legal.append("• Police must register FIR and transfer to appropriate station\n\n");

        legal.append("RIGHT TO FREE LEGAL AID\n");
        legal.append("• Every woman has right to free legal services\n");
        legal.append("• Contact: National Legal Services Authority (NALSA)\n\n");

        legal.append("8. IMPORTANT HELPLINES\n");
        legal.append("• Women Helpline: 1091\n");
        legal.append("• National Commission for Women: 7827170170\n");
        legal.append("• Police: 100\n");
        legal.append("• Legal Aid: 15100\n\n");

        legal.append("REMEMBER:\n");
        legal.append("• You have the right to file a complaint\n");
        legal.append("• Police cannot refuse to file FIR\n");
        legal.append("• Medical examination is your right\n");
        legal.append("• Identity of victim must be kept confidential\n");
        legal.append("• Statement can be recorded at home/choice of place\n\n");

        legal.append("This app provides general information. For specific legal advice, consult a lawyer.");

        legalContent.setText(legal.toString());
    }
}
