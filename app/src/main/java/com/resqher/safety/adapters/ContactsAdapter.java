package com.resqher.safety.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.models.EmergencyContact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private List<EmergencyContact> contacts;
    private OnContactDeleteListener deleteListener;
    private OnContactEditListener editListener;

    public interface OnContactDeleteListener {
        void onDelete(EmergencyContact contact);
    }

    public interface OnContactEditListener {
        void onEdit(EmergencyContact contact);
    }

    public ContactsAdapter(List<EmergencyContact> contacts,
                          OnContactDeleteListener deleteListener,
                          OnContactEditListener editListener) {
        this.contacts = contacts;
        this.deleteListener = deleteListener;
        this.editListener = editListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        EmergencyContact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void updateContacts(List<EmergencyContact> newContacts) {
        this.contacts = newContacts;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, phoneText, relationText, primaryBadge;
        ImageButton editButton, deleteButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.contactName);
            phoneText = itemView.findViewById(R.id.contactPhone);
            relationText = itemView.findViewById(R.id.contactRelation);
            primaryBadge = itemView.findViewById(R.id.primaryBadge);
            editButton = itemView.findViewById(R.id.btnEdit);
            deleteButton = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(EmergencyContact contact) {
            nameText.setText(contact.getName());
            phoneText.setText(contact.getPhoneNumber());
            relationText.setText(contact.getRelationship());
            primaryBadge.setVisibility(contact.isPrimary() ? View.VISIBLE : View.GONE);

            editButton.setOnClickListener(v -> {
                if (editListener != null) {
                    editListener.onEdit(contact);
                }
            });

            deleteButton.setOnClickListener(v -> {
                if (deleteListener != null) {
                    deleteListener.onDelete(contact);
                }
            });
        }
    }
}
