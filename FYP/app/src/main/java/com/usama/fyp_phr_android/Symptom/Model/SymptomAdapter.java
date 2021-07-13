package com.usama.fyp_phr_android.Symptom.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistory;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistoryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.ViewHolder> {
    ArrayList<AllDisease> allDiseaseArrayList;
    ArrayList<AllSymptom> allSymptomArrayList;
    ArrayList<Symptom> symptomArrayList;

    public SymptomAdapter(ArrayList<AllDisease> allDiseaseArrayList, ArrayList<AllSymptom> allSymptomArrayList, ArrayList<Symptom> symptomArrayList) {
        this.allDiseaseArrayList = allDiseaseArrayList;
        this.allSymptomArrayList = allSymptomArrayList;
        this.symptomArrayList = symptomArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_symptom_items, parent, false);

        // Return a new holder instance
        SymptomAdapter.ViewHolder viewHolder = new SymptomAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Symptom symptom = symptomArrayList.get(position);

        TextView tvSymptomId = holder.tvSymptomId;
        TextView tvSymptomDiseaseName = holder.tvSymptomDiseaseName;
        TextView tvSymptomName = holder.tvSymptomName;
        TextView tvSymptomStartDate = holder.tvSymptomStartDate;
        TextView tvSymptomEndDate = holder.tvSymptomEndDate;
        TextView tvSymptomLastUpdated = holder.tvSymptomLastUpdated;

        String dname = "";
        for (int i = 0; i < allDiseaseArrayList.size(); i++) {
            if (symptom.getD_id() == allDiseaseArrayList.get(i).getD_id())
                dname = allDiseaseArrayList.get(i).getD_name();
        }

        String sname = "";
        for (int i = 0; i < allSymptomArrayList.size(); i++) {
            if (symptom.getD_id() == allSymptomArrayList.get(i).getS_id())
                sname = allSymptomArrayList.get(i).getS_name();
        }

        tvSymptomId.setText("ID: " + symptom.getUs_id());
        tvSymptomDiseaseName.setText("Disease: " + dname);
        tvSymptomName.setText("Symptom: " + sname);
        tvSymptomStartDate.setText("Start Date: " + symptom.getUs_StartDate());
        tvSymptomEndDate.setText("End Date: " + symptom.getUs_EndDate());
        tvSymptomLastUpdated.setText("Updated Date: " + symptom.getLastUpdated());

    }

    @Override
    public int getItemCount() {
        return symptomArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSymptomId;
        TextView tvSymptomDiseaseName;
        TextView tvSymptomName;
        TextView tvSymptomStartDate;
        TextView tvSymptomEndDate;
        TextView tvSymptomLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvSymptomId = itemView.findViewById(R.id.tvSymptomId);
            tvSymptomDiseaseName = itemView.findViewById(R.id.tvSymptomDiseaseName);
            tvSymptomName = itemView.findViewById(R.id.tvSymptomName);
            tvSymptomStartDate = itemView.findViewById(R.id.tvSymptomStartDate);
            tvSymptomEndDate = itemView.findViewById(R.id.tvSymptomEndDate);
            tvSymptomLastUpdated = itemView.findViewById(R.id.tvSymptomLastUpdated);
        }
    }
}
