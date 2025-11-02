package com.resqher.safety.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.resqher.safety.R;

public class SafetyTipsActivity extends AppCompatActivity {

    private TextView tipsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        tipsContent = findViewById(R.id.tipsContent);
        loadSafetyTips();
    }

    private void loadSafetyTips() {
        StringBuilder tips = new StringBuilder();

        tips.append("GENERAL SAFETY TIPS\n\n");

        tips.append("1. STAY ALERT\n");
        tips.append("• Always be aware of your surroundings\n");
        tips.append("• Avoid using headphones while walking alone\n");
        tips.append("• Trust your instincts - if something feels wrong, leave\n\n");

        tips.append("2. TRAVEL SAFETY\n");
        tips.append("• Share your travel details with trusted contacts\n");
        tips.append("• Use verified transportation services\n");
        tips.append("• Sit near the driver in public transport\n");
        tips.append("• Keep emergency numbers on speed dial\n\n");

        tips.append("3. DIGITAL SAFETY\n");
        tips.append("• Don't share personal information on social media\n");
        tips.append("• Turn off location tracking when not needed\n");
        tips.append("• Be cautious of strangers online\n");
        tips.append("• Use strong passwords and two-factor authentication\n\n");

        tips.append("4. SELF-DEFENSE BASICS\n");
        tips.append("• Carry a personal safety alarm or whistle\n");
        tips.append("• Learn basic self-defense techniques\n");
        tips.append("• Target vulnerable areas: eyes, nose, throat, groin\n");
        tips.append("• Make noise to attract attention\n\n");

        tips.append("5. NIGHT SAFETY\n");
        tips.append("• Walk in well-lit areas\n");
        tips.append("• Keep your phone charged and accessible\n");
        tips.append("• Inform someone of your whereabouts\n");
        tips.append("• Avoid shortcuts through isolated areas\n\n");

        tips.append("6. PUBLIC SPACES\n");
        tips.append("• Keep valuables hidden\n");
        tips.append("• Be cautious of overly friendly strangers\n");
        tips.append("• Know the location of exits\n");
        tips.append("• Stay in groups when possible\n\n");

        tips.append("7. HOME SAFETY\n");
        tips.append("• Keep doors and windows locked\n");
        tips.append("• Verify identity before opening the door\n");
        tips.append("• Install proper lighting around entry points\n");
        tips.append("• Have emergency contacts readily available\n\n");

        tips.append("8. EMERGENCY PREPAREDNESS\n");
        tips.append("• Keep ResQHer app easily accessible\n");
        tips.append("• Memorize important emergency numbers\n");
        tips.append("• Practice using SOS features regularly\n");
        tips.append("• Keep your emergency contacts updated\n\n");

        tips.append("REMEMBER: Your safety is paramount. Don't hesitate to seek help!");

        tipsContent.setText(tips.toString());
    }
}
