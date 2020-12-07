package com.am.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedStates extends AppCompatActivity implements StateAdapter.SelectedState{

    Toolbar toolbar;
    RecyclerView recyclerView;
    SimpleArcLoader arcLoader;
    List<StateHelper> stateHelperList=new ArrayList<>();
    StateHelper stateHelper;
    StateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_states);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Affected States");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        arcLoader=(SimpleArcLoader)findViewById(R.id.state_list_acrloader);
        recyclerView=(RecyclerView)findViewById(R.id.state_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        GetRecycleViewData();
    }

    private void GetRecycleViewData() {
        String url="https://api.covidindiatracker.com/state_data.json";
        arcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String statename=jsonObject.getString("state");
                        String totalcases=jsonObject.getString("confirmed");
                        String totalrecovered=jsonObject.getString("recovered");
                        String totaldeaths=jsonObject.getString("deaths");
                        String active=jsonObject.getString("active");
                        String todaycases=jsonObject.getString("cChanges");
                        String todayrecovred= jsonObject.getString("rChanges");
                        String todaydeaths=jsonObject.getString("dChanges");
                        String todayactive=jsonObject.getString("aChanges");
                        stateHelper=new StateHelper(statename,totalcases,totalrecovered,totaldeaths,active,todaycases,todayrecovred,todaydeaths,todayactive);
                        stateHelperList.add(stateHelper);
                    }
                    adapter=new StateAdapter(stateHelperList,AffectedStates.this);
                    recyclerView.setAdapter(adapter);
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AffectedStates.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    @Override
    public void selectedState(StateHelper stateHelper) {
        startActivity(new Intent(AffectedStates.this,StateDetails.class).putExtra("data", stateHelper));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.seacrh_state);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Enter state name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        else if(item.getItemId()==R.id.seacrh_state)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}