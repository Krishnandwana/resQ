package com.resqher.safety.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resqher.safety.R;
import com.resqher.safety.models.LegalRightsSection;

import java.util.List;

public class LegalRightsAdapter extends RecyclerView.Adapter<LegalRightsAdapter.LegalViewHolder> {

    private final List<LegalRightsSection> sections;

    public LegalRightsAdapter(List<LegalRightsSection> sections) {
        this.sections = sections;
    }

    @NonNull
    @Override
    public LegalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_legal_right, parent, false);
        return new LegalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LegalViewHolder holder, int position) {
        holder.bind(sections.get(position));
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    static class LegalViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView subtitleText;
        private final TextView pointsText;

        LegalViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.legalTitle);
            subtitleText = itemView.findViewById(R.id.legalSubtitle);
            pointsText = itemView.findViewById(R.id.legalPoints);
        }

        void bind(LegalRightsSection section) {
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
