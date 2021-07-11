package com.usama.fyp_phr_android.Disease;

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
import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.Model.User;
import com.usama.fyp_phr_android.User.SignInActivity;
import com.usama.fyp_phr_android.User.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiseaseActivity extends AppCompatActivity {
    TextInputLayout inputDiseases,
            inputLevel,
            inputStatus;

    AutoCompleteTextView act_Diseases,
            act_Level,
            act_Status;

    EditText etStartDate,
            etEndDate,
            etLastUpdated;

    ArrayList<String> arrayList_Diseases,
            arrayList_Level,
            arrayList_Status;

    ArrayAdapter<String> arrayAdapter_Diseases,
            arrayAdapter_Level,
            arrayAdapter_Status;

    Button btnAdd;
    ProgressBar pg;
    int id, disease_name_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

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

        //  For Getting Selected Country
        act_Diseases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                disease_name_pos = adapterView.getSelectedItemPosition();
                disease_name_pos = i + 1;
            }
        });

        //  Function to Set Status Dropdown
        setStatusDropdown();
        //  For Getting Selected Status
        act_Status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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

        //  For Setting Start Date
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiseaseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
                        String date = day + "/" + month + "/" + year;
                        etStartDate.setText(date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

        //  For Setting End Date
        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiseaseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
                        String date = day + "/" + month + "/" + year;
                        etEndDate.setText(date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

        //  For Setting Last Updated Date
        etLastUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DiseaseActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        inputStatus = findViewById(R.id.inputStatus);

        act_Diseases = findViewById(R.id.act_Diseases);
        act_Level = findViewById(R.id.act_Level);
        act_Status = findViewById(R.id.act_Status);

        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etLastUpdated = findViewById(R.id.etLastUpdated);

        btnAdd = findViewById(R.id.btnAdd);
        pg = findViewById(R.id.disease_progress);
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
        arrayAdapter_Diseases = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Diseases);
        act_Diseases.setAdapter(arrayAdapter_Diseases);
        act_Diseases.setThreshold(1);
    }


    //  Setting Status Dropdown
    private void setStatusDropdown() {
        arrayList_Status = new ArrayList<>();
        arrayList_Status.add("Active");
        arrayList_Status.add("Cured");
        arrayList_Status.add("Expired");

        arrayAdapter_Status = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Status);
        act_Status.setAdapter(arrayAdapter_Status);
        act_Status.setThreshold(1);
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


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn Add Diseases
            case R.id.btnAdd:
                RegisterDiseaseFieldsValidation();
                break;
        }
    }


    //  Register Disease Field's Validation
    private void RegisterDiseaseFieldsValidation() {

        if (TextUtils.isEmpty(inputDiseases.getEditText().getText().toString())) {
            inputDiseases.setError("Disease can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputLevel.getEditText().getText().toString())) {
            inputLevel.setError("Level can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputStatus.getEditText().getText().toString())) {
            inputStatus.setError("Status can not be null");
            return;
        }

        if (TextUtils.isEmpty(etStartDate.getText().toString())) {
            etStartDate.setError("Start Date can not be null");
            return;
        }

        if (TextUtils.isEmpty(etLastUpdated.getText().toString())) {
            etLastUpdated.setError("Last Updated Date can not be null");
            return;
        }

        //  Function to Register User Disease
        RegisterDisease();

    }


    //  API Call to get All Diseases through AFNL
    private void getAllDisease() {
        arrayList_Diseases = new ArrayList<>();

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
                            arrayList_Diseases.add(disease.getD_name());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(DiseaseActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to Register User Diseases through AFNL
    private void RegisterDisease() {
        pg.setVisibility(View.VISIBLE);

        Disease disease = new Disease();
        disease.setU_id(id);
        disease.setD_id(disease_name_pos);
        disease.setUd_level(inputLevel.getEditText().getText().toString());
        disease.setUd_Status(inputStatus.getEditText().getText().toString());
        disease.setUd_StartDate(etStartDate.getText().toString());
        if (etEndDate.getText().equals(""))
            disease.setUd_EndDate("00/00/0000");
        else
            disease.setUd_EndDate(etEndDate.getText().toString());
        disease.setLastUpdated(etLastUpdated.getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Diseases/AddingUserDiseases")
                .addBodyParameter(disease) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(DiseaseActivity.this, "Added User Disease Successfully: ", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(DiseaseActivity.this, DiseaseActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(DiseaseActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
    }
}