package com.example.msgapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "main_table")
public class Main {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String sender;
    private String last_msg;
    @TypeConverters( {TimestampConverter.class} )
    //@ColumnInfo(defaultValue = "(dateToTimestamp('%s','now'))")
    private Date ts;

    public Main(String sender, String last_msg) {
        this.sender = sender;
        this.last_msg = last_msg;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTs(Date ts) {
        this.ts = AppUtils.getCurrentDateTime();;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getLast_msg() {
        return last_msg;
    }

    public Date getTs() {
        return ts;
    }
}

@Entity(tableName = "msg_table")
class Msg{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String msg;
    @TypeConverters( {TimestampConverter.class} )
    private Date ts;
    private int msg_type;

    public Msg(String msg, int msg_type){
        this.msg = msg;
        this.msg_type = msg_type;
    }

    public void setTs(Date ts) {
        this.ts = AppUtils.getCurrentDateTime();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getTs() {
        return ts;
    }

    public int getMsg_type() {
        return msg_type;
    }
}
