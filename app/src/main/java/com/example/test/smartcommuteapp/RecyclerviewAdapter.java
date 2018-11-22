package com.example.test.smartcommuteapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyHolder> {

    List<ListData> listdata;

    String[] cond = {"High traffic", "Low Traffic", "Normal Traffic"};
    //String word = cond[new Random().nextInt(cond.length)];

    public RecyclerviewAdapter(List<ListData> listdata) {
        this.listdata = listdata;


    }


    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myview,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(MyHolder holder, int position) {
        ListData data = listdata.get(position);
        holder.vname.setText(data.getLocationName());
        holder.vaddress.setText(data.getAddress());
        holder.randcondition.setText(cond[new Random().nextInt(cond.length)]);
       // holder.vaddress.setText(data.getAddress());
    }



    public int getItemCount() {
        return listdata.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        TextView vname , vaddress, vemail,randcondition;

        public MyHolder(View itemView) {
            super(itemView);
            vname = (TextView) itemView.findViewById(R.id.pplace);
            vaddress = (TextView) itemView.findViewById(R.id.paddress);
            randcondition = (TextView)itemView.findViewById(R.id.pconditions);

        }
    }
}
