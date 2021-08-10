package com.usama.fyp_phr_android.Researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.usama.fyp_phr_android.Allergy.AllergyFragment;
import com.usama.fyp_phr_android.Disease.DiseaseFragment;
import com.usama.fyp_phr_android.Medication.MedicationFragment;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.SocialHistory.SocialHistoryFragment;
import com.usama.fyp_phr_android.Symptom.SymptomFragment;
import com.usama.fyp_phr_android.User.ProfileFragment;
import com.usama.fyp_phr_android.Vaccination.VaccinationFragment;
import com.usama.fyp_phr_android.VitalSign.VitalsFragment;

public class AnalysisActivity extends AppCompatActivity {
    CountryFragment countryFragment;
    CityFragment cityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);


        initView();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.countryFragment, countryFragment)
                .replace(R.id.cityFragment, cityFragment)
                .commit();
    }

    //  Initializing Fragments
    private void initView() {
        countryFragment = new CountryFragment();
        cityFragment = new CityFragment();
    }
}