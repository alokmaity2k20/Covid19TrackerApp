package com.am.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class StateDetails extends AppCompatActivity {
    TextView statenametitle,totalcasestitle,totalrecoveredtitle,totaldeathstitle,todaycasestitle,todayrecoveredtitle,todaydeathstitle,activetitle,todayactivetitle;
    TextView statename,totalcases,totalrecovered,totaldeaths,todaycases,todayrecovered,todaydeaths,active,todayactive;
    Toolbar toolbar;
    String shareinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_details);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("State Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        View State=findViewById(R.id.state_name);
        statenametitle=State.findViewById(R.id.status_title);
        statename=State.findViewById(R.id.status_details);

        View total_cases=findViewById(R.id.state_total_cases);
        totalcasestitle=total_cases.findViewById(R.id.status_title);
        totalcases=total_cases.findViewById(R.id.status_details);

        View Total_recovered=findViewById(R.id.state_total_recovered);
        totalrecoveredtitle=Total_recovered.findViewById(R.id.status_title);
        totalrecovered=Total_recovered.findViewById(R.id.status_details);

        View Total_deaths=findViewById(R.id.state_total_deaths);
        totaldeathstitle=Total_deaths.findViewById(R.id.status_title);
        totaldeaths=Total_deaths.findViewById(R.id.status_details);

        View Today_active=findViewById(R.id.state_today_active);
        todayactivetitle=Today_active.findViewById(R.id.status_title);
        todayactive=Today_active.findViewById(R.id.status_details);

        View Active=findViewById(R.id.state_active);
        activetitle=Active.findViewById(R.id.status_title);
        active=Active.findViewById(R.id.status_details);

        View today_cases=findViewById(R.id.state_today_cases);
        todaycasestitle=today_cases.findViewById(R.id.status_title);
        todaycases=today_cases.findViewById(R.id.status_details);

        View Today_recovered=findViewById(R.id.state_today_recovered);
        todayrecoveredtitle=Today_recovered.findViewById(R.id.status_title);
        todayrecovered=Today_recovered.findViewById(R.id.status_details);

        View Today_deaths=findViewById(R.id.state_today_deaths);
        todaydeathstitle=Today_deaths.findViewById(R.id.status_title);
        todaydeaths=Today_deaths.findViewById(R.id.status_details);

        statenametitle.setText(getString(R.string.state_name));
        totalcasestitle.setText(getString(R.string.total_cases));
        totalrecoveredtitle.setText(getString(R.string.total_recovered));
        totaldeathstitle.setText(getString(R.string.total_deaths));
        activetitle.setText(getString(R.string.active));
        todayactivetitle.setText(getString(R.string.today_active));
        todaycasestitle.setText(getString(R.string.today_cases));
        todayrecoveredtitle.setText(getString(R.string.today_recovered));
        todaydeathstitle.setText(getString(R.string.today_deaths));

        statename.setTextColor(getResources().getColor(R.color.colorAccent));
        todaycases.setTextColor(getResources().getColor(R.color.colorOrange));
        todayrecovered.setTextColor(getResources().getColor(R.color.colorGreen));
        todaydeaths.setTextColor(getResources().getColor(R.color.colorRed));
        totalcases.setTextColor(getResources().getColor(R.color.colorOrange));
        totalrecovered.setTextColor(getResources().getColor(R.color.colorGreen));
        totaldeaths.setTextColor(getResources().getColor(R.color.colorRed));
        active.setTextColor(getResources().getColor(R.color.colorBlue));
        todayactive.setTextColor(getResources().getColor(R.color.colorBlue));


        Intent intent=getIntent();
        if(intent.getExtras()!=null)
        {
            StateHelper stateHelper=(StateHelper)intent.getSerializableExtra("data");
            statename.setText(stateHelper.getStatename());
            todaycases.setText(stateHelper.getTodaycases());
            todayrecovered.setText(stateHelper.getTodayrecovred());
            todaydeaths.setText(stateHelper.getTodaydeaths());
            totalcases.setText(stateHelper.getTotalcases());
            totalrecovered.setText(stateHelper.getTotalrecovered());
            totaldeaths.setText(stateHelper.getTotaldeaths());
            active.setText(stateHelper.getActive());
            active.setText(stateHelper.getTodayactive());

            shareinfo=statenametitle.getText().toString()+":"+statename.getText().toString()+"\n"+
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