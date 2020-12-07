package com.am.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateAdapterVH> implements Filterable {
    List<StateHelper> stateHelperList;
    List<StateHelper> stateHelperListFiltered;
    Context context;
    private SelectedState selectedState;
    public StateAdapter(List<StateHelper> stateHelperList,SelectedState selectedState) {
        this.stateHelperList = stateHelperList;
        this.stateHelperListFiltered=stateHelperList;
        this.selectedState = selectedState;
    }

    @NonNull
    @Override
    public StateAdapter.StateAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new StateAdapterVH(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.StateAdapterVH holder, int position) {
        StateHelper stateHelper=stateHelperList.get(position);
        String statename=stateHelper.getStatename();
        String statecases=stateHelper.getTotalcases();
        String staterecovered=stateHelper.getTotalrecovered();
        String statedeaths=stateHelper.getTotaldeaths();

        holder.name.setText(statename);
        holder.cases.setText(statecases);
        holder.recovered.setText(staterecovered);
        holder.deaths.setText(statedeaths);
    }

    @Override
    public int getItemCount() {
        return stateHelperList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults=new FilterResults();
                if(charSequence==null || charSequence.length()==0)
                {
                    filterResults.count=stateHelperListFiltered.size();
                    filterResults.values=stateHelperListFiltered;
                }
                else {
                    String searchchar=charSequence.toString().toLowerCase();
                    List<StateHelper> resultData=new ArrayList<>();
                    for(StateHelper stateHelpernew:stateHelperListFiltered)
                    {
                        if(stateHelpernew.getStatename().toLowerCase().contains(searchchar))
                        {
                            resultData.add(stateHelpernew);
                        }
                    }
                    filterResults.count=resultData.size();
                    filterResults.values=resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    stateHelperList= (List<StateHelper>) filterResults.values;
                    notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface SelectedState{
        void selectedState(StateHelper stateHelper);
    }

    public class StateAdapterVH extends RecyclerView.ViewHolder {
        TextView name,cases,recovered,deaths;

        public StateAdapterVH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            cases=itemView.findViewById(R.id.cases);
            recovered=itemView.findViewById(R.id.recovered);
            deaths=itemView.findViewById(R.id.deaths);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedState.selectedState(stateHelperList.get(getAdapterPosition()));
                }
            });
        }
    }
}
