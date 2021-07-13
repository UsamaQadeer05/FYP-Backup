package com.usama.fyp_phr_android.Allergy;

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
import com.usama.fyp_phr_android.Allergy.Model.AllAllergy;
import com.usama.fyp_phr_android.Allergy.Model.Allergy;
import com.usama.fyp_phr_android.Allergy.Model.AllergyAdapter;
import com.usama.fyp_phr_android.R;

import java.util.ArrayList;
import java.util.List;

public class AllergyDisplayActivity extends AppCompatActivity {
    ArrayList<Allergy> allergyArrayList;
    ArrayList<AllAllergy> allAllergyArrayList;
    AES aes;
    SharedPreferences sh;
    RecyclerView recyclerView;
    int id;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_display);

        initView();
        getSharedPreferences();
        getAllAllergy();
        getUserAllergy();
        Toast.makeText(this, "DSA Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "DSA Started", Toast.LENGTH_SHORT).show();
    }

    //  Initializing Objects
    private void initView() {
        recyclerView = findViewById(R.id.rvdAllergy);
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

        Toast.makeText(AllergyDisplayActivity.this, "Id: " + id, Toast.LENGTH_SHORT).show();

        // We can then use the data
//        name.setText(s1);
//        age.setText(String.valueOf(a));

    }


    //  API Call to get All Allergies through AFNL
    private void getAllAllergy() {
        allAllergyArrayList = new ArrayList<>();

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
                            allAllergyArrayList.add(allergy);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(AllergyDisplayActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to get User Allergy through AFNL
    private void getUserAllergy() {
        allergyArrayList = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Allergies/GetUserAllergies/{id}")
                .addPathParameter("id", "" + id)
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Allergy.class, new ParsedRequestListener<List<Allergy>>() {
                    @Override
                    public void onResponse(List<Allergy> diseases) {
                        // do anything with response
//                        Log.d(TAG, "userList size : " + users.size());
                        for (Allergy disease : diseases) {
                            allergyArrayList.add(disease);
                        }
                        AllergyAdapter adapter = new AllergyAdapter(allAllergyArrayList, allergyArrayList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AllergyDisplayActivity.this));
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(view.getContext(), "Error: " + anError.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

}