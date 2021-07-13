package com.usama.fyp_phr_android.Vaccination.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.Symptom.Model.Symptom;
import com.usama.fyp_phr_android.Symptom.Model.SymptomAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.ViewHolder> {
    ArrayList<AllVaccination> allVaccinations;
    ArrayList<Vaccination> vaccinationArrayList;

    public VaccinationAdapter(ArrayList<AllVaccination> allVaccinations, ArrayList<Vaccination> vaccinationArrayList) {
        this.allVaccinations = allVaccinations;
        this.vaccinationArrayList = vaccinationArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_vaccination_items, parent, false);

        // Return a new holder instance
        VaccinationAdapter.ViewHolder viewHolder = new VaccinationAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Vaccination vaccination = vaccinationArrayList.get(position);

        TextView tvVacId = holder.tvVacId;
        TextView tvVacName = holder.tvVacName;
        TextView tvVacCompany = holder.tvVacCompany;
        TextView tvVacDosage = holder.tvVacDosage;
        TextView tvVacInjectedDate = holder.tvVacInjectedDate;
        TextView tvVacLastUpdated = holder.tvVacLastUpdated;

        String vname = "";
        String cname = "";

        for (int i = 0; i < allVaccinations.size(); i++) {
            if (vaccination.getV_id() == allVaccinations.get(i).getV_id()) {
                vname = allVaccinations.get(i).getV_name();
                cname = allVaccinations.get(i).getV_company();
            }
        }

        tvVacId.setText("ID: " + vaccination.getUv_id());
        tvVacName.setText("Vaccination: " + vname);
        tvVacCompany.setText("Company: " + cname);
        tvVacDosage.setText("Dosage: " + vaccination.getUv_dosage());
        tvVacInjectedDate.setText("Injected Date: " + vaccination.getUv_injecteddate());
        tvVacLastUpdated.setText("Last Updated: " + vaccination.getLastUpdated());

    }

    @Override
    public int getItemCount() {
        return vaccinationArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVacId;
        TextView tvVacName;
        TextView tvVacCompany;
        TextView tvVacDosage;
        TextView tvVacInjectedDate;
        TextView tvVacLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvVacId = itemView.findViewById(R.id.tvVacId);
            tvVacName = itemView.findViewById(R.id.tvVacName);
            tvVacCompany = itemView.findViewById(R.id.tvVacCompany);
            tvVacDosage = itemView.findViewById(R.id.tvVacDosage);
            tvVacInjectedDate = itemView.findViewById(R.id.tvVacInjectedDate);
            tvVacLastUpdated = itemView.findViewById(R.id.tvVacLastUpdated);
        }
    }
}
