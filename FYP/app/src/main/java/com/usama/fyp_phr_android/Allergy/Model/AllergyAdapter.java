package com.usama.fyp_phr_android.Allergy.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistoryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AllergyAdapter extends RecyclerView.Adapter<AllergyAdapter.ViewHolder> {
    ArrayList<AllAllergy> allAllergies;
    ArrayList<Allergy> allergyArrayList;

    public AllergyAdapter(ArrayList<AllAllergy> allAllergies, ArrayList<Allergy> allergyArrayList) {
        this.allAllergies = allAllergies;
        this.allergyArrayList = allergyArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_allergy_items, parent, false);

        // Return a new holder instance
        AllergyAdapter.ViewHolder viewHolder = new AllergyAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Allergy allergy = allergyArrayList.get(position);

        TextView tvAllergyId = holder.tvAllergyId;
        TextView tvAllergyName = holder.tvAllergyName;
        TextView tvAllergyLevel = holder.tvAllergyLevel;
        TextView tvAllergyStartDate = holder.tvAllergyStartDate;
        TextView tvAllergyLastUpdated = holder.tvAllergyLastUpdated;

        String dname = "";
        for (int i = 0; i < allergyArrayList.size(); i++) {
            if (allergy.getA_id() == allAllergies.get(i).getA_id())
                dname = allAllergies.get(i).getA_name();
        }

        tvAllergyId.setText("ID: " + allergy.getUa_id());
        tvAllergyName.setText("Name: " + dname);
        tvAllergyLevel.setText("Level: " + allergy.getUa_Level());
        tvAllergyStartDate.setText("Start Date: " + allergy.getUa_StartDate());
        tvAllergyLastUpdated.setText("Updated Date: " + allergy.getLastUpdated());
    }

    @Override
    public int getItemCount() {
        return allergyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAllergyId;
        TextView tvAllergyName;
        TextView tvAllergyLevel;
        TextView tvAllergyStartDate;
        TextView tvAllergyLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvAllergyId = itemView.findViewById(R.id.tvAllergyId);
            tvAllergyName = itemView.findViewById(R.id.tvAllergyName);
            tvAllergyLevel = itemView.findViewById(R.id.tvAllergyLevel);
            tvAllergyStartDate = itemView.findViewById(R.id.tvAllergyStartDate);
            tvAllergyLastUpdated = itemView.findViewById(R.id.tvAllergyLastUpdated);
        }
    }
}
