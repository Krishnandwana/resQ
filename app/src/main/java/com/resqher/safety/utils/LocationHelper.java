package com.resqher.safety.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHelper {

    private static final String TAG = "LocationHelper";
    private FusedLocationProviderClient fusedLocationClient;
    private Context context;

    public LocationHelper(Context context) {
        this.context = context;
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public interface LocationCallback {
        void onLocationReceived(String location, double latitude, double longitude);
        void onLocationError(String error);
    }

    public void getCurrentLocation(LocationCallback callback) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            callback.onLocationError("Location permission not granted");
            return;
        }

        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CurrentLocationRequest locationRequest = new CurrentLocationRequest.Builder()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setMaxUpdateAgeMillis(10000)
                .setDurationMillis(15000)
                .build();

        fusedLocationClient.getCurrentLocation(locationRequest, cancellationTokenSource.getToken())
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        sendLocationToCallback(location, callback);
                    } else {
                        getLastKnownLocation(callback);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error getting current location", e);
                    getLastKnownLocation(callback);
                });
    }

    private void getLastKnownLocation(LocationCallback callback) {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        sendLocationToCallback(location, callback);
                    } else {
                        callback.onLocationError("Unable to get current location");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error getting last known location", e);
                    callback.onLocationError("Failed to get location: " + e.getMessage());
                });
    }

    private void sendLocationToCallback(Location location, LocationCallback callback) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String address = getAddressFromLocation(latitude, longitude);
        String locationString = address + "\n" +
                "https://maps.google.com/?q=" + latitude + "," + longitude;
        callback.onLocationReceived(locationString, latitude, longitude);
    }

    private String getAddressFromLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                StringBuilder addressString = new StringBuilder();

                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressString.append(address.getAddressLine(i));
                    if (i < address.getMaxAddressLineIndex()) {
                        addressString.append(", ");
                    }
                }
                return addressString.toString();
            }
        } catch (IOException e) {
            Log.e(TAG, "Geocoder error", e);
        }
        return "Lat: " + latitude + ", Lng: " + longitude;
    }

    public static String formatLocation(double latitude, double longitude) {
        return "https://maps.google.com/?q=" + latitude + "," + longitude;
    }
}
