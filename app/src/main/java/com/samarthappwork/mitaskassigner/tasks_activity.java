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

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samarthappwork.mitaskassigner.databinding.ActivityTasksBinding;

public class tasks_activity extends AppCompatActivity {

TextView name, description, deadline ;
FloatingActionButton done, addperson ;
String task_name, task_deadline, task_description , event_name ;

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

        name = findViewById(R.id.textView);
        description = findViewById(R.id.textView2);
        deadline = findViewById(R.id.textView3);
        done = this.findViewById(R.id.floatingActionButton3);
       addperson = this.findViewById(R.id.floatingActionButton2);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReferencevalue.child(event_name).child(task_name).child("ispending").setValue("Done");

            }
        });

    }
}