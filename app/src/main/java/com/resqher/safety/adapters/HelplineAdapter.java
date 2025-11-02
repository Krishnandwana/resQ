package com.resqher.safety.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.models.Helpline;

import java.util.List;

public class HelplineAdapter extends RecyclerView.Adapter<HelplineAdapter.HelplineViewHolder> {

    private List<Helpline> helplines;
    private OnHelplineCallListener callListener;

    public interface OnHelplineCallListener {
        void onCall(Helpline helpline);
    }

    public HelplineAdapter(List<Helpline> helplines, OnHelplineCallListener callListener) {
        this.helplines = helplines;
        this.callListener = callListener;
    }

    @NonNull
    @Override
    public HelplineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_helpline, parent, false);
        return new HelplineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelplineViewHolder holder, int position) {
        Helpline helpline = helplines.get(position);
        holder.bind(helpline);
    }

    @Override
    public int getItemCount() {
        return helplines.size();
    }

    public void updateHelplines(List<Helpline> newHelplines) {
        this.helplines = newHelplines;
        notifyDataSetChanged();
    }

    class HelplineViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, numberText, descriptionText, categoryText;
        Button callButton;

        public HelplineViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.helplineName);
            numberText = itemView.findViewById(R.id.helplineNumber);
            descriptionText = itemView.findViewById(R.id.helplineDescription);
            categoryText = itemView.findViewById(R.id.helplineCategory);
            callButton = itemView.findViewById(R.id.btnCall);
        }

        public void bind(Helpline helpline) {
            nameText.setText(helpline.getName());
            numberText.setText(helpline.getNumber());
            descriptionText.setText(helpline.getDescription());
            categoryText.setText(helpline.getCategory());

            callButton.setOnClickListener(v -> {
                if (callListener != null) {
                    callListener.onCall(helpline);
                }
            });
        }
    }
}
