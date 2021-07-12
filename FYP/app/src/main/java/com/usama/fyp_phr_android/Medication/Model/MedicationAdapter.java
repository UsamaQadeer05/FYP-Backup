package com.usama.fyp_phr_android.Medication.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.Disease.Model.DiseaseAdapter;
import com.usama.fyp_phr_android.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder> {
    ArrayList<AllDisease> allDiseaseArrayList;
    ArrayList<AllMedication> allMedicationArrayList;
    ArrayList<Medication> medicationArrayList;

    public MedicationAdapter(ArrayList<AllDisease> allDiseaseArrayList, ArrayList<AllMedication> allMedicationArrayList, ArrayList<Medication> medicationArrayList) {
        this.allDiseaseArrayList = allDiseaseArrayList;
        this.allMedicationArrayList = allMedicationArrayList;
        this.medicationArrayList = medicationArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_medication_items, parent, false);

        // Return a new holder instance
        MedicationAdapter.ViewHolder viewHolder = new MedicationAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Medication medication = medicationArrayList.get(position);
        TextView tvMedicationId = holder.tvMedicationId;
        TextView tvMedicationDiseaseName = holder.tvMedicationDiseaseName;
        TextView tvMedicationName = holder.tvMedicationName;
        TextView tvMedicationCompany = holder.tvMedicationCompany;
        TextView tvMedicationDosage = holder.tvMedicationDosage;
        TextView tvMedicationStartDate = holder.tvMedicationStartDate;
        TextView tvMedicationEndDate = holder.tvMedicationEndDate;
        TextView tvMedicationLastUpdated = holder.tvMedicationLastUpdated;


        String dname = "";
        String mname = "";
        String cname = "";

        for (int i = 0; i < allDiseaseArrayList.size(); i++) {
            if (medication.getD_id() == allDiseaseArrayList.get(i).getD_id())
                dname = allDiseaseArrayList.get(i).getD_name();
        }

        for (int i = 0; i < allMedicationArrayList.size(); i++) {
            if (medication.getD_id() == allMedicationArrayList.get(i).getM_id()) {
                dname = allMedicationArrayList.get(i).getM_name();
                cname = allMedicationArrayList.get(i).getM_company();
            }
        }


        tvMedicationId.setText("ID: " + medication.getUm_id());
        tvMedicationDiseaseName.setText("Disease: " + dname);
        tvMedicationName.setText("Medicine: " + mname);
        tvMedicationCompany.setText("Company: " + cname);
        tvMedicationDosage.setText("Dosage: " + medication.getUm_dosage());
        tvMedicationStartDate.setText("Start Date: " + medication.getUm_StartDate());
        tvMedicationEndDate.setText("End Date: " + medication.getUm_EndDate());
        tvMedicationLastUpdated.setText("Last Updated: " + medication.getLastUpdated());

    }

    @Override
    public int getItemCount() {
        return medicationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicationId;
        TextView tvMedicationDiseaseName;
        TextView tvMedicationName;
        TextView tvMedicationCompany;
        TextView tvMedicationDosage;
        TextView tvMedicationStartDate;
        TextView tvMedicationEndDate;
        TextView tvMedicationLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvMedicationId = itemView.findViewById(R.id.tvMedicationId);
            tvMedicationDiseaseName = itemView.findViewById(R.id.tvMedicationDiseaseName);
            tvMedicationName = itemView.findViewById(R.id.tvMedicationName);
            tvMedicationCompany = itemView.findViewById(R.id.tvMedicationCompany);
            tvMedicationDosage = itemView.findViewById(R.id.tvMedicationDosage);
            tvMedicationStartDate = itemView.findViewById(R.id.tvMedicationStartDate);
            tvMedicationEndDate = itemView.findViewById(R.id.tvMedicationEndDate);
            tvMedicationLastUpdated = itemView.findViewById(R.id.tvMedicationLastUpdated);
        }
    }
}
