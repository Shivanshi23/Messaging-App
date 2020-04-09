package com.example.msgapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MsgRepository {
    private MsgDao msgDao;
    private LiveData<List<Main>> allMainMsg;
    private LiveData<List<Msg>> allMsg;

    public MsgRepository(Application application) {
        MsgDatabase database = MsgDatabase.getInstance(application);
        msgDao = database.msgDao();
        allMainMsg = msgDao.getAll_t1();
        allMsg = msgDao.getAll_t2();
    }

    // for main activity
    public void insert_t1(Main m) {
        new InsertMainAsyncTask(msgDao).execute(m);
    }
    public void update_t1(Main m) {
        new UpdateMainAsyncTask(msgDao).execute(m);
    }
    public void delete_t1(Main m) {
        new DeleteMainAsyncTask(msgDao).execute(m);
    }
    public void deleteAll_t1() {
        new DeleteAllMainAsyncTask(msgDao).execute();
    }
    public LiveData<List<Main>> getAll_t1(){
        return allMainMsg;
    }
    private static class InsertMainAsyncTask extends AsyncTask<Main, Void, Void> {
        private MsgDao msgDao;

        private InsertMainAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Main... m) {
            msgDao.insert_t1(m[0]);
            return null;
        }
    }
    private static class UpdateMainAsyncTask extends AsyncTask<Main, Void, Void> {
        private MsgDao msgDao;

        private UpdateMainAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Main... m) {
            msgDao.update_t1(m[0]);
            return null;
        }
    }
    private static class DeleteMainAsyncTask extends AsyncTask<Main, Void, Void> {
        private MsgDao msgDao;

        private DeleteMainAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Main... m) {
            msgDao.delete_t1(m[0]);
            return null;
        }
    }
    private static class DeleteAllMainAsyncTask extends AsyncTask<Void, Void, Void> {
        private MsgDao msgDao;

        private DeleteAllMainAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            msgDao.deleteAll_t1();
            return null;
        }
    }

    // for individual threads of contacts
    public void insert_t2(Msg m) {
        new InsertMsgAsyncTask(msgDao).execute(m);
    }

    public void delete_t2(Msg m) {
        new DeleteMsgAsyncTask(msgDao).execute(m);
    }
    public void deleteAll_t2() {
        new DeleteAllMsgAsyncTask(msgDao).execute();
    }
    public LiveData<List<Msg>> getAll_t2(){
        return allMsg;
    }
    private static class InsertMsgAsyncTask extends AsyncTask<Msg, Void, Void> {
        private MsgDao msgDao;

        private InsertMsgAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Msg... m) {
            msgDao.insert_t2(m[0]);
            return null;
        }
    }
    private static class DeleteMsgAsyncTask extends AsyncTask<Msg, Void, Void> {
        private MsgDao msgDao;

        private DeleteMsgAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Msg... m) {
            msgDao.delete_t2(m[0]);
            return null;
        }
    }
    private static class DeleteAllMsgAsyncTask extends AsyncTask<Void, Void, Void> {
        private MsgDao msgDao;

        private DeleteAllMsgAsyncTask(MsgDao msgDao) {
            this.msgDao = msgDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            msgDao.deleteAll_t2();
            return null;
        }
    }
}
