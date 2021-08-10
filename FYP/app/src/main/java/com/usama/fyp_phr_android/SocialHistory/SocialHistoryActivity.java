package com.usama.fyp_phr_android.SocialHistory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.Disease.DiseaseActivity;
import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SocialHistoryActivity extends AppCompatActivity {
    TextInputLayout inputDiseases, inputLevel, inputRelation;
    AutoCompleteTextView act_Diseases, act_Level, act_Relation;
    ArrayList<String> arrayList_Disease, arrayList_Level, arrayList_Relation;
    ArrayAdapter<String> arrayAdapter_Disease, arrayAdapter_Level, arrayAdapter_Relation;
    EditText etLastUpdated;
    Button btnAdd;
    ProgressBar pg;
    int id, disease_name_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_history);

        initView();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        //  Function to Get All Diseases
        getAllDisease();

        //  Function to Set Disease Dropdown
        setDiseaseDropdown();

        //  Getting Login User Id
        getSharedPreferences();

        //  For Getting Selected Disease
        act_Diseases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                disease_name_pos = adapterView.getSelectedItemPosition();
                disease_name_pos = i + 1;
            }
        });

        //  Function to Set Level Dropdown
        setLevelDropdown();
        //  For Getting Selected Level
        act_Level.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        //  Function to Set Relation Dropdown
        setRelationDropdown();
        //  For Getting Selected Relation
        act_Relation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        //  For Setting Last Updated Date
        etLastUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SocialHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
                        String date = day + "/" + month + "/" + year;
                        etLastUpdated.setText(date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });


    }

    //  Intializing Objects
    private void initView() {
        inputDiseases = findViewById(R.id.inputDiseases);
        inputLevel = findViewById(R.id.inputLevel);
        inputRelation = findViewById(R.id.inputRelation);

        act_Diseases = findViewById(R.id.act_Diseases);
        act_Level = findViewById(R.id.act_Level);
        act_Relation = findViewById(R.id.act_Relation);

        etLastUpdated = findViewById(R.id.etLastUpdated);

        btnAdd = findViewById(R.id.btnAdd);
        pg = findViewById(R.id.social_progress);
    }


    //  Getting User Id in Shared Preferences
    private void getSharedPreferences() {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        id = sh.getInt("User Id", 0);

        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        // We can then use the data
//        name.setText(s1);
//        age.setText(String.valueOf(a));

    }


    //  Setting Disease Dropdown
    private void setDiseaseDropdown() {
        arrayAdapter_Disease = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Disease);
        act_Diseases.setAdapter(arrayAdapter_Disease);
        act_Diseases.setThreshold(1);
    }


    //  Setting Level Dropdown
    private void setLevelDropdown() {
        arrayList_Level = new ArrayList<>();
        arrayList_Level.add("Minor");
        arrayList_Level.add("Major");
        arrayList_Level.add("Severe");

        arrayAdapter_Level = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Level);
        act_Level.setAdapter(arrayAdapter_Level);
        act_Level.setThreshold(1);
    }


    //  Setting Relation Dropdown
    private void setRelationDropdown() {
        arrayList_Relation = new ArrayList<>();
        arrayList_Relation.add("Uncle");
        arrayList_Relation.add("Friend");

        arrayAdapter_Relation = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Relation);
        act_Relation.setAdapter(arrayAdapter_Relation);
        act_Relation.setThreshold(1);
    }


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn Add Social History
            case R.id.btnAdd:
                RegisterSocialFieldsValidation();
                break;
        }
    }


    //  Register Social History Field's Validation
    private void RegisterSocialFieldsValidation() {

        if (TextUtils.isEmpty(inputDiseases.getEditText().getText().toString())) {
            inputDiseases.setError("Disease can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputLevel.getEditText().getText().toString())) {
            inputLevel.setError("Level can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputRelation.getEditText().getText().toString())) {
            inputRelation.setError("Relation can not be null");
            return;
        }


        if (TextUtils.isEmpty(etLastUpdated.getText().toString())) {
            etLastUpdated.setError("Last Updated Date can not be null");
            return;
        }

        //  Function to Register User Social History
        RegisterSocialHistory();

    }


    //  API Call to get All Diseases through AFNL
    private void getAllDisease() {
        arrayList_Disease = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Diseases/Diseases")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(AllDisease.class, new ParsedRequestListener<List<AllDisease>>() {
                    @Override
                    public void onResponse(List<AllDisease> allDiseases) {
                        // do anything with response
                        for (AllDisease disease : allDiseases) {
                            arrayList_Disease.add(disease.getD_name());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(SocialHistoryActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to Register User Social History through AFNL
    private void RegisterSocialHistory() {
        pg.setVisibility(View.VISIBLE);

        SocialHistory history = new SocialHistory();
        history.setU_id(id);
        history.setD_id(disease_name_pos);
        history.setS_level(inputLevel.getEditText().getText().toString());
        history.setS_relation(inputRelation.getEditText().getText().toString());
        history.setLastUpdated(etLastUpdated.getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/SocialHistory/AddingUserSocialHistory")
                .addBodyParameter(history) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(SocialHistoryActivity.this,
                                    "Added User Social History Successfully: ", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(SocialHistoryActivity.this,
//                                    SocialHistoryActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(SocialHistoryActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
    }


}