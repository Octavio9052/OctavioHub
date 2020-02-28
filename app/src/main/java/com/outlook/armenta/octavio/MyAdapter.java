package com.outlook.armenta.octavio;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String[] mDataset;

    MyAdapter(String[] data){
        mDataset = data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout constraintLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        return new MyViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.textView.setText(mDataset[position]);
        //holder.clView.getViewById(R.id.tv_datetime);
        holder.tvDatetime.setText(mDataset[position]);
        holder.tvDescription.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView tvDatetime;
        TextView tvDescription;
        MyViewHolder(LinearLayout cl) {
            super(cl);
            tvDatetime = (TextView) cl.findViewById(R.id.tv_datetime);
            tvDescription = (TextView) cl.findViewById(R.id.tv_description);
        }

    }
}
