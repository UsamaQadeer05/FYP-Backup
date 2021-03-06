package com.usama.fyp_phr_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.usama.fyp_phr_android.Allergy.AllergyActivity;
import com.usama.fyp_phr_android.Disease.DiseaseActivity;
import com.usama.fyp_phr_android.Medication.MedicationActivity;
import com.usama.fyp_phr_android.SocialHistory.SocialHistoryActivity;
import com.usama.fyp_phr_android.Symptom.SymptomActivity;
import com.usama.fyp_phr_android.User.SignInActivity;
import com.usama.fyp_phr_android.User.SignUpActivity;
import com.usama.fyp_phr_android.Vaccination.VaccinationActivity;
import com.usama.fyp_phr_android.VitalSign.VitalSignsActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

//        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,
                        SignInActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, 2000);
    }
}