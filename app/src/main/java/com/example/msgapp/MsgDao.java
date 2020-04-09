package com.example.msgapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MsgDao {

    // for main activity
    @Insert
    void insert_t1(Main m);
    @Update
    void update_t1(Main m);
    @Delete
    void delete_t1(Main m);
    @Query("DELETE FROM main_table")
    void deleteAll_t1();
//    @Query("SELECT * FROM main_table order by datetime(ts) desc")
    @Query("SELECT * FROM main_table order by id desc")
    LiveData<List<Main>> getAll_t1();

    // for individual threads
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert_t2(Msg msg);
    @Delete
    void delete_t2(Msg msg);
    @Query("DELETE FROM msg_table")
    void deleteAll_t2();
    @Query("SELECT * FROM msg_table order by datetime(ts)")
    LiveData<List<Msg>> getAll_t2();

}
