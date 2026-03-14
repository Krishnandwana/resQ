package com.resqher.safety.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.widget.Toast;

import com.resqher.safety.activities.SOSActivity;

public class ShakeToSOSHelper implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_G = 2.7f;
    private static final long SHAKE_DEBOUNCE_MS = 400;
    private static final long SOS_TRIGGER_COOLDOWN_MS = 3500;
    private static final String PREFS_NAME = "resqher_settings";
    private static final String KEY_SHAKE_TO_SOS_ENABLED = "shake_to_sos_enabled";

    private static long lastSOSTriggerAt = 0L;

    private final Activity activity;
    private final SensorManager sensorManager;
    private final Sensor accelerometer;
    private long lastShakeAt = 0L;

    public ShakeToSOSHelper(Activity activity) {
        this.activity = activity;
        sensorManager = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);
        accelerometer = sensorManager != null
                ? sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                : null;
    }

    public void start() {
        if (!isEnabled(activity)) {
            stop();
            return;
        }

        if (sensorManager != null && accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void stop() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
            return;
        }

        if (!isEnabled(activity)) {
            return;
        }

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float gForce = (float) Math.sqrt(x * x + y * y + z * z) / SensorManager.GRAVITY_EARTH;

        long now = SystemClock.elapsedRealtime();
        if (gForce > SHAKE_THRESHOLD_G && now - lastShakeAt > SHAKE_DEBOUNCE_MS) {
            lastShakeAt = now;
            triggerSOSIfAllowed(now);
        }
    }

    private void triggerSOSIfAllowed(long now) {
        if (now - lastSOSTriggerAt < SOS_TRIGGER_COOLDOWN_MS) {
            return;
        }

        lastSOSTriggerAt = now;
        Toast.makeText(activity, "Shake detected. Starting SOS...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(activity, SOSActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    public static boolean isEnabled(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getBoolean(KEY_SHAKE_TO_SOS_ENABLED, true);
    }

    public static void setEnabled(Context context, boolean enabled) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_SHAKE_TO_SOS_ENABLED, enabled)
                .apply();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No-op
    }
}