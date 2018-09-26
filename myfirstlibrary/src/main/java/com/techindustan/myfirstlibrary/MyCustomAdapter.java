package com.techindustan.myfirstlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    private List<String> countryList;
    private List<String> alTemporaryList = new ArrayList<>();
    ItemClick itemClick;
    ItemClickToHideDialog hideDialog;
    MyDialog dialog;
    int list_item_color;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countryText;
        LinearLayout llParent;


        public MyViewHolder(View view) {
            super(view);
            countryText = (TextView) view.findViewById(R.id.tvName);
            llParent = view.findViewById(R.id.llParent);

        }
    }

    public MyCustomAdapter(List<String> countryList, Context context, MyDialog dialog, int list_item_color) {
        this.countryList = countryList;
        alTemporaryList.addAll(countryList);
        itemClick = (ItemClick) context;
        this.dialog = dialog;
        this.list_item_color = list_item_color;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String c = countryList.get(position);
        holder.countryText.setText(c);
        holder.countryText.setTextColor(list_item_color);
        holder.llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                itemClick.itemClick(position, countryList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(v);
    }


    void filterData(String s) {


        if (s.length() == 0) {
            countryList.clear();

            countryList.addAll(alTemporaryList);
            notifyDataSetChanged();
        } else {

            countryList.clear();
            for (String str : alTemporaryList) {
                if (str.toLowerCase().contains(s.toLowerCase())) {
                    countryList.add(str);

                }
            }


            notifyDataSetChanged();
        }


    }

    public interface ItemClickToHideDialog {
        public void itemClick(int position, Object val);
    }

}
