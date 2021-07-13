package com.usama.fyp_phr_android.VitalSign.Model;

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

public class VitalsAdapter extends RecyclerView.Adapter<VitalsAdapter.ViewHolder> {
    ArrayList<Vitals> vitalsArrayList;

    public VitalsAdapter(ArrayList<Vitals> vitalsArrayList) {
        this.vitalsArrayList = vitalsArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_vitals_items, parent, false);

        // Return a new holder instance
        VitalsAdapter.ViewHolder viewHolder = new VitalsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Vitals vitals = vitalsArrayList.get(position);

        TextView tvVitalId = holder.tvVitalId;
        TextView tvVitalTemperature = holder.tvVitalTemperature;
        TextView tvVitalPulseRate = holder.tvVitalPulseRate;
        TextView tvVitalRespirationRate = holder.tvVitalRespirationRate;
        TextView tvVitalBloodPressure = holder.tvVitalBloodPressure;
        TextView tvVitalWeight = holder.tvVitalWeight;
        TextView tvVitalHeight = holder.tvVitalHeight;
        TextView tvVitalLastUpdated = holder.tvVitalLastUpdated;


        tvVitalId.setText("ID: " + vitals.getUvi_id());
        tvVitalTemperature.setText("Temperature: " + vitals.getUvi_temperature());
        tvVitalPulseRate.setText("Pulse Rate: " + vitals.getUvi_pulserate());
        tvVitalRespirationRate.setText("Respiratory Rate: " + vitals.getUvi_respirationrate());
        tvVitalBloodPressure.setText("Blood Pressure: " + vitals.getUvi_bloodpressure());
        tvVitalWeight.setText("Weight: " + vitals.getUvi_weight());
        tvVitalHeight.setText("Height: " + vitals.getUvi_height());
        tvVitalLastUpdated.setText("Updated Date: " + vitals.getLastUpdated());

    }

    @Override
    public int getItemCount() {
        return vitalsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVitalId;
        TextView tvVitalTemperature;
        TextView tvVitalPulseRate;
        TextView tvVitalRespirationRate;
        TextView tvVitalBloodPressure;
        TextView tvVitalWeight;
        TextView tvVitalHeight;
        TextView tvVitalLastUpdated;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvVitalId = itemView.findViewById(R.id.tvVitalId);
            tvVitalTemperature = itemView.findViewById(R.id.tvVitalTemperature);
            tvVitalPulseRate = itemView.findViewById(R.id.tvVitalPulseRate);
            tvVitalRespirationRate = itemView.findViewById(R.id.tvVitalRespirationRate);
            tvVitalBloodPressure = itemView.findViewById(R.id.tvVitalBloodPressure);
            tvVitalWeight = itemView.findViewById(R.id.tvVitalWeight);
            tvVitalHeight = itemView.findViewById(R.id.tvVitalHeight);
            tvVitalLastUpdated = itemView.findViewById(R.id.tvVitalLastUpdated);
        }
    }
}
