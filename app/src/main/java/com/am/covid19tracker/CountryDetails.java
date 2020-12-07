package com.am.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Date;

public class CountryDetails extends AppCompatActivity {
    TextView countrynametitle,totalcasestitle,totalrecoveredtitle,totaldeathstitle,todaycasestitle,todayrecoveredtitle,todaydeathstitle,activetitle;
    TextView countryname,totalcases,totalrecovered,totaldeaths,todaycases,todayrecovered,todaydeaths,active;
    Toolbar toolbar;
    PieChart pieChart;
    ImageView countryflag;
    String shareinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        toolbar=(Toolbar)findViewById(R.id.country_details_toolbar);
        toolbar.setTitle("Country Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pieChart=(PieChart)findViewById(R.id.country_details_piechart);

        View Country=findViewById(R.id.country_name);
        countrynametitle=Country.findViewById(R.id.status_title);
        countryname=Country.findViewById(R.id.status_details);

        View Country_flag=findViewById(R.id.country_flag);
        countryflag=(ImageView)Country_flag.findViewById(R.id.flag_icon);

        View total_cases=findViewById(R.id.country_total_cases);
        totalcasestitle=total_cases.findViewById(R.id.status_title);
        totalcases=total_cases.findViewById(R.id.status_details);

        View Total_recovered=findViewById(R.id.country_total_recovered);
        totalrecoveredtitle=Total_recovered.findViewById(R.id.status_title);
        totalrecovered=Total_recovered.findViewById(R.id.status_details);

        View Total_deaths=findViewById(R.id.country_total_deaths);
        totaldeathstitle=Total_deaths.findViewById(R.id.status_title);
        totaldeaths=Total_deaths.findViewById(R.id.status_details);


        View Active=findViewById(R.id.country_active);
        activetitle=Active.findViewById(R.id.status_title);
        active=Active.findViewById(R.id.status_details);

        View today_cases=findViewById(R.id.country_today_cases);
        todaycasestitle=today_cases.findViewById(R.id.status_title);
        todaycases=today_cases.findViewById(R.id.status_details);

        View Today_recovered=findViewById(R.id.country_today_recovered);
        todayrecoveredtitle=Today_recovered.findViewById(R.id.status_title);
        todayrecovered=Today_recovered.findViewById(R.id.status_details);

        View Today_deaths=findViewById(R.id.country_today_deaths);
        todaydeathstitle=Today_deaths.findViewById(R.id.status_title);
        todaydeaths=Today_deaths.findViewById(R.id.status_details);

        countrynametitle.setText("Country");
        totalcasestitle.setText(getString(R.string.total_cases));
        totalrecoveredtitle.setText(getString(R.string.total_recovered));
        totaldeathstitle.setText(getString(R.string.total_deaths));
        activetitle.setText(getString(R.string.active));
        todaycasestitle.setText(getString(R.string.today_cases));
        todayrecoveredtitle.setText(getString(R.string.today_recovered));
        todaydeathstitle.setText(getString(R.string.today_deaths));

        countryname.setTextColor(getResources().getColor(R.color.colorAccent));
        todaycases.setTextColor(getResources().getColor(R.color.colorOrange));
        todayrecovered.setTextColor(getResources().getColor(R.color.colorGreen));
        todaydeaths.setTextColor(getResources().getColor(R.color.colorRed));
        totalcases.setTextColor(getResources().getColor(R.color.colorOrange));
        totalrecovered.setTextColor(getResources().getColor(R.color.colorGreen));
        totaldeaths.setTextColor(getResources().getColor(R.color.colorRed));
        active.setTextColor(getResources().getColor(R.color.colorBlue));

        Intent intent=getIntent();
        if(intent.getExtras()!=null)
        {
            CountryHelper countryHelper=(CountryHelper)intent.getSerializableExtra("data");
            countryname.setText(countryHelper.getCountryname());
            Glide.with(getApplicationContext()).load(countryHelper.getFlag()).into(countryflag);
            todaycases.setText(countryHelper.getTodaycases());
            todayrecovered.setText(countryHelper.getTodayrecovred());
            todaydeaths.setText(countryHelper.getTodaydeaths());
            totalcases.setText(countryHelper.getTotalcases());
            totalrecovered.setText(countryHelper.getTotalrecovered());
            totaldeaths.setText(countryHelper.getTotaldeaths());
            active.setText(countryHelper.getActive());


            //piechat data
            pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(totalcases.getText().toString()), Color.parseColor("#FF7300")));
            pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(totalrecovered.getText().toString()), Color.parseColor("#52D726")));
            pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(totaldeaths.getText().toString()), Color.parseColor("#FF0000")));
            pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(active.getText().toString()), Color.parseColor("#007ED6")));

            pieChart.startAnimation();

            shareinfo=countrynametitle.getText().toString()+":"+countryname.getText().toString()+"\n"+
                        totalcasestitle.getText().toString()+":"+totalcases.getText().toString()+"\n"+
                        totalrecoveredtitle.getText().toString()+":"+totalrecovered.getText().toString()+"\n"+
                        totaldeathstitle.getText().toString()+":"+totaldeaths.getText().toString();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        else if(item.getItemId()==R.id.share_details)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,shareinfo);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}