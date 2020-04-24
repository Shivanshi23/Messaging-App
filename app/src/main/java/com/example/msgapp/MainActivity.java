package com.example.msgapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MsgAdapter.OnChatSummaryListener{
    public static final int ADD_MSG_REQUEST = 1;
    private String TAG = this.getClass().getSimpleName();
    MsgViewModel msgViewModel;
    private List<Main> chatSummaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        recyclerView.setHasFixedSize(true);

        final MsgAdapter adapter = new MsgAdapter(this,this);
        recyclerView.setAdapter(adapter);

        msgViewModel = new ViewModelProvider(this).get(MsgViewModel.class);
        msgViewModel.getAll_t1().observe(this, new Observer<List<Main>>() {
            @Override
            public void onChanged(List<Main> mains) {
                adapter.setItems(mains);
                chatSummaryList = mains;
            }
        });

        FloatingActionButton button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,NewMsgActivity.class);
                //pass intent as input to new msg activity
                //and startactivityforresult method also get output of that activity
                startActivityForResult(intent, ADD_MSG_REQUEST);
            }
        });

        //for swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                msgViewModel.delete_t1(adapter.getMsgAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Msg deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onChatSummaryClick(int position) {
        Log.i(TAG, position + "item clicked");
        Intent i = new Intent(MainActivity.this, IndividualChatActivity.class);
        i.putExtra("contactNumber",chatSummaryList.get(position).getSender());
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_MSG_REQUEST && resultCode == RESULT_OK){
            String phone = data.getStringExtra(NewMsgActivity.EXTRA_PHONE);
            String msg = data.getStringExtra(NewMsgActivity.EXTRA_MSG);

            Main m = new Main(phone, msg);
            msgViewModel.insert_t1(m);
            Toast.makeText(this, "Msg Saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Msg not saved", Toast.LENGTH_SHORT).show();
        }
    }

    //for creating menu for deleting all records in main page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_t1:
                msgViewModel.deleteAll_t1();
                Toast.makeText(this, "All records deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent intent1 = getIntent();
//        String phone = intent1.getStringExtra(Receiver.EXTRA_PHONE);
//        String msg = intent1.getStringExtra(Receiver.EXTRA_MSG);
//        if(phone != null && msg != null){
//        Main m = new Main(phone,msg);
//        msgViewModel.insert_t1(m);
//        Toast.makeText(this, "Received Msg Saved", Toast.LENGTH_SHORT).show();
//        }
//    }
}
