package com.usama.fyp_phr_android.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.AES.AES;
import com.usama.fyp_phr_android.Disease.DiseaseActivity;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.Model.User;
import com.usama.fyp_phr_android.Vaccination.VaccinationActivity;
import com.usama.fyp_phr_android.VitalSign.VitalSignsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.MIN_VALUE;

public class SignInActivity extends AppCompatActivity {
    TextInputLayout inputCNIC;
    TextInputLayout inputPIN;
    TextView tvforgotPassword;
    TextView tvRegister;
    Button btnLogin;
    String CNIC, PIN, enCNIC, enPIN;
    AES aes = new AES();
    SharedPreferences sh;
    ProgressBar pg;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        AndroidNetworking.initialize(getApplicationContext());
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        setSharedPreferences();
    }


    //  Initializing Objects
    private void initView() {
        inputCNIC = findViewById(R.id.inputCNIC);
        inputPIN = findViewById(R.id.inputPIN);
        tvforgotPassword = findViewById(R.id.tvforgotPassword);
        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);
        pg = findViewById(R.id.login_progress);
    }


    //  Setting User Id in Shared Preferences
    private void setSharedPreferences() {
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Storing the key and its value as the data fetched from edittext
        myEdit.putInt("User Id", id);

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply();
        myEdit.commit();
    }


    //  Onclick Listeners
    public void onClick(View view) {
        switch (view.getId()) {

            //  Btn Login
            case R.id.btnLogin:
                LoginFieldsValidation();
                break;

            //  Forget Password
            case R.id.tvforgotPassword:
                break;

            //  Registering User
            case R.id.tvRegister:
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                break;
        }
    }


    //  Login User Field's Validation
    private void LoginFieldsValidation() {
        CNIC = inputCNIC.getEditText().getText().toString();
        PIN = inputPIN.getEditText().getText().toString();

        if (TextUtils.isEmpty(CNIC)) {
            inputCNIC.setError("CNIC can not be null");
            return;
        }

        if (TextUtils.isEmpty(PIN)) {
            inputPIN.setError("PIN can not be null");
            return;
        }

        /*if (PIN.length() <= 3) {
            // do something
            Toast.makeText(this, "Minimum Length", Toast.LENGTH_SHORT).show();
        }*/

        enCNIC = aes.encryption(CNIC);
        enPIN = aes.encryption(PIN);

        //  Name: Usama Qadeer   ->    802CA0A143C65E1D70AD6884C9905D83
        //  CNIC: 61101-1234567-7   ->    96BA503AFA8DF312E7350D6A184F42CE
        //  PIN: 1122   ->    33345A6A6C150792C847FF0391054076

        //  Name: Sufyan Qadeer   ->    DFEB262652081E4E52EBF1AD23098EB4
        //  CNIC: 61101-1234561-1   ->    A1D8BEDAAF9B0B63D322128D8034B0A1
        //  PIN: 8787   ->    109EAB8B10E5436EF98AB623041CE3CE


        LoginUser();
    }


    // API Call to Validate Login User through AFNL
    private void LoginUser() {
        pg.setVisibility(View.VISIBLE);

        User user = new User();
        user.setU_password(enPIN);
        user.setU_cnic(enCNIC);

        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Users/LoginUser")
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
                            Toast.makeText(SignInActivity.this, "Login Successfully: " + id, Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(SignInActivity.this, VaccinationActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pg.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(SignInActivity.this, "Some went wrong: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}