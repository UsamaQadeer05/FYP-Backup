package com.usama.fyp_phr_android.SocialHistory.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.Disease.Model.DiseaseAdapter;
import com.usama.fyp_phr_android.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SocialHistoryAdapter extends RecyclerView.Adapter<SocialHistoryAdapter.ViewHolder> {
    ArrayList<SocialHistory> socialHistoryArrayList;
    ArrayList<AllDisease> allDiseaseArrayList;


    public SocialHistoryAdapter(ArrayList<SocialHistory> socialHistoryArrayList, ArrayList<AllDisease> allDiseaseArrayList) {
        this.socialHistoryArrayList = socialHistoryArrayList;
        this.allDiseaseArrayList = allDiseaseArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_social_items, parent, false);

        // Return a new holder instance
        SocialHistoryAdapter.ViewHolder viewHolder = new SocialHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SocialHistory history = socialHistoryArrayList.get(position);

        TextView tvSocialHistoryId = holder.tvSocialHistoryId;
        TextView tvSocialHistoryDiseaseName = holder.tvSocialHistoryDiseaseName;
        TextView tvSocialHistoryLevel = holder.tvSocialHistoryLevel;
        TextView tvSocialHistoryRelation = holder.tvSocialHistoryRelation;
        TextView tvSocialHistoryLastUpdated = holder.tvSocialHistoryLastUpdated;

        String dname = "";
        for (int i = 0; i < allDiseaseArrayList.size(); i++) {
            if (history.getD_id() == allDiseaseArrayList.get(i).getD_id())
                dname = allDiseaseArrayList.get(i).getD_name();
        }
        tvSocialHistoryId.setText("ID: " + history.getSh_id());
        tvSocialHistoryDiseaseName.setText("Name: " + dname);
        tvSocialHistoryLevel.setText("Level: " + history.getS_level());
        tvSocialHistoryRelation.setText("Relation: " + history.getS_relation());
        tvSocialHistoryLastUpdated.setText("Updated Date: " + history.getLastUpdated());


    }

    @Override
    public int getItemCount() {
        return socialHistoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSocialHistoryId;
        TextView tvSocialHistoryDiseaseName;
        TextView tvSocialHistoryLevel;
        TextView tvSocialHistoryRelation;
        TextView tvSocialHistoryLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvSocialHistoryId = itemView.findViewById(R.id.tvSocialHistoryId);
            tvSocialHistoryDiseaseName = itemView.findViewById(R.id.tvSocialHistoryDiseaseName);
            tvSocialHistoryLevel = itemView.findViewById(R.id.tvSocialHistoryLevel);
            tvSocialHistoryRelation = itemView.findViewById(R.id.tvSocialHistoryRelation);
            tvSocialHistoryLastUpdated = itemView.findViewById(R.id.tvSocialHistoryLastUpdated);
        }
    }
}
