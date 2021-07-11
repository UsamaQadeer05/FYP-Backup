package com.usama.fyp_phr_android.User;

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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.AES.AES;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.Model.User;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SignUpActivity extends AppCompatActivity {
    String enCNIC, enPIN, enName;
    AES aes = new AES();
    User user;
    TextInputLayout inputName,
            inputCNIC,
            inputPIN,
            inputGender,
            inputCountry,
            inputProvince,
            inputCity,
            inputWater,
            inputArea,
            inputHome,
            inputFood,
            inputVentilation;

    AutoCompleteTextView act_Gender,
            act_Country,
            act_Province,
            act_City,
            act_Water,
            act_Area,
            act_Home,
            act_Food,
            act_Ventilation;

    ArrayList<String> arrayList_Gender,
            arrayList_Country,
            arrayList_Province,
            arrayList_City,
            arrayList_Water,
            arrayList_Area,
            arrayList_Home,
            arrayList_Food,
            arrayList_Ventilation;

    ArrayAdapter<String> arrayAdapter_Gender,
            arrayAdapter_Country,
            arrayAdapter_Province,
            arrayAdapter_City,
            arrayAdapter_Water,
            arrayAdapter_Area,
            arrayAdapter_Home,
            arrayAdapter_Food,
            arrayAdapter_Ventilation;

    EditText etDOB;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

//        Toast.makeText(this, "YEAR: " + year + " Month: " + month + " Day: " + day, Toast.LENGTH_SHORT).show();

        //  Function to Set Gender Dropdown
        setGenderDropdown();
        //  For Getting Selected Gender
        act_Gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Country Dropdown
        setCountryDropdown();
        //  For Getting Selected Country
        act_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Province Dropdown
        setProvinceDropdown();
        //  For Getting Selected Province
        act_Province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set City Dropdown
        setCityDropdown();
        //  For Getting Selected City
        act_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Water Dropdown
        setWaterDropdown();
        //  For Getting Selected Water
        act_Water.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Area Dropdown
        setAreaDropdown();
        //  For Getting Selected Area
        act_Area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Home Dropdown
        setHomeDropdown();
        //  For Getting Selected Home
        act_Home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Food Dropdown
        setFoodDropdown();
        //  For Getting Selected Food
        act_Food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  Function to Set Ventilation Dropdown
        setVentilationDropdown();
        //  For Getting Selected Ventilation
        act_Ventilation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });


        //  For Setting DOB
        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
//                        calendar.set(year, month, day);
//                        calendar.setTime();
                        String date = day + "/" + month + "/" + year;
                        etDOB.setText(date);

//                        String dateString = String.format("%02d/%02d/%d", day, month, year);
//                        etDOB.setText(dateString);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //  Setting Gender Dropdown
    private void setGenderDropdown() {
        arrayList_Gender = new ArrayList<>();
        arrayList_Gender.add("Male");
        arrayList_Gender.add("Female");
        arrayList_Gender.add("Other");

        arrayAdapter_Gender = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Gender);
        act_Gender.setAdapter(arrayAdapter_Gender);
        act_Gender.setThreshold(1);
    }


    //  Setting Country Dropdown
    private void setCountryDropdown() {
        arrayList_Country = new ArrayList<>();
        arrayList_Country.add("Pakistan");


        arrayAdapter_Country = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Country);
        act_Country.setAdapter(arrayAdapter_Country);
        act_Country.setThreshold(1);
    }


    //  Setting Province Dropdown
    private void setProvinceDropdown() {
        arrayList_Province = new ArrayList<>();
        arrayList_Province.add("Punjab");
        arrayList_Province.add("Sindh");
        arrayList_Province.add("KPK");
        arrayList_Province.add("Balochistan");
        arrayList_Province.add("Gilgit Baltistan");
        arrayList_Province.add("Azad Kashmir");
        arrayList_Province.add("Islamabad ICT");

        arrayAdapter_Province = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Province);
        act_Province.setAdapter(arrayAdapter_Province);
        act_Province.setThreshold(1);
    }


    //  Setting City Dropdown
    private void setCityDropdown() {
        arrayList_City = new ArrayList<>();
        arrayList_City.add("Islamabad");
        arrayList_City.add("Lahore");
        arrayList_City.add("Karachi");
        arrayList_City.add("Peshawer");
        arrayList_City.add("Quetta");
        arrayList_City.add("Gilgit");
        arrayList_City.add("Muzaffarabad");

        arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_City);
        act_City.setAdapter(arrayAdapter_City);
        act_City.setThreshold(1);
    }


    //  Setting Water Dropdown
    private void setWaterDropdown() {
        arrayList_Water = new ArrayList<>();
        arrayList_Water.add("Filtered");
        arrayList_Water.add("Mineral");
        arrayList_Water.add("Tap");

        arrayAdapter_Water = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Water);
        act_Water.setAdapter(arrayAdapter_Water);
        act_Water.setThreshold(1);
    }


    //  Setting Area Dropdown
    private void setAreaDropdown() {
        arrayList_Area = new ArrayList<>();
        arrayList_Area.add("Rural");
        arrayList_Area.add("Urban");

        arrayAdapter_Area = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Area);
        act_Area.setAdapter(arrayAdapter_Area);
        act_Area.setThreshold(1);
    }


    //  Setting Home Dropdown
    private void setHomeDropdown() {
        arrayList_Home = new ArrayList<>();
        arrayList_Home.add("Personal");
        arrayList_Home.add("Rent");
        arrayList_Home.add("Apartment");

        arrayAdapter_Home = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Home);
        act_Home.setAdapter(arrayAdapter_Home);
        act_Home.setThreshold(1);
    }


    //  Setting Food Dropdown
    private void setFoodDropdown() {
        arrayList_Food = new ArrayList<>();
        arrayList_Food.add("Home");
        arrayList_Food.add("Outside");

        arrayAdapter_Food = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Food);
        act_Food.setAdapter(arrayAdapter_Food);
        act_Food.setThreshold(1);
    }


    //  Setting Ventilation Dropdown
    private void setVentilationDropdown() {
        arrayList_Ventilation = new ArrayList<>();
        arrayList_Ventilation.add("Open Air");
        arrayList_Ventilation.add("Air Condition");
        arrayList_Ventilation.add("Centerialized AC");

        arrayAdapter_Ventilation = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Ventilation);
        act_Ventilation.setAdapter(arrayAdapter_Ventilation);
        act_Ventilation.setThreshold(1);
    }


    //  Intializing Objects
    private void initView() {
        inputName = findViewById(R.id.inputName);
        inputCNIC = findViewById(R.id.inputCNIC);
        inputPIN = findViewById(R.id.inputPIN);

        inputGender = findViewById(R.id.inputGender);
        act_Gender = findViewById(R.id.act_Gender);
        inputCountry = findViewById(R.id.inputCountry);
        act_Country = findViewById(R.id.act_Country);
        inputProvince = findViewById(R.id.inputProvince);
        act_Province = findViewById(R.id.act_Province);
        inputCity = findViewById(R.id.inputCity);
        act_City = findViewById(R.id.act_City);
        inputWater = findViewById(R.id.inputWater);
        act_Water = findViewById(R.id.act_Water);
        inputArea = findViewById(R.id.inputArea);
        act_Area = findViewById(R.id.act_Area);
        inputHome = findViewById(R.id.inputHome);
        act_Home = findViewById(R.id.act_Home);
        inputFood = findViewById(R.id.inputFood);
        act_Food = findViewById(R.id.act_Food);
        inputVentilation = findViewById(R.id.inputVentilation);
        act_Ventilation = findViewById(R.id.act_Ventilation);

        btnRegister = findViewById(R.id.btnRegister);
        etDOB = findViewById(R.id.etDOB);
    }


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn SignUp
            case R.id.btnRegister:
                RegisterFieldsValidation();
                break;

            //  Already Have an account
            case R.id.tvLogin:
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                break;
        }
    }


    //  Register User Field's Validation
    private void RegisterFieldsValidation() {
        enCNIC = inputCNIC.getEditText().getText().toString();
        enPIN = inputPIN.getEditText().getText().toString();
        enName = inputName.getEditText().getText().toString();

        if (TextUtils.isEmpty(inputName.getEditText().getText().toString())) {
            inputName.setError("Name can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputCNIC.getEditText().getText().toString())) {
            inputCNIC.setError("CNIC can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputGender.getEditText().getText().toString())) {
            inputGender.setError("Gender can not be null");
            return;
        }

        if (TextUtils.isEmpty(etDOB.getText().toString())) {
            etDOB.setError("DOB can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputCountry.getEditText().getText().toString())) {
            inputCountry.setError("Country can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputProvince.getEditText().getText().toString())) {
            inputProvince.setError("Province can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputCity.getEditText().getText().toString())) {
            inputCity.setError("City can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputPIN.getEditText().getText().toString())) {
            inputPIN.setError("PIN can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputWater.getEditText().getText().toString())) {
            inputWater.setError("Water can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputArea.getEditText().getText().toString())) {
            inputArea.setError("Area can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputHome.getEditText().getText().toString())) {
            inputHome.setError("Home can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputFood.getEditText().getText().toString())) {
            inputFood.setError("Food can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputVentilation.getEditText().getText().toString())) {
            inputVentilation.setError("Ventilation can not be null");
            return;
        }


//        if (PIN.length() <= 3) {
//            // do somthing
//            Toast.makeText(this, "Minimum Length", Toast.LENGTH_SHORT).show();
//        }

        enCNIC = aes.encryption(enCNIC);
        enPIN = aes.encryption(enPIN);
        enName = aes.encryption(enName);

        //  Function to Register User
        RegisterUser();
    }


    //  API Call to Register User through AFNL
    private void RegisterUser() {
        getSharedPreferences();
    }

    //  Getting User Id in Shared Preferences
    private void getSharedPreferences() {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        int a = sh.getInt("User Id", 0);

        Toast.makeText(this, "Id: " + a, Toast.LENGTH_SHORT).show();

        // We can then use the data
//        name.setText(s1);
//        age.setText(String.valueOf(a));

    }

}