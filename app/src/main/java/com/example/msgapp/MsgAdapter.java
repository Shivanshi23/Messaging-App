package com.example.msgapp;

import android.content.Context;
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
    private LayoutInflater layoutInflater;
    private List<Main> items = new ArrayList<>();
    private OnChatSummaryListener onChatSummaryListener;

    public MsgAdapter(Context context, OnChatSummaryListener onChatSummaryListener){
        layoutInflater = LayoutInflater.from(context);
        this.onChatSummaryListener = onChatSummaryListener;
    }

    public class MsgHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_sender;
        private TextView tv_msg;
        private TextView tv_ts;
        OnChatSummaryListener onChatSummaryListener;


        public MsgHolder(@NonNull View itemView, OnChatSummaryListener onChatSummaryListener) {
            super(itemView);
            tv_sender = itemView.findViewById(R.id.tv_sender);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            tv_ts = itemView.findViewById(R.id.tv_ts);
            this.onChatSummaryListener = onChatSummaryListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view){
            onChatSummaryListener.onChatSummaryClick(getAdapterPosition());
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

    public interface OnChatSummaryListener{
        void onChatSummaryClick(int position);
    }


    @NonNull
    @Override
    public MsgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_item, parent, false);
        return new MsgHolder(itemView,onChatSummaryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgHolder holder, int position) {
        Main currentItem = items.get(position);
        holder.setData(currentItem,position);
    }



    @Override
    public int getItemCount() {
        if(items!=null)
        return items.size();
        else
            return 0;
    }

    public void setItems(List<Main> m){
        this.items = m;
        notifyDataSetChanged();
    }

    public Main getMsgAt(int position){
        return items.get(position);
    }
}
