package com.usama.fyp_phr_android.Disease.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.Model.ProfileAdapter;
import com.usama.fyp_phr_android.User.Model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.ViewHolder> {
    ArrayList<Disease> diseaseArrayList;
    ArrayList<AllDisease> allDiseaseArrayList;

    public DiseaseAdapter(ArrayList<Disease> diseaseArrayList, ArrayList<AllDisease> allDiseaseArrayList) {
        this.diseaseArrayList = diseaseArrayList;
        this.allDiseaseArrayList = allDiseaseArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_disease_items, parent, false);

        // Return a new holder instance
        DiseaseAdapter.ViewHolder viewHolder = new DiseaseAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Disease disease = diseaseArrayList.get(position);
        TextView tvDiseaseId = holder.tvDiseaseId;
        TextView tvDiseaseName = holder.tvDiseaseName;
        TextView tvDiseaseLevel = holder.tvDiseaseLevel;
        TextView tvDiseaseStatus = holder.tvDiseaseStatus;
        TextView tvDiseaseStartDate = holder.tvDiseaseStartDate;
        TextView tvDiseaseEndDate = holder.tvDiseaseEndDate;
        TextView tvDiseaseLastUpdated = holder.tvDiseaseLastUpdated;

        String dname = allDiseaseArrayList.get(disease.getD_id() - 1).getD_name();

        tvDiseaseId.setText("ID: " + disease.getUd_id());
        tvDiseaseName.setText("Name: " + dname);
        tvDiseaseLevel.setText("Level: " + disease.getUd_level());
        tvDiseaseStatus.setText("Status: " + disease.getUd_Status());
        tvDiseaseStartDate.setText("Start Date: " + disease.getUd_StartDate());
        tvDiseaseEndDate.setText("End Date: " + disease.getUd_EndDate());
        tvDiseaseLastUpdated.setText("Last Updated: " + disease.getLastUpdated());
    }

    @Override
    public int getItemCount() {
        return diseaseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDiseaseId;
        TextView tvDiseaseName;
        TextView tvDiseaseLevel;
        TextView tvDiseaseStatus;
        TextView tvDiseaseStartDate;
        TextView tvDiseaseEndDate;
        TextView tvDiseaseLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvDiseaseId = itemView.findViewById(R.id.tvDiseaseId);
            tvDiseaseName = itemView.findViewById(R.id.tvDiseaseName);
            tvDiseaseLevel = itemView.findViewById(R.id.tvDiseaseLevel);
            tvDiseaseStatus = itemView.findViewById(R.id.tvDiseaseStatus);
            tvDiseaseStartDate = itemView.findViewById(R.id.tvDiseaseStartDate);
            tvDiseaseEndDate = itemView.findViewById(R.id.tvDiseaseEndDate);
            tvDiseaseLastUpdated = itemView.findViewById(R.id.tvDiseaseLastUpdated);
        }
    }
}
