package com.samarthappwork.mitaskassigner;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tasks_activity extends AppCompatActivity {

TextView name, description, deadline, status ;
FloatingActionButton done, addperson ;
String task_name, task_deadline, task_description , event_name , task_status ;
    final  static String IS_CG_YES = "NO" ;

    DatabaseReference databaseReferencevalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        databaseReferencevalue = FirebaseDatabase.getInstance().getReference("TASKS");
        task_name = getIntent().getStringExtra("task_name");
        task_deadline = getIntent().getStringExtra("task_deadline");
        task_description = getIntent().getStringExtra("task_description");
        event_name = getIntent().getStringExtra("event_name");
        task_status = getIntent().getStringExtra("task_status");

        name = findViewById(R.id.textView);
        name.setText("Task : "+task_name);
        description = findViewById(R.id.textView2);
       description.setText(task_description);
        deadline = findViewById(R.id.textView3);
        deadline.setText(task_deadline);
        status = findViewById(R.id.textView11);
        status.setText(task_status);
        done = this.findViewById(R.id.floatingActionButton3);
        done.setVisibility(View.INVISIBLE);
        if(LoginedUser.getIs_CG().length() == 3){
            done.setVisibility(View.INVISIBLE);
        }
        else  done.setVisibility(View.VISIBLE);
       addperson = this.findViewById(R.id.floatingActionButton2);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReferencevalue.child(event_name).child(task_name).child("ispending").setValue("Done");
status.setText("Done");
                Toast.makeText(getApplicationContext(),"Task Marked Completed",Toast.LENGTH_SHORT).show();

            }
        });

    }
}