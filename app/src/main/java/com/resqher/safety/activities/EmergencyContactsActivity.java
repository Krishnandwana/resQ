package com.resqher.safety.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.resqher.safety.R;
import com.resqher.safety.adapters.ContactsAdapter;
import com.resqher.safety.database.AppDatabase;
import com.resqher.safety.models.EmergencyContact;
import com.resqher.safety.utils.ShakeToSOSHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmergencyContactsActivity extends AppCompatActivity {

    private RecyclerView contactsRecyclerView;
    private ContactsAdapter contactsAdapter;
    private FloatingActionButton fabAddContact;
    private AppDatabase database;
    private ExecutorService executorService;
    private ShakeToSOSHelper shakeToSOSHelper;

    private static final int PICK_CONTACT_REQUEST = 1001;
    private EditText currentNameEdit, currentPhoneEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        database = AppDatabase.getInstance(this);
        executorService = Executors.newSingleThreadExecutor();
        shakeToSOSHelper = new ShakeToSOSHelper(this);

        initializeViews();
        setupRecyclerView();
        loadContacts();
    }

    private void initializeViews() {
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView);
        fabAddContact = findViewById(R.id.fabAddContact);

        fabAddContact.setOnClickListener(v -> showAddContactDialog());
    }

    private void setupRecyclerView() {
        contactsAdapter = new ContactsAdapter(new ArrayList<>(), this::onDeleteContact, this::onEditContact);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsRecyclerView.setAdapter(contactsAdapter);
    }

    private void loadContacts() {
        database.emergencyContactDao().getAllContacts().observe(this, new Observer<List<EmergencyContact>>() {
            @Override
            public void onChanged(List<EmergencyContact> contacts) {
                contactsAdapter.updateContacts(contacts);
            }
        });
    }

    private void showAddContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_contact, null);

        Button pickContactBtn = dialogView.findViewById(R.id.btnPickContact);
        EditText nameEdit = dialogView.findViewById(R.id.editName);
        EditText phoneEdit = dialogView.findViewById(R.id.editPhone);
        EditText relationEdit = dialogView.findViewById(R.id.editRelation);
        CheckBox primaryCheck = dialogView.findViewById(R.id.checkPrimary);

        // Store references for use in onActivityResult
        currentNameEdit = nameEdit;
        currentPhoneEdit = phoneEdit;

        // Contact picker button
        pickContactBtn.setOnClickListener(v -> {
            Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
        });

        builder.setView(dialogView)
                .setTitle("Add Emergency Contact")
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = nameEdit.getText().toString().trim();
                    String phone = phoneEdit.getText().toString().trim();
                    String relation = relationEdit.getText().toString().trim();
                    boolean isPrimary = primaryCheck.isChecked();

                    if (validateInput(name, phone, relation)) {
                        addContact(name, phone, relation, isPrimary);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showEditContactDialog(EmergencyContact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_contact, null);

        EditText nameEdit = dialogView.findViewById(R.id.editName);
        EditText phoneEdit = dialogView.findViewById(R.id.editPhone);
        EditText relationEdit = dialogView.findViewById(R.id.editRelation);
        CheckBox primaryCheck = dialogView.findViewById(R.id.checkPrimary);

        nameEdit.setText(contact.getName());
        phoneEdit.setText(contact.getPhoneNumber());
        relationEdit.setText(contact.getRelationship());
        primaryCheck.setChecked(contact.isPrimary());

        builder.setView(dialogView)
                .setTitle("Edit Contact")
                .setPositiveButton("Update", (dialog, which) -> {
                    String name = nameEdit.getText().toString().trim();
                    String phone = phoneEdit.getText().toString().trim();
                    String relation = relationEdit.getText().toString().trim();
                    boolean isPrimary = primaryCheck.isChecked();

                    if (validateInput(name, phone, relation)) {
                        contact.setName(name);
                        contact.setPhoneNumber(phone);
                        contact.setRelationship(relation);
                        contact.setPrimary(isPrimary);
                        updateContact(contact);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private boolean validateInput(String name, String phone, String relation) {
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.isEmpty() || phone.length() < 10) {
            Toast.makeText(this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (relation.isEmpty()) {
            Toast.makeText(this, "Please enter relationship", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void addContact(String name, String phone, String relation, boolean isPrimary) {
        executorService.execute(() -> {
            EmergencyContact contact = new EmergencyContact(name, phone, relation, isPrimary);
            database.emergencyContactDao().insert(contact);
            runOnUiThread(() -> Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show());
        });
    }

    private void updateContact(EmergencyContact contact) {
        executorService.execute(() -> {
            database.emergencyContactDao().update(contact);
            runOnUiThread(() -> Toast.makeText(this, "Contact updated", Toast.LENGTH_SHORT).show());
        });
    }

    private void onDeleteContact(EmergencyContact contact) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete " + contact.getName() + "?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    executorService.execute(() -> {
                        database.emergencyContactDao().delete(contact);
                        runOnUiThread(() -> Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show());
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void onEditContact(EmergencyContact contact) {
        showEditContactDialog(contact);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri contactUri = data.getData();
            if (contactUri != null) {
                String[] projection = {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID};

                try (Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null)) {
                    if (cursor != null && cursor.moveToFirst()) {
                        int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                        int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);

                        String name = cursor.getString(nameIndex);
                        String contactId = cursor.getString(idIndex);

                        // Get phone number
                        String phoneNumber = getPhoneNumber(contactId);

                        // Update the dialog fields
                        if (currentNameEdit != null) {
                            currentNameEdit.setText(name);
                        }
                        if (currentPhoneEdit != null && phoneNumber != null) {
                            currentPhoneEdit.setText(phoneNumber);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Error reading contact", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String getPhoneNumber(String contactId) {
        String phoneNumber = null;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
        String[] selectionArgs = {contactId};

        try (Cursor phoneCursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null)) {
            if (phoneCursor != null && phoneCursor.moveToFirst()) {
                int numberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                phoneNumber = phoneCursor.getString(numberIndex);
            }
        } catch (Exception e) {
            // Handle exception
        }
        return phoneNumber;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
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
