package com.example.msgapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MsgViewModel extends AndroidViewModel {
    private MsgRepository repository;

    private LiveData<List<Main>> allMainMsg;
    private LiveData<List<Msg>> allMsg;

    public MsgViewModel(@NonNull Application application) {
        super(application);
        repository = new MsgRepository(application);
        allMainMsg = repository.getAll_t1();
        allMsg = repository.getAll_t2();
    }

    public void insert_t1(Main m){
        repository.insert_t1(m);
    }
    public void update_t1(Main m){
        repository.update_t1(m);
    }
    public void delete_t1(Main m){
        repository.delete_t1(m);
    }
    public void deleteAll_t1(){
        repository.deleteAll_t1();
    }
    public LiveData<List<Main>> getAll_t1(){
        return allMainMsg;
    }

    public void insert_t2(Msg m){
        repository.insert_t2(m);
    }
    public void delete_t2(Msg m){
        repository.delete_t2(m);
    }
    public void deleteAll_t2(){
        repository.deleteAll_t2();
    }
    public LiveData<List<Msg>> getAll_t2(){
        return allMsg;
    }
}
