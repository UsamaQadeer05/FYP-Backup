package com.usama.fyp_phr_android.VitalSign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.Disease.DiseaseActivity;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.VitalSign.Model.Vitals;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class VitalSignsActivity extends AppCompatActivity {
    TextInputLayout inputTemperature,
            inputPulseRate,
            inputRespirationRate,
            inputBloodPressure,
            inputWeight,
            inputHeight;

    EditText etLastUpdated;
    Button btnAdd;
    ProgressBar pg;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs);

        initView();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        //  Getting Login User Id
        getSharedPreferences();

        //  For Setting Start Date
        etLastUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VitalSignsActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        inputTemperature = findViewById(R.id.inputTemperature);
        inputPulseRate = findViewById(R.id.inputPulseRate);
        inputRespirationRate = findViewById(R.id.inputRespirationRate);
        inputBloodPressure = findViewById(R.id.inputBloodPressure);
        inputWeight = findViewById(R.id.inputWeight);
        inputHeight = findViewById(R.id.inputHeight);

        etLastUpdated = findViewById(R.id.etLastUpdated);
        pg = findViewById(R.id.vitals_progress);
        btnAdd = findViewById(R.id.btnAdd);
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


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn Add Diseases
            case R.id.btnAdd:
                RegisterVitalsFieldsValidation();
                break;
        }
    }


    //  Register User Vitals Field's Validation
    private void RegisterVitalsFieldsValidation() {
        if (TextUtils.isEmpty(inputTemperature.getEditText().getText().toString())) {
            inputTemperature.setError("Temperature can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputPulseRate.getEditText().getText().toString())) {
            inputPulseRate.setError("Pulse Rate can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputRespirationRate.getEditText().getText().toString())) {
            inputRespirationRate.setError("Respiration Rate can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputBloodPressure.getEditText().getText().toString())) {
            inputBloodPressure.setError("Blood Pressure can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputWeight.getEditText().getText().toString())) {
            inputWeight.setError("Weight can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputHeight.getEditText().getText().toString())) {
            inputHeight.setError("Height can not be null");
            return;
        }

        if (TextUtils.isEmpty(etLastUpdated.getText().toString())) {
            etLastUpdated.setError("Last Updated Date can not be null");
            return;
        }

        //  Function to Register User Vitals
        RegisterVitals();
    }


    //  API Call to Register User Vitals through AFNL
    private void RegisterVitals() {
        pg.setVisibility(View.VISIBLE);

        Vitals vitals = new Vitals();
        vitals.setU_id(id);
        vitals.setUvi_temperature(inputTemperature.getEditText().getText().toString());
        vitals.setUvi_pulserate(inputPulseRate.getEditText().getText().toString());
        vitals.setUvi_respirationrate(inputRespirationRate.getEditText().getText().toString());
        vitals.setUvi_bloodpressure(inputBloodPressure.getEditText().getText().toString());
        vitals.setUvi_weight(inputWeight.getEditText().getText().toString());
        vitals.setUvi_height(inputHeight.getEditText().getText().toString());
        vitals.setLastUpdated(etLastUpdated.getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/VitalSigns/AddingUserVitals")
                .addBodyParameter(vitals) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(VitalSignsActivity.this, "Added User Vitals Successfully: ", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(VitalSignsActivity.this, VitalSignsActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(VitalSignsActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
    }
}



/*--------------Example Two -> Getting an user----------------*/
/*
AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAnUserDetail/{userId}")
        .addPathParameter("userId", "1")
        .setTag(this)
        .setPriority(Priority.LOW)
        .build()
        .getAsObject(User.class, new ParsedRequestListener<User>() {
@Override
public void onResponse(User user) {
        // do anything with response
        Log.d(TAG, "id : " + user.id);
        Log.d(TAG, "firstname : " + user.firstname);
        Log.d(TAG, "lastname : " + user.lastname);
        }
@Override
public void onError(ANError anError) {
        // handle error
        }
        }); */
