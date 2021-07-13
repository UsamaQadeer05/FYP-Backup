package com.usama.fyp_phr_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.usama.fyp_phr_android.Allergy.AllergyActivity;
import com.usama.fyp_phr_android.Disease.DiseaseActivity;
import com.usama.fyp_phr_android.Medication.MedicationActivity;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistory;
import com.usama.fyp_phr_android.SocialHistory.SocialHistoryActivity;
import com.usama.fyp_phr_android.Summary.SummaryActivity;
import com.usama.fyp_phr_android.Symptom.SymptomActivity;
import com.usama.fyp_phr_android.Vaccination.VaccinationActivity;
import com.usama.fyp_phr_android.VitalSign.Model.Vitals;
import com.usama.fyp_phr_android.VitalSign.Model.VitalsAdapter;
import com.usama.fyp_phr_android.VitalSign.VitalSignsActivity;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    TextView tvdashProfileName, tvHeight, tvWeight, tvBP;
    int id;
    String name;
    ArrayList<Vitals> vitalsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initView();

        //  Getting Login User Id
        getSharedPreferences();

        getUserVitals();
    }


    //  Initializing Objects
    private void initView() {
        tvdashProfileName = findViewById(R.id.tvdashProfileName);
        tvHeight = findViewById(R.id.tveHeight);
        tvWeight = findViewById(R.id.tveWeight);
        tvBP = findViewById(R.id.tveBP);
    }


    //  Getting User Id in Shared Preferences
    private void getSharedPreferences() {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        id = sh.getInt("User Id", 0);
        name = sh.getString("User Name", "");

        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        // We can then use the data
//        name.setText(s1);
//        age.setText(String.valueOf(a));

    }


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dash_disease:
                startActivity(new Intent(DashboardActivity.this, DiseaseActivity.class));
                break;
            case R.id.dash_allergy:
                startActivity(new Intent(DashboardActivity.this, AllergyActivity.class));
                break;
            case R.id.dash_medication:
                startActivity(new Intent(DashboardActivity.this, MedicationActivity.class));
                break;
            case R.id.dash_familymember:
                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                break;
            case R.id.dash_socialhistory:
                startActivity(new Intent(DashboardActivity.this, SocialHistoryActivity.class));
                break;
            case R.id.dash_symptom:
                startActivity(new Intent(DashboardActivity.this, SymptomActivity.class));
                break;
            case R.id.dash_vitals:
                startActivity(new Intent(DashboardActivity.this, VitalSignsActivity.class));
                break;
            case R.id.dash_vaccination:
                startActivity(new Intent(DashboardActivity.this, VaccinationActivity.class));
                break;
            case R.id.dash_summary:
                startActivity(new Intent(DashboardActivity.this, SummaryActivity.class));
                break;

        }
    }


    //  API Call to get User Vital Signs through AFNL
    private void getUserVitals() {
        vitalsArrayList = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/VitalSigns/GetUserVitals/{id}")
                .addPathParameter("id", "" + id)
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Vitals.class, new ParsedRequestListener<List<Vitals>>() {
                    @Override
                    public void onResponse(List<Vitals> diseases) {
                        // do anything with response
//                        Log.d(TAG, "userList size : " + users.size());
                        for (Vitals disease : diseases) {
                            vitalsArrayList.add(disease);
                        }
                        int size = vitalsArrayList.size() - 1;
                        tvWeight.setText(vitalsArrayList.get(size).getUvi_height());
                        tvHeight.setText(vitalsArrayList.get(size).getUvi_weight());
                        tvBP.setText(vitalsArrayList.get(size).getUvi_bloodpressure());
                        tvdashProfileName.setText(name);
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(DashboardActivity.this, "Error: " + anError.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}