package com.samarthappwork.mitaskassigner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Alltask_activity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private RecyclerView.LayoutManager layoutManager ;
    BottomNavigationView bottom_nav_view;
    Toolbar home_activity_toolbar ;
    int present_selected_item;
    TaskAdapter task_adapter;
    String event_name ;
    final  static String IS_CG_YES = "NO" ;

    FirebaseRecyclerOptions<New_task> options;

    FloatingActionButton add_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar setup code

        event_name = getIntent().getStringExtra("event_name");

        home_activity_toolbar = findViewById(R.id.home_activity_toolbar);
        setSupportActionBar(home_activity_toolbar);
        home_activity_toolbar.setTitle("EVENT : " + event_name);

        // bottom navigation complete code

        bottom_nav_view = this.findViewById(R.id.bottom_nav_view);
        bottom_nav_view.setSelectedItemId(R.id.bottom_nav_menu_tasks);
        bottom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()) {
                    case R.id.bottom_nav_menu_home:

                        // home_activty_parent_layout.addView(home_activity_toolbar,0);
                        //   temp_fragment = new home_fragment();
                        //  present_selected_item = item.getItemId();
                        //   getSupportFragmentManager().beginTransaction().replace(R.id.post_container, temp_fragment).commit();
                        present_selected_item = item.getItemId();

                        Intent intent_search = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent_search);
                        break;
                    case R.id.bottom_nav_menu_tasks:
                        if(present_selected_item != item.getItemId()){
                            Intent intent_task = new Intent(getApplicationContext(), tasks_activity.class);
                            startActivity(intent_task);}
                        break;
                    case R.id.bottom_nav_menu_notification:

                        Intent intent_notification = new Intent( getApplicationContext(), coordies_activity.class);
                        startActivity(intent_notification);
                        present_selected_item = item.getItemId();
                        break;

                }


                return true;
            }


        });

        // recyclerview setup code

        Query QUERY = FirebaseDatabase.getInstance().getReference("TASKS").child(event_name);

        recyclerView = findViewById(R.id.homeactivity_RV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if(LoginedUser.getIs_CG().length() == 3){
           options = new FirebaseRecyclerOptions.Builder<New_task>().
                    setQuery(FirebaseDatabase.getInstance().getReference().child("TASKS_COORDIES").child(LoginedUser.getPhone())
                            //.equalTo("8319085865p1")
                            , New_task.class).build();
        }else { options = new FirebaseRecyclerOptions.Builder<New_task>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("ALL_TASKS")
                        //.equalTo("8319085865p1")
                        , New_task.class).build();}

        task_adapter = new TaskAdapter( options);
        task_adapter.startListening();
        recyclerView.setAdapter(task_adapter);


        add_event = this.findViewById(R.id.floatingActionButton);
        if(LoginedUser.getIs_CG().length() == 3){
            add_event.setVisibility(View.INVISIBLE);
        }
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), add_task_activity.class);
                intent.putExtra("event_name", event_name);
                view.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //todo go to profile section and icrease icon size
        switch (item.getItemId()){
            case R.id.toolbar_menu_chat :
                Toast.makeText(this,"you clicked messages",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void open(){
        Toast.makeText(this,"sow comment box",Toast.LENGTH_SHORT).show();
    }


}