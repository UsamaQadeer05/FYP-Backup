package com.usama.fyp_phr_android.FamilyMember;

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
import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.AES.AES;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.Model.User;
import com.usama.fyp_phr_android.User.SignInActivity;
import com.usama.fyp_phr_android.User.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class FamilyMemberActivity extends AppCompatActivity {
    String enCNIC, enPIN, enName;
    AES aes = new AES();
    User user;
    ProgressBar pg;
    int id, shid;
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
            inputVentilation,
            inputRelation,
            inputAsRelation;

    AutoCompleteTextView act_Gender,
            act_Country,
            act_Province,
            act_City,
            act_Water,
            act_Area,
            act_Home,
            act_Food,
            act_Ventilation,
            act_Relation,
            act_AsRelation;

    ArrayList<String> arrayList_Gender,
            arrayList_Country,
            arrayList_Province,
            arrayList_City,
            arrayList_Water,
            arrayList_Area,
            arrayList_Home,
            arrayList_Food,
            arrayList_Ventilation,
            arrayList_Relation,
            arrayList_AsRelation;

    ArrayAdapter<String> arrayAdapter_Gender,
            arrayAdapter_Country,
            arrayAdapter_Province,
            arrayAdapter_City,
            arrayAdapter_Water,
            arrayAdapter_Area,
            arrayAdapter_Home,
            arrayAdapter_Food,
            arrayAdapter_Ventilation,
            arrayAdapter_Relation,
            arrayAdapter_AsRelation;

    EditText etDOB, etACD;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member);

        initView();

        getSharedPreferences();

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
            }
        });


        //  Function to Set Country Dropdown
        setCountryDropdown();
        //  For Getting Selected Country
        act_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set Province Dropdown
        setProvinceDropdown();
        //  For Getting Selected Province
        act_Province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set City Dropdown
        setCityDropdown();
        //  For Getting Selected City
        act_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set Water Dropdown
        setWaterDropdown();
        //  For Getting Selected Water
        act_Water.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set Area Dropdown
        setAreaDropdown();
        //  For Getting Selected Area
        act_Area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set Home Dropdown
        setHomeDropdown();
        //  For Getting Selected Home
        act_Home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set Food Dropdown
        setFoodDropdown();
        //  For Getting Selected Food
        act_Food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  Function to Set Ventilation Dropdown
        setVentilationDropdown();
        //  For Getting Selected Ventilation
        act_Ventilation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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


        //  Function to Set As Relation Dropdown
        setAsRelationDropdown();
        //  For Getting Selected As Relation
        act_AsRelation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });


        //  For Setting DOB
        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FamilyMemberActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
                        String date = day + "/" + month + "/" + year;
                        etDOB.setText(date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });


        //  For Setting ACD
        etACD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FamilyMemberActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month += 1;
                        String date = day + "/" + month + "/" + year;
                        etACD.setText(date);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });

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
        inputRelation = findViewById(R.id.inputRelation);
        act_Relation = findViewById(R.id.act_Relation);
        inputAsRelation = findViewById(R.id.inputASRelation);
        act_AsRelation = findViewById(R.id.act_ASRelation);

        pg = findViewById(R.id.login_progress);
        btnRegister = findViewById(R.id.btnRegister);
        etDOB = findViewById(R.id.etDOB);
        etACD = findViewById(R.id.etACD);
    }


    //  onClick Listeners
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Toast.makeText(this, "Family Member", Toast.LENGTH_SHORT).show();
                MembersFieldsValidation();
                break;
        }
    }


    //  Getting User Id in Shared Preferences
    private void getSharedPreferences() {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        shid = sh.getInt("User Id", 0);

        Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();

        // We can then use the data
//        name.setText(s1);
//        age.setText(String.valueOf(a));

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
        arrayList_Food.add("Mixed");
        arrayList_Food.add("Fast");
        arrayList_Food.add("Restaurant");

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


    //  Setting Relation Dropdown
    private void setRelationDropdown() {
        arrayList_Relation = new ArrayList<>();
        arrayList_Relation.add("Father");
        arrayList_Relation.add("Mother");
        arrayList_Relation.add("Son");
        arrayList_Relation.add("Daughter");
        arrayList_Relation.add("Brother");
        arrayList_Relation.add("Sister");

        arrayAdapter_Relation = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_Relation);
        act_Relation.setAdapter(arrayAdapter_Relation);
        act_Relation.setThreshold(1);
    }


    //  Setting As Relation Dropdown
    private void setAsRelationDropdown() {
        arrayList_AsRelation = new ArrayList<>();
        arrayList_AsRelation.add("Father");
        arrayList_AsRelation.add("Mother");
        arrayList_AsRelation.add("Son");
        arrayList_AsRelation.add("Daughter");
        arrayList_AsRelation.add("Brother");
        arrayList_AsRelation.add("Sister");

        arrayAdapter_AsRelation = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_AsRelation);
        act_AsRelation.setAdapter(arrayAdapter_AsRelation);
        act_AsRelation.setThreshold(1);
    }


    //  Register Family Member Field's Validation
    private void MembersFieldsValidation() {
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

        if (TextUtils.isEmpty(inputRelation.getEditText().getText().toString())) {
            inputRelation.setError("Relation can not be null");
            return;
        }

        if (TextUtils.isEmpty(inputAsRelation.getEditText().getText().toString())) {
            inputAsRelation.setError("As Relation can not be null");
            return;
        }

        if (TextUtils.isEmpty(etACD.getText().toString())) {
            etACD.setError("ACD can not be null");
            return;
        }


//        if (PIN.length() <= 3) {
//            // do somthing
//            Toast.makeText(this, "Minimum Length", Toast.LENGTH_SHORT).show();
//        }

        enCNIC = aes.encryption(enCNIC);
        enPIN = aes.encryption(enPIN);
        enName = aes.encryption(enName);

        Toast.makeText(this, "Name: " + enName +
                        " CNIC: " + enCNIC +
                        " Gender: " + inputGender.getEditText().getText() +
                        " DOB: " + etDOB.getText() +
                        " Country: " + inputCountry.getEditText().getText() +
                        " City: " + inputCity.getEditText().getText() +
                        " Province: " + inputProvince.getEditText().getText() +
                        " PIN: " + inputPIN.getEditText().getText() +
                        " Water: " + inputWater.getEditText().getText() +
                        " Area: " + inputArea.getEditText().getText() +
                        " Home: " + inputHome.getEditText().getText() +
                        " Food: " + inputFood.getEditText().getText() +
                        " Ventilation: " + inputVentilation.getEditText().getText() +
                        " Relation: " + inputRelation.getEditText().getText() +
                        " As Relation: " + inputAsRelation.getEditText().getText()
                , Toast.LENGTH_SHORT).show();

        //  Function to Register Family Member
//        RegisterMember();
    }


    //  API Call to Register Family Member through AFNL
    private void RegisterMember() {
//        getSharedPreferences();
        pg.setVisibility(View.VISIBLE);

        User user = new User();
        user.setU_name(enName);
        user.setU_cnic(enCNIC);
        user.setU_gender(inputGender.getEditText().getText().toString());
        user.setU_dob(etDOB.getText().toString());
        user.setU_country(inputCountry.getEditText().getText().toString());
        user.setU_province(inputProvince.getEditText().getText().toString());
        user.setU_city(inputCity.getEditText().getText().toString());
        user.setU_password(enPIN);
        user.setU_waterfac(inputWater.getEditText().getText().toString());
        user.setU_area(inputArea.getEditText().getText().toString());
        user.setU_food(inputFood.getEditText().getText().toString());
        user.setU_ventilation(inputVentilation.getEditText().getText().toString());


        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Users/RegisterUser")
                .addBodyParameter(user) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            id = response.getInt("u_id");
                            Toast.makeText(FamilyMemberActivity.this, "Registered Successfully: " + response.toString(), Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(FamilyMemberActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}