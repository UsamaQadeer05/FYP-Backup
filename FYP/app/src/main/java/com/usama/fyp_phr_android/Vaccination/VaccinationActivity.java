package com.usama.fyp_phr_android.Vaccination;

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
import com.usama.fyp_phr_android.Vaccination.Model.AllVaccination;
import com.usama.fyp_phr_android.Vaccination.Model.Vaccination;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VaccinationActivity extends AppCompatActivity {

    TextInputLayout inputVaccination, inputDosage;
    AutoCompleteTextView act_Vaccination;
    EditText etInjectedDate, etLastUpdated;
    Button btnAdd;
    ProgressBar pg;
    int id, vaccination_name_pos;

    ArrayList<String> arrayList_Vaccination;
    ArrayAdapter<String> arrayAdapter_Vaccination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        initView();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        //  Function to Get All Vaccination
        getAllVaccination();

        //  Function to Set Disease Dropdown
        setVaccinationDropdown();

        //  Getting Login User Id
        getSharedPreferences();

        //  For Getting Selected Country
        act_Vaccination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vaccination_name_pos = i + 1;
            }
        });

        //  For Setting Injected Date
        etInjectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VaccinationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
                        String date = day + "/" + month + "/" + year;
                        etInjectedDate.setText(date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

        //  For Setting Last Updated Date
        etLastUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VaccinationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        inputVaccination = findViewById(R.id.inputVaccination);
        inputDosage = findViewById(R.id.inputDosage);
        act_Vaccination = findViewById(R.id.act_Vaccination);
        etInjectedDate = findViewById(R.id.etInjectedDate);
        etLastUpdated = findViewById(R.id.etLastUpdated);
        btnAdd = findViewById(R.id.btnAdd);
        pg = findViewById(R.id.vac_progress);
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
    private void setVaccinationDropdown() {
        arrayAdapter_Vaccination = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Vaccination);
        act_Vaccination.setAdapter(arrayAdapter_Vaccination);
        act_Vaccination.setThreshold(1);
    }


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn Add Vaccination
            case R.id.btnAdd:
                RegisterVaccinationFieldsValidation();
                break;
        }
    }


    //  Register Disease Field's Validation
    private void RegisterVaccinationFieldsValidation() {

        if (TextUtils.isEmpty(inputVaccination.getEditText().getText().toString())) {
            inputVaccination.setError("Vaccination can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputDosage.getEditText().getText().toString())) {
            inputDosage.setError("Dosage can not be null");
            return;
        }

        if (TextUtils.isEmpty(etInjectedDate.getText().toString())) {
            etInjectedDate.setError("Injected Date can not be null");
            return;
        }

        if (TextUtils.isEmpty(etLastUpdated.getText().toString())) {
            etLastUpdated.setError("Last Updated Date can not be null");
            return;
        }

        //  Function to Register User Vaccination
        RegisterVaccination();

    }


    //  API Call to get All Diseases through AFNL
    private void getAllVaccination() {
        arrayList_Vaccination = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Vaccinations/Vaccinations")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(AllVaccination.class, new ParsedRequestListener<List<AllVaccination>>() {
                    @Override
                    public void onResponse(List<AllVaccination> allVaccinations) {
                        // do anything with response
                        for (AllVaccination vaccination : allVaccinations) {
                            arrayList_Vaccination.add(vaccination.getV_name());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(VaccinationActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to Register User Diseases through AFNL
    private void RegisterVaccination() {
        pg.setVisibility(View.VISIBLE);

        Vaccination vaccination = new Vaccination();
        vaccination.setU_id(id);
        vaccination.setV_id(vaccination_name_pos);
        vaccination.setUv_dosage(inputDosage.getEditText().getText().toString());
        vaccination.setUv_injecteddate(etInjectedDate.getText().toString());
        vaccination.setLastUpdated(etLastUpdated.getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Vaccinations/AddingUserVaccinations")
                .addBodyParameter(vaccination) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(VaccinationActivity.this, "Added User Vaccination Successfully: ", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(VaccinationActivity.this, VaccinationActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(VaccinationActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
    }

}