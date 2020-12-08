package com.swapnil.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.swapnil.myapplication.R;
import com.swapnil.myapplication.model.pojo.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class CountryListAdapter extends
        RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    private List<CountryModel> dataList = new ArrayList<>();
    private OnItemClickListener clickListener;

    public CountryListAdapter() {

    }

    public void updataList(List<CountryModel> updatedList) {
        dataList.clear();
        dataList.addAll(updatedList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.country_list_item, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CountryModel item = dataList.get(position);
        holder.bind(item);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CountryModel selectedData);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            clickListener.onItemClick(dataList.get(pos));
        }


        public void bind(final CountryModel model) {

        }
    }

}
