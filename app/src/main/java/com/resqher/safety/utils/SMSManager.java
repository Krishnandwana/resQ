package com.resqher.safety.utils;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import com.resqher.safety.models.EmergencyContact;

import java.util.ArrayList;
import java.util.List;

public class SMSManager {

    private static final String TAG = "SMSManager";

    public static void sendEmergencySMS(Context context, List<EmergencyContact> contacts,
                                       String location, String message) {
        if (!PermissionManager.hasSMSPermission(context)) {
            Log.e(TAG, "SMS permission not granted");
            return;
        }

        SmsManager smsManager = SmsManager.getDefault();
        String emergencyMessage = message + "\n\nMy current location: " + location;

        for (EmergencyContact contact : contacts) {
            try {
                ArrayList<String> messageParts = smsManager.divideMessage(emergencyMessage);
                if (messageParts.size() > 1) {
                    smsManager.sendMultipartTextMessage(
                            contact.getPhoneNumber(),
                            null,
                            messageParts,
                            null,
                            null
                    );
                } else {
                    smsManager.sendTextMessage(
                            contact.getPhoneNumber(),
                            null,
                            emergencyMessage,
                            null,
                            null
                    );
                }
                Log.d(TAG, "Emergency SMS sent to: " + contact.getName());
            } catch (Exception e) {
                Log.e(TAG, "Failed to send SMS to " + contact.getName(), e);
            }
        }
    }

    public static void sendQuickSOS(Context context, String phoneNumber, String location) {
        if (!PermissionManager.hasSMSPermission(context)) {
            Log.e(TAG, "SMS permission not granted");
            return;
        }

        try {
            SmsManager smsManager = SmsManager.getDefault();
            String message = "EMERGENCY! I need help immediately!\nLocation: " + location;
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Log.d(TAG, "Quick SOS sent to: " + phoneNumber);
        } catch (Exception e) {
            Log.e(TAG, "Failed to send quick SOS", e);
        }
    }
}
