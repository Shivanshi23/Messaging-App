package com.example.msgapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgHolder> {
    private List<Main> items = new ArrayList<>();

    class MsgHolder extends RecyclerView.ViewHolder {
        private TextView tv_sender;
        private TextView tv_msg;
        private TextView tv_ts;

        public MsgHolder(@NonNull View itemView) {
            super(itemView);
            tv_sender = itemView.findViewById(R.id.tv_sender);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            tv_ts = itemView.findViewById(R.id.tv_ts);

        }
    }

    //hi there

    @NonNull
    @Override
    public MsgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_item, parent, false);
        return new MsgHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgHolder holder, int position) {
        Main currentItem = items.get(position);
        holder.tv_sender.setText(currentItem.getSender());
        holder.tv_msg.setText(currentItem.getLast_msg());
        holder.tv_ts.setText(String.valueOf(currentItem.getTs()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Main> m){
        this.items = m;
        notifyDataSetChanged();
    }

    public Main getMsgAt(int position){
        return items.get(position);
    }
}
