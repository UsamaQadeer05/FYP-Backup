package com.usama.fyp_phr_android.Summary;

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

public class SummaryActivity extends AppCompatActivity {
    ProfileFragment profileFragment;
    DiseaseFragment diseaseFragment;
    AllergyFragment allergyFragment;
    MedicationFragment medicationFragment;
    SocialHistoryFragment socialHistoryFragment;
    SymptomFragment symptomFragment;
    VitalsFragment vitalsFragment;
    VaccinationFragment vaccinationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initView();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.profileFragment, profileFragment)
                .replace(R.id.diseaseFragment, diseaseFragment)
                .replace(R.id.allergyFragment, allergyFragment)
                .replace(R.id.medicationFragment, medicationFragment)
                .replace(R.id.socialFragment, socialHistoryFragment)
                .replace(R.id.symptomFragment, symptomFragment)
                .replace(R.id.vitalsFragment, vitalsFragment)
                .replace(R.id.vaccinationFragment, vaccinationFragment)
                .commit();
    }


    //  Initializing Fragments
    private void initView() {
        profileFragment = new ProfileFragment();
        diseaseFragment = new DiseaseFragment();
        allergyFragment = new AllergyFragment();
        medicationFragment = new MedicationFragment();
        socialHistoryFragment = new SocialHistoryFragment();
        symptomFragment = new SymptomFragment();
        vitalsFragment = new VitalsFragment();
        vaccinationFragment = new VaccinationFragment();
    }
}