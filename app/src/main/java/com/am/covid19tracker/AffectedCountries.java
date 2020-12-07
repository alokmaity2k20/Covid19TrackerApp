package com.am.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
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

public class AffectedCountries extends AppCompatActivity implements CountryAdapter.SelectedCountry{

    Toolbar toolbar;
    RecyclerView recyclerViewcountry;
    SimpleArcLoader arcLoader;
    List<CountryHelper> countryHelperList=new ArrayList<>();
    CountryHelper countryHelper;
    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);
        toolbar=(Toolbar)findViewById(R.id.affectedcountry_toolbar);
        toolbar.setTitle("Affected Countries");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewcountry=(RecyclerView)findViewById(R.id.country_list);
        arcLoader=(SimpleArcLoader)findViewById(R.id.country_list_acrloader);

        recyclerViewcountry.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewcountry.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        GetRecycleViewData();

    }

    private void GetRecycleViewData() {
        String url="https://disease.sh/v2/countries/";
        arcLoader.start();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String countryname=jsonObject.getString("country");
                        String totalcases=jsonObject.getString("cases");
                        String totalrecovered=jsonObject.getString("recovered");
                        String totaldeaths=jsonObject.getString("deaths");
                        String active=jsonObject.getString("active");
                        String todaycases=jsonObject.getString("todayCases");
                        String todayrecovred= jsonObject.getString("todayRecovered");
                        String todaydeaths=jsonObject.getString("todayDeaths");
                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flagurl=object.getString("flag");
                        countryHelper=new CountryHelper(countryname,totalcases,totalrecovered,totaldeaths,active,todaycases,todayrecovred,todaydeaths,flagurl);
                        countryHelperList.add(countryHelper);

                    }
                    countryAdapter=new CountryAdapter(countryHelperList,AffectedCountries.this);
                    recyclerViewcountry.setAdapter(countryAdapter);
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
                Toast.makeText(AffectedCountries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void selectedCountry(CountryHelper countryHelper) {
        startActivity(new Intent(AffectedCountries.this,CountryDetails.class).putExtra("data", countryHelper));
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.seacrh_state);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Enter country name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                countryAdapter.getFilter().filter(s);
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