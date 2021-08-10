package com.usama.fyp_phr_android.Researcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputLayout;
import com.usama.fyp_phr_android.Allergy.AllergyActivity;
import com.usama.fyp_phr_android.Allergy.Model.AllAllergy;
import com.usama.fyp_phr_android.DashboardActivity;
import com.usama.fyp_phr_android.R;
import com.usama.fyp_phr_android.User.SignInActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {
    PieChart pieChart, apieChart;
    TextInputLayout inputCity;
    AutoCompleteTextView act_City;
    ArrayList<String> arrayList_City;
    ArrayAdapter<String> arrayAdapter_City;

    ArrayList<DiseaseCases> casesArrayList;
    ArrayList<DiseaseCases> casessArrayList;

    TextInputLayout inputCountry;
    AutoCompleteTextView act_Country;
    ArrayList<String> arrayList_Country;
    ArrayAdapter<String> arrayAdapter_Country;
    String CITYNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        initView();


        //  Function to Set Country Dropdown
        setCountryDropdown();

        //  For Getting Selected Country
        getCountriesDisease();


        getCitiesDisease();

        act_Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                getCountriesDisease();
                plotCountryPieChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //  Function to Set City Dropdown
        setCityDropdown();
        act_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CITYNAME = adapterView.getItemAtPosition(i).toString();
//                getCitiesDisease();
                plotCityPieChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

//    act_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            adapterView.getItemAtPosition()
//        }
//    });
    }

    @Override
    protected void onResume() {
        super.onResume();
        act_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                plotCountryPieChart();
            }
        });

//        //  For Getting Selected City
        act_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CITYNAME = adapterView.getItemAtPosition(i).toString();
                getCitiesDisease();
                plotCityPieChart();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        act_Country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                plotCountryPieChart();
            }
        });

//        //  For Getting Selected City
        act_City.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CITYNAME = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(PieChartActivity.this, "City Name at Start: " + CITYNAME, Toast.LENGTH_SHORT).show();
                getCitiesDisease();
                plotCityPieChart();
            }
        });
    }

    //  Initializing Objects
    private void initView() {
        act_City = findViewById(R.id.acact_City);
        inputCity = findViewById(R.id.acinputCity);
        inputCountry = findViewById(R.id.ainputCountry);
        act_Country = findViewById(R.id.aact_Country);
        pieChart = findViewById(R.id.pieChart);
        apieChart = findViewById(R.id.apieChart);
//        CITYNAME = "Rawalpindi";
    }


    //  Plotting Country Wise DIsease Chart
    void plotCountryPieChart() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (DiseaseCases cases : casesArrayList
        ) {
            pieEntries.add(new PieEntry(cases.DISEASE_COUNT, cases.d_name));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Country Report");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(22f);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
//        pieChart.setCenterText("Disease Cases");
        apieChart.setDrawHoleEnabled(false);
        pieChart.animate();
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


    //  Setting City Dropdown
    private void setCityDropdown() {
        arrayList_City = new ArrayList<>();
        arrayList_City.add("Rawalpindi");
        arrayList_City.add("Lahore");
        arrayList_City.add("Faisalabad");
        arrayList_City.add("Karachi");
        arrayList_City.add("Islamabad");


        arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, arrayList_City);
        act_City.setAdapter(arrayAdapter_City);
        act_City.setThreshold(1);
    }


    //  Plotting City Wise DIsease Chart
    void plotCityPieChart() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (DiseaseCases cases : casessArrayList
        ) {
            pieEntries.add(new PieEntry(cases.DISEASE_COUNT, cases.d_name));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, CITYNAME + " Wise Report");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(22f);

        PieData pieData = new PieData(dataSet);

        apieChart.calculateOffsets();
        apieChart.getCenterCircleBox();
        apieChart.setDrawHoleEnabled(false);
        apieChart.setData(pieData);
        apieChart.getDescription().setEnabled(true);
//        apieChart.setCenterText("Disease Cases");
        apieChart.animate();
    }


    //  API Call to get Country Wise Diseases through AFNL
    private void getCountriesDisease() {
        casesArrayList = new ArrayList<>();

        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Researcher/GetCountriesDiseases")
                .addQueryParameter("limit", "3")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(DiseaseCases.class, new ParsedRequestListener<List<DiseaseCases>>() {
                    @Override
                    public void onResponse(List<DiseaseCases> allAllergies) {
                        // do anything with response
                        for (DiseaseCases allergy : allAllergies) {
                            casesArrayList.add(allergy);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        // handle error
                        Toast.makeText(PieChartActivity.this, "Some Went Wrong: " +
                                anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //  API Call to get Country Wise Diseases through AFNL
    private void getCitiesDisease() {
        casessArrayList = new ArrayList<>();

//        AndroidNetworking.get("http://10.0.2.2/PHP_FYP_API/api/Researcher/GetCitiesDiseases/{CITYNAME}")
//                .addPathParameter("CITYNAME", CITYNAME)
//                .addQueryParameter("limit", "3")
//                .setTag(this)
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsObjectList(DiseaseCases.class, new ParsedRequestListener<List<DiseaseCases>>() {
//                    @Override
//                    public void onResponse(List<DiseaseCases> allAllergies) {
//                        // do anything with response
//                        for (DiseaseCases allergy : allAllergies) {
//                            casessArrayList.add(allergy);
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        // handle error
//                        Toast.makeText(PieChartActivity.this, "Some Went Wrong in Cities: " +
//                                anError.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

        DiseaseCases cases = new DiseaseCases(CITYNAME, 1);
        AndroidNetworking.post("http://10.0.2.2/PHP_FYP_API/api/Researcher/GetCitiesDisease")
                .addBodyParameter(cases) // posting java object
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        //try {
//                            id = response.getInt("u_id");
//                            name = response.getString("u_name");
//                            name = aes.decryption(name);

                        try {
                            for (int i = 0; i < response.length(); i++) {

//                                String d_name;
//                                int DISEASE_COUNT;
                                int DISEASE_COUNT = response.getJSONObject(i).getInt("DISEASE_COUNT");
                                String d_name = response.getJSONObject(i).getString("d_name");

                                casessArrayList.add(new DiseaseCases(d_name, DISEASE_COUNT));
                            }
                            plotCityPieChart();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(PieChartActivity.this, "Login Successfully: " + response, Toast.LENGTH_SHORT).show();
//                            pg.setVisibility(View.INVISIBLE);
//                            startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
////                            pg.setVisibility(View.INVISIBLE);
//                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Toast.makeText(PieChartActivity.this, "Some went wrong new: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}