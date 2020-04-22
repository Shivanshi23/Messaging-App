package com.example.msgapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgHolder> {
    private List<Main> items = new ArrayList<>();

    public class MsgHolder extends RecyclerView.ViewHolder {
        private TextView tv_sender;
        private TextView tv_msg;
        private TextView tv_ts;

        public MsgHolder(@NonNull View itemView) {
            super(itemView);
            tv_sender = itemView.findViewById(R.id.tv_sender);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            tv_ts = itemView.findViewById(R.id.tv_ts);

        }

        public void setData(Main current, int position){
            this.tv_sender.setText(current.getSender());
            String lastMessage = current.getLast_msg();
            if(lastMessage.length() >= 20) {
                lastMessage = lastMessage.substring(0, 19) + "...";
            }
            this.tv_msg.setText(lastMessage);

            Date date = current.getTs();
            Date currentDate = new Date();
            DateFormat formatter;
            String dateText;
            formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
            if(formatter.format(date).equals(formatter.format(currentDate))){
                formatter = DateFormat.getTimeInstance(DateFormat.SHORT);
                dateText = formatter.format(date);
            }else {
                formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
                dateText = formatter.format(date);
            }
            this.tv_ts.setText(dateText);
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
        holder.setData(currentItem,position);
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
