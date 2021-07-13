package com.usama.fyp_phr_android.Allergy;

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
import com.usama.fyp_phr_android.Allergy.Model.AllAllergy;
import com.usama.fyp_phr_android.Allergy.Model.Allergy;
import com.usama.fyp_phr_android.Disease.DiseaseActivity;
import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AllergyActivity extends AppCompatActivity {
    TextInputLayout inputAllergy, inputLevel;
    AutoCompleteTextView act_Allergy, act_Level;
    ArrayList<String> arrayList_Allergy, arrayList_Level;
    ArrayAdapter<String> arrayAdapter_Allergy, arrayAdapter_Level;
    EditText etStartDate, etLastUpdated;
    Button btnAdd, btnView;
    ProgressBar pg;
    int id, allergy_name_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy);

        initView();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        //  Function to Get All Allergy
        getAllAllergy();

        //  Function to Set Allergy Dropdown
        setAllergyDropdown();

        //  Getting Login User Id
        getSharedPreferences();

        //  For Getting Selected Allergy
        act_Allergy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                disease_name_pos = adapterView.getSelectedItemPosition();
                allergy_name_pos = i + 1;
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(AllergyActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        //  For Setting Last Updated Date
        etLastUpdated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AllergyActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        inputAllergy = findViewById(R.id.inputAllergy);
        inputLevel = findViewById(R.id.inputLevel);

        act_Allergy = findViewById(R.id.act_Allergy);
        act_Level = findViewById(R.id.act_Level);

        etStartDate = findViewById(R.id.etStartDate);
        etLastUpdated = findViewById(R.id.etLastUpdated);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        pg = findViewById(R.id.allergy_progress);
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


    //  Setting Allergy Dropdown
    private void setAllergyDropdown() {
        arrayAdapter_Allergy = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Allergy);
        act_Allergy.setAdapter(arrayAdapter_Allergy);
        act_Allergy.setThreshold(1);
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

            //  Btn Add Allergies
            case R.id.btnAdd:
                RegisterAllergyFieldsValidation();
                break;

            //  Btn View Allergies
            case R.id.btnView:
                startActivity(new Intent(AllergyActivity.this, AllergyDisplayActivity.class));
                break;
        }
    }


    //  Register Allergies Field's Validation
    private void RegisterAllergyFieldsValidation() {

        if (TextUtils.isEmpty(inputAllergy.getEditText().getText().toString())) {
            inputAllergy.setError("Allergy can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputLevel.getEditText().getText().toString())) {
            inputLevel.setError("Level can not be null");
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

        //  Function to Register User Allergy
        RegisterAllergy();

    }


    //  API Call to get All Allergies through AFNL
    private void getAllAllergy() {
        arrayList_Allergy = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Allergies/Allergies")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(AllAllergy.class, new ParsedRequestListener<List<AllAllergy>>() {
                    @Override
                    public void onResponse(List<AllAllergy> allAllergies) {
                        // do anything with response
                        for (AllAllergy allergy : allAllergies) {
                            arrayList_Allergy.add(allergy.getA_name());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(AllergyActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to Register User Allergies through AFNL
    private void RegisterAllergy() {
        pg.setVisibility(View.VISIBLE);

        Allergy allergy = new Allergy();
        allergy.setU_id(id);
        allergy.setA_id(allergy_name_pos);
        allergy.setUa_Level(inputLevel.getEditText().getText().toString());
        allergy.setUa_StartDate(etStartDate.getText().toString());
        allergy.setLastUpdated(etLastUpdated.getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Allergies/AddingUserAllergies")
                .addBodyParameter(allergy) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(AllergyActivity.this, "Added User Disease Successfully: ", Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(AllergyActivity.this, AllergyActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(AllergyActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        pg.setVisibility(View.INVISIBLE);
                    }
                });
    }
}