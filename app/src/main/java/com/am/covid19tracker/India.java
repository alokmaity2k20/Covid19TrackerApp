package com.am.covid19tracker;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class India extends Fragment {
    View view;
    SimpleArcLoader arcLoader;
    PieChart pieChart;
    NestedScrollView nestedScrollView;
    ExtendedFloatingActionButton trackstates;
    TextView totalcasestitle,totalrecoveredtitle,totaldeathstitle,todaycasestitle,todayrecoveredtitle,todaydeathstitle,activetitle;
    TextView totalcases,totalrecovered,totaldeaths,todaycases,todayrecovered,todaydeaths,active;
    public India() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.india,container,false);
        arcLoader=view.findViewById(R.id.arcloader);
        nestedScrollView=view.findViewById(R.id.nested_scrolllview);
        pieChart=view.findViewById(R.id.piechart);
        trackstates=view.findViewById(R.id.track_states);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY>oldScrollY)
                {
                    trackstates.hide();
                }
                else
                {
                    trackstates.show();
                }
            }
        });

        trackstates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AffectedStates.class));
            }
        });
        setIndiaData();

        FetchData();

        return view;
    }

    private void setIndiaData() {
        View total_cases=view.findViewById(R.id.totalcases);
        totalcasestitle=total_cases.findViewById(R.id.status_title);
        totalcases=total_cases.findViewById(R.id.status_details);

        View Total_recovered=view.findViewById(R.id.totalrecovered);
        totalrecoveredtitle=Total_recovered.findViewById(R.id.status_title);
        totalrecovered=Total_recovered.findViewById(R.id.status_details);

        View Total_deaths=view.findViewById(R.id.totaldeaths);
        totaldeathstitle=Total_deaths.findViewById(R.id.status_title);
        totaldeaths=Total_deaths.findViewById(R.id.status_details);

        View Active=view.findViewById(R.id.active);
        activetitle=Active.findViewById(R.id.status_title);
        active=Active.findViewById(R.id.status_details);

        View today_cases=view.findViewById(R.id.todaycases);
        todaycasestitle=today_cases.findViewById(R.id.status_title);
        todaycases=today_cases.findViewById(R.id.status_details);

        View Today_recovered=view.findViewById(R.id.todayrecovered);
        todayrecoveredtitle=Today_recovered.findViewById(R.id.status_title);
        todayrecovered=Today_recovered.findViewById(R.id.status_details);

        View Today_deaths=view.findViewById(R.id.todaydeaths);
        todaydeathstitle=Today_deaths.findViewById(R.id.status_title);
        todaydeaths=Today_deaths.findViewById(R.id.status_details);


        totalcasestitle.setText(getString(R.string.total_cases));
        totalrecoveredtitle.setText(getString(R.string.total_recovered));
        totaldeathstitle.setText(getString(R.string.total_deaths));
        activetitle.setText(getString(R.string.active));
        todaycasestitle.setText(getString(R.string.today_cases));
        todayrecoveredtitle.setText(getString(R.string.today_recovered));
        todaydeathstitle.setText(getString(R.string.today_deaths));

        todaycases.setTextColor(getResources().getColor(R.color.colorOrange));
        todayrecovered.setTextColor(getResources().getColor(R.color.colorGreen));
        todaydeaths.setTextColor(getResources().getColor(R.color.colorRed));
        totalcases.setTextColor(getResources().getColor(R.color.colorOrange));
        totalrecovered.setTextColor(getResources().getColor(R.color.colorGreen));
        totaldeaths.setTextColor(getResources().getColor(R.color.colorRed));
        active.setTextColor(getResources().getColor(R.color.colorBlue));
    }


    private void FetchData() {

        String url="https://api.covidindiatracker.com/total.json";
        arcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    totalcases.setText(jsonObject.getString("confirmed"));
                    totalrecovered.setText(jsonObject.getString("recovered"));
                    totaldeaths.setText(jsonObject.getString("deaths"));
                    active.setText(jsonObject.getString("active"));
                    todaycases.setText(jsonObject.getString("cChanges"));
                    todayrecovered.setText(jsonObject.getString("rChanges"));
                    todaydeaths.setText(jsonObject.getString("dChanges"));



                    //piechat data
                    pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(totalcases.getText().toString()), Color.parseColor("#FF7300")));
                    pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(totalrecovered.getText().toString()), Color.parseColor("#52D726")));
                    pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(totaldeaths.getText().toString()), Color.parseColor("#FF0000")));
                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(active.getText().toString()), Color.parseColor("#007ED6")));

                    pieChart.startAnimation();
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    arcLoader.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arcLoader.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(request);
    }


}
