package com.usama.fyp_phr_android.Allergy;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.usama.fyp_phr_android.AES.AES;
import com.usama.fyp_phr_android.Allergy.Model.AllAllergy;
import com.usama.fyp_phr_android.Allergy.Model.Allergy;
import com.usama.fyp_phr_android.Allergy.Model.AllergyAdapter;
import com.usama.fyp_phr_android.Disease.Model.AllDisease;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistory;
import com.usama.fyp_phr_android.SocialHistory.Model.SocialHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllergyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllergyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ArrayList<Allergy> allergyArrayList;
    ArrayList<AllAllergy> allAllergyArrayList;
    AES aes;
    SharedPreferences sh;
    RecyclerView recyclerView;
    int id;
    View view;


    public AllergyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllergyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllergyFragment newInstance(String param1, String param2) {
        AllergyFragment fragment = new AllergyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_allergy, container, false);

        initView();
        getSharedPreferences();
        getAllAllergy();
        getUserAllergy();

        return view;
    }

    //  Initializing Objects
    private void initView() {
        recyclerView = view.findViewById(R.id.rvAllergy);
        aes = new AES();
    }


    //  Getting User Id in Shared Preferences
    private void getSharedPreferences() {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        id = sh.getInt("User Id", 0);

        Toast.makeText(getContext(), "Id: " + id, Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(view.getContext(), "Some Went Wrong: " +
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
                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(view.getContext(), "Error: " + anError.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

}