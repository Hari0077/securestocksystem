package com.example.securestocksystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder >{

    private ShowActivity activity;
    private List<Model> mList;

    public MyAdaptor(ShowActivity activity,List<Model> mList)
    {
        this.activity = activity;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptor.MyViewHolder holder, int position) {
          holder.productname.setText(mList.get(position).getProductname());
          holder.cprice.setText(mList.get(position).getCprice());
          holder.wprice.setText(mList.get(position).getWprice());
          holder.tstocks.setText(mList.get(position).getTstocks());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productname,cprice,wprice,tstocks;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.productname);
            cprice = itemView.findViewById(R.id.cprice);
            wprice = itemView.findViewById(R.id.wprice);
            tstocks = itemView.findViewById(R.id.tstocks);

        }
    }

}
