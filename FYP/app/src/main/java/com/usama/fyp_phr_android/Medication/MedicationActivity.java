package com.usama.fyp_phr_android.Medication;

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
import com.usama.fyp_phr_android.Medication.Model.AllMedication;
import com.usama.fyp_phr_android.Medication.Model.Medication;
import com.usama.fyp_phr_android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MedicationActivity extends AppCompatActivity {
    TextInputLayout inputDiseases, inputMedicine, inputDosage;
    AutoCompleteTextView act_Diseases, act_Medicine, act_Dosage;
    ArrayList<String> arrayList_Diseases, arrayList_Medicine, arrayList_Dosage;
    ArrayAdapter<String> arrayAdapter_Diseases, arrayAdapter_Medicine, arrayAdapter_Dosage;
    EditText etStartDate, etEndDate, etLastUpdated;
    Button btnAdd;
    ProgressBar pg;
    int id, disease_name_pos, medicine_name_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        initView();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        //  Function to Get All Diseases
        getAllDisease();

        //  Function to Get All Medication
        getAllMedication();

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

        //  Function to Set Medicine Dropdown
        setMedicineDropdown();
        //  For Getting Selected Status
        act_Medicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                medicine_name_pos = i + 1;
            }
        });

        //  Function to Set Dosage Dropdown
        setDosageDropdown();
        //  For Getting Selected Level
        act_Dosage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        //  For Setting Start Date
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MedicationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(MedicationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(MedicationActivity.this, new DatePickerDialog.OnDateSetListener() {
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


    //  Initializing Objects
    private void initView() {
        inputDiseases = findViewById(R.id.inputDiseases);
        inputMedicine = findViewById(R.id.inputMedicine);
        inputDosage = findViewById(R.id.inputDosage);

        act_Diseases = findViewById(R.id.act_Diseases);
        act_Medicine = findViewById(R.id.act_Medicine);
        act_Dosage = findViewById(R.id.act_Dosage);

        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etLastUpdated = findViewById(R.id.etLastUpdated);

        btnAdd = findViewById(R.id.btnAdd);
        pg = findViewById(R.id.medical_progress);
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


    //  Setting Medicine Dropdown
    private void setMedicineDropdown() {

        arrayAdapter_Medicine = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Medicine);
        act_Medicine.setAdapter(arrayAdapter_Medicine);
        act_Medicine.setThreshold(1);
    }


    //  Setting Dosage Dropdown
    private void setDosageDropdown() {
        arrayList_Dosage = new ArrayList<>();
        arrayList_Dosage.add("1");
        arrayList_Dosage.add("2");
        arrayList_Dosage.add("3");

        arrayAdapter_Dosage = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Dosage);
        act_Dosage.setAdapter(arrayAdapter_Dosage);
        act_Dosage.setThreshold(1);
    }


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn Add Medication
            case R.id.btnAdd:
                RegisterMedicationFieldsValidation();
                break;
        }
    }


    //  Register Medication Field's Validation
    private void RegisterMedicationFieldsValidation() {

        if (TextUtils.isEmpty(inputDiseases.getEditText().getText().toString())) {
            inputDiseases.setError("Disease can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputDosage.getEditText().getText().toString())) {
            inputDosage.setError("Dosage can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputMedicine.getEditText().getText().toString())) {
            inputMedicine.setError("Medicine can not be null");
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
        RegisterMedication();

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
                        Toast.makeText(MedicationActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to get All Medicines through AFNL
    private void getAllMedication() {
        arrayList_Medicine = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Medications/Medicines")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(AllMedication.class, new ParsedRequestListener<List<AllMedication>>() {
                    @Override
                    public void onResponse(List<AllMedication> allMedications) {
                        // do anything with response
                        for (AllMedication medication : allMedications) {
                            arrayList_Medicine.add(medication.getM_name());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(MedicationActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to Register User Diseases through AFNL
    private void RegisterMedication() {
        pg.setVisibility(View.VISIBLE);

        Medication medication = new Medication();
        medication.setU_id(id);
        medication.setD_id(disease_name_pos);
        medication.setM_id(medicine_name_pos);
        medication.setUm_dosage(Integer.parseInt(inputDosage.getEditText().getText().toString()));
        medication.setUm_StartDate(etStartDate.getText().toString());
        if (etEndDate.getText().equals(""))
            medication.setUm_EndDate("00/00/0000");
        else
            medication.setUm_EndDate(etEndDate.getText().toString());
        medication.setLastUpdated(etLastUpdated.getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Medications/AddingUserMedicines")
                .addBodyParameter(medication) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(MedicationActivity.this, "Added User Medication Successfully: ", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(MedicationActivity.this, MedicationActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(MedicationActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
    }

}