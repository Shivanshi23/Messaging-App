package com.example.msgapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Main.class, Msg.class}, version = 1)
@TypeConverters({TimestampConverter.class})
public abstract class MsgDatabase extends RoomDatabase {
    public abstract MsgDao msgDao();
    private static MsgDatabase instance;

    public static synchronized MsgDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MsgDatabase.class, "msg_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MsgDao msgDao;

        private PopulateDbAsyncTask(MsgDatabase db) {
            msgDao = db.msgDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            msgDao.insert_t1(new Main("8543837466", "Description 1"));
            msgDao.insert_t1(new Main("8543837466", "Description 2"));
            msgDao.insert_t1(new Main("8543837403", "Description 3"));
            return null;
        }
    }
}
