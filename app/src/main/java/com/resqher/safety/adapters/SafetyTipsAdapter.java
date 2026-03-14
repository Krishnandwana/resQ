package com.resqher.safety.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.models.SafetyTipSection;

import java.util.List;

public class SafetyTipsAdapter extends RecyclerView.Adapter<SafetyTipsAdapter.TipViewHolder> {

    private final List<SafetyTipSection> tipSections;

    public SafetyTipsAdapter(List<SafetyTipSection> tipSections) {
        this.tipSections = tipSections;
    }

    @NonNull
    @Override
    public TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_safety_tip, parent, false);
        return new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {
        holder.bind(tipSections.get(position));
    }

    @Override
    public int getItemCount() {
        return tipSections.size();
    }

    static class TipViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView subtitleText;
        private final TextView pointsText;

        TipViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tipTitle);
            subtitleText = itemView.findViewById(R.id.tipSubtitle);
            pointsText = itemView.findViewById(R.id.tipPoints);
        }

        void bind(SafetyTipSection section) {
            titleText.setText(section.getTitle());
            subtitleText.setText(section.getSubtitle());

            StringBuilder pointsBuilder = new StringBuilder();
            for (int i = 0; i < section.getPoints().size(); i++) {
                pointsBuilder.append("• ").append(section.getPoints().get(i));
                if (i < section.getPoints().size() - 1) {
                    pointsBuilder.append("\n");
                }
            }
            pointsText.setText(pointsBuilder.toString());
        }
    }
}
