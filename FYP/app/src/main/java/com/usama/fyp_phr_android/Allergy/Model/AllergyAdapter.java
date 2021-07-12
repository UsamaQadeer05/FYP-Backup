package com.usama.fyp_phr_android.Allergy.Model;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.Model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class AllergyAdapter extends RecyclerView.Adapter<AllergyAdapter.ViewHolder> {

    ArrayList<User> userArrayList;

    public AllergyAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.row_display_profile_items, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        User user = userArrayList.get(position);
        TextView tvProfileId = holder.tvProfileId;
        TextView tvProfileName = holder.tvProfileName;
        TextView tvProfileGender = holder.tvProfileGender;
        TextView tvProfileDOB = holder.tvProfileDOB;
        TextView tvProfileCountry = holder.tvProfileCountry;
        TextView tvProfileProvince = holder.tvProfileProvince;
        TextView tvProfileCity = holder.tvProfileCity;
        TextView tvWaterFac = holder.tvWaterFac;
        TextView tvArea = holder.tvArea;
        TextView tvHome = holder.tvHome;
        TextView tvFood = holder.tvFood;
        TextView tvVentilation = holder.tvVentilation;

        tvProfileId.setText(user.getU_id());
        tvProfileName.setText(user.getU_name());
        tvProfileGender.setText(user.getU_gender());
        tvProfileDOB.setText(user.getU_dob());
        tvProfileCountry.setText(user.getU_country());
        tvProfileProvince.setText(user.getU_province());
        tvProfileCity.setText(user.getU_city());
        tvWaterFac.setText(user.getU_waterfac());
        tvArea.setText(user.getU_area());
        tvHome.setText(user.getU_home());
        tvFood.setText(user.getU_food());
        tvVentilation.setText(user.getU_ventilation());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProfileId;
        TextView tvProfileName;
        TextView tvProfileGender;
        TextView tvProfileDOB;
        TextView tvProfileCountry;
        TextView tvProfileProvince;
        TextView tvProfileCity;
        TextView tvWaterFac;
        TextView tvArea;
        TextView tvHome;
        TextView tvFood;
        TextView tvVentilation;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.tvProfileId = itemView.findViewById(R.id.tvProfileId);
            this.tvProfileName = itemView.findViewById(R.id.tvProfileName);
            this.tvProfileGender = itemView.findViewById(R.id.tvProfileGender);
            this.tvProfileDOB = itemView.findViewById(R.id.tvProfileDOB);
            this.tvProfileCountry = itemView.findViewById(R.id.tvProfileCountry);
            this.tvProfileProvince = itemView.findViewById(R.id.tvProfileProvince);
            this.tvProfileCity = itemView.findViewById(R.id.tvProfileCity);
            this.tvWaterFac = itemView.findViewById(R.id.tvWaterFac);
            this.tvArea = itemView.findViewById(R.id.tvArea);
            this.tvHome = itemView.findViewById(R.id.tvHome);
            this.tvFood = itemView.findViewById(R.id.tvFood);
            this.tvVentilation = itemView.findViewById(R.id.tvVentilation);
        }
    }
}
