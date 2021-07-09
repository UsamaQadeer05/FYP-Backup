package com.usama.fyp_phr_android.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout etGender;
    AutoCompleteTextView act_Gender;
    ArrayList<String> arrayList_Gender;
    ArrayAdapter<String> arrayAdapter_Gender;
    EditText etDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        act_Gender = findViewById(R.id.act_Gender);
        etGender = findViewById(R.id.inputGender);
        etDOB = findViewById(R.id.etDOB);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        arrayList_Gender = new ArrayList<>();
        arrayList_Gender.add("Male");
        arrayList_Gender.add("Female");
        arrayList_Gender.add("Other");

        arrayAdapter_Gender = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Gender);
        act_Gender.setAdapter(arrayAdapter_Gender);
        act_Gender.setThreshold(1);

        /*  For Gender Dropdown*/
        act_Gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignUpActivity.this, "Item Click " + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });

        /*  For DOB */
        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    }
}