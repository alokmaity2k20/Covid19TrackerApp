package com.am.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryAdapterVH> implements Filterable {
    List<CountryHelper> countryHelperList;
    List<CountryHelper> countryHelperListFilter;
    Context context;
    private SelectedCountry selectedCountry;

    public CountryAdapter(List<CountryHelper> countryHelperList,SelectedCountry selectedCountry) {
        this.countryHelperList = countryHelperList;
        this.countryHelperListFilter=countryHelperList;
        this.selectedCountry=selectedCountry;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new CountryAdapterVH(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryAdapterVH holder, int position) {
        CountryHelper countryHelper=countryHelperList.get(position);
        String countryname=countryHelper.getCountryname();
        String countrycases=countryHelper.getTotalcases();
        String countrycovered=countryHelper.getTotalrecovered();
        String countrydeaths=countryHelper.getTotaldeaths();

        holder.name.setText(countryname);
        holder.cases.setText(countrycases);
        holder.recovered.setText(countrycovered);
        holder.deaths.setText(countrydeaths);

    }

    @Override
    public int getItemCount() {
        return countryHelperList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults=new FilterResults();
                if(charSequence==null || charSequence.length()==0)
                {
                    filterResults.count=countryHelperListFilter.size();
                    filterResults.values=countryHelperListFilter;
                }
                else
                {
                    String searchchar=charSequence.toString().toLowerCase();
                    List<CountryHelper> resultData=new ArrayList<>();
                    for(CountryHelper countryHelpernew:countryHelperListFilter)
                    {
                        if(countryHelpernew.getCountryname().toLowerCase().contains(searchchar))
                        {
                            resultData.add(countryHelpernew);
                        }
                    }
                    filterResults.count=resultData.size();
                    filterResults.values=resultData;

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    countryHelperList= (List<CountryHelper>) filterResults.values;
                    notifyDataSetChanged();
            }
        };
        return filter;
    }


    public interface SelectedCountry{
        void selectedCountry(CountryHelper countryHelper);
    }

    public class CountryAdapterVH extends RecyclerView.ViewHolder {
        TextView name,cases,recovered,deaths;

        public CountryAdapterVH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            cases=itemView.findViewById(R.id.cases);
            recovered=itemView.findViewById(R.id.recovered);
            deaths=itemView.findViewById(R.id.deaths);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCountry.selectedCountry(countryHelperList.get(getAdapterPosition()));
                }
            });
        }
    }
}
