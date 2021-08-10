package com.usama.fyp_phr_android.Researcher;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryFragment extends Fragment {


    TextInputLayout inputCountry;
    AutoCompleteTextView act_Country;
    ArrayList<String> arrayList_Country;
    ArrayAdapter<String> arrayAdapter_Country;
    View view;
    PieChart pieChart;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CountryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CountryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountryFragment newInstance(String param1, String param2) {
        CountryFragment fragment = new CountryFragment();
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
        view = inflater.inflate(R.layout.fragment_country, container, false);

        initView();

//        setCountryDropdown();

        //  Function to Set Country Dropdown
//        setCountryDropdown();
        //  For Getting Selected Country
       /* act_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });*/
//        plotPieChart();


        return view;
    }

    //  Initializing Objects
    private void initView() {
        /*inputCountry = view.findViewById(R.id.ainputCountry);
        act_Country = view.findViewById(R.id.aact_Country);*/
        pieChart = view.findViewById(R.id.apieChart);
    }


    //  Setting Country Dropdown
//    private void setCountryDropdown() {
//        arrayList_Country = new ArrayList<>();
//        arrayList_Country.add("Pakistan");
//
//
//        arrayAdapter_Country = new ArrayAdapter<>(view.getContext(),
//                R.layout.support_simple_spinner_dropdown_item, arrayList_Country);
//        act_Country.setAdapter(arrayAdapter_Country);
//        act_Country.setThreshold(1);
//    }


    //  Setting Country Based Disease Cases Graph
    private void plotPieChart() {

    }

}