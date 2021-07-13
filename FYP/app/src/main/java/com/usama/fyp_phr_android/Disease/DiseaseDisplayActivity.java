package com.usama.fyp_phr_android.Disease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.usama.fyp_phr_android.AES.AES;
import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.Disease.Model.Disease;
import com.usama.fyp_phr_android.Disease.Model.DiseaseAdapter;
import com.usama.fyp_phr_android.R;

import java.util.ArrayList;
import java.util.List;

public class DiseaseDisplayActivity extends AppCompatActivity {

    ArrayList<Disease> diseaseArrayList;
    ArrayList<AllDisease> allDiseaseArrayList;
    AES aes;
    SharedPreferences sh;
    RecyclerView recyclerView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_display);

        initView();
        getSharedPreferences();
        getAllDisease();
        getUserDisease();
        Toast.makeText(this, "DSD Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "DSD Started", Toast.LENGTH_SHORT).show();
    }

    //  Initializing Objects
    private void initView() {
        recyclerView = findViewById(R.id.rvdDisease);
        aes = new AES();
    }


    //  Getting User Id in Shared Preferences
    private void getSharedPreferences() {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        id = sh.getInt("User Id", 0);

//        Toast.makeText(getContext(), "Id: " + id, Toast.LENGTH_SHORT).show();

        // We can then use the data
//        name.setText(s1);
//        age.setText(String.valueOf(a));

    }


    //  API Call to get All Diseases through AFNL
    private void getAllDisease() {
        allDiseaseArrayList = new ArrayList<>();

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
                            allDiseaseArrayList.add(disease);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(DiseaseDisplayActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to get User Diseases through AFNL
    private void getUserDisease() {
        diseaseArrayList = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Diseases/GetUserDiseases/{id}")
                .addPathParameter("id", "" + id)
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Disease.class, new ParsedRequestListener<List<Disease>>() {
                    @Override
                    public void onResponse(List<Disease> diseases) {
                        // do anything with response
//                        Log.d(TAG, "userList size : " + users.size());
                        for (Disease disease : diseases) {
                            diseaseArrayList.add(disease);
                        }
                        DiseaseAdapter adapter = new DiseaseAdapter(diseaseArrayList, allDiseaseArrayList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DiseaseDisplayActivity.this));
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(DiseaseDisplayActivity.this, "Error: " + anError.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

}