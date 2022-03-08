package com.samarthappwork.mitaskassigner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView ;
    private RecyclerView.LayoutManager layoutManager ;
    BottomNavigationView bottom_nav_view;
    Toolbar home_activity_toolbar ;
    int present_selected_item;
    EventAdapter event_adapter;

    FloatingActionButton add_event;
    final  static String IS_COORDIE_YES = "YES" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar setup code

        home_activity_toolbar = findViewById(R.id.home_activity_toolbar);
        setSupportActionBar(home_activity_toolbar);
        home_activity_toolbar.setTitle("Hi, "+ LoginedUser.getName());

        // bottom navigation complete code


        Toast.makeText(getApplicationContext(),String.valueOf(LoginedUser.getIs_CG().length()),Toast.LENGTH_SHORT).show();
        bottom_nav_view = this.findViewById(R.id.bottom_nav_view);
        bottom_nav_view.setSelectedItemId(R.id.bottom_nav_menu_home);
        bottom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()) {
                    case R.id.bottom_nav_menu_home:
                        if(present_selected_item != item.getItemId())
                            // home_activty_parent_layout.addView(home_activity_toolbar,0);
                            //   temp_fragment = new home_fragment();
                            //  present_selected_item = item.getItemId();
                            //   getSupportFragmentManager().beginTransaction().replace(R.id.post_container, temp_fragment).commit();
                            present_selected_item = item.getItemId();
                        break;
                    case R.id.bottom_nav_menu_tasks:

                            Toast.makeText(getApplicationContext(),"Task started",Toast.LENGTH_SHORT).show();
                            Intent intent_search = new Intent(MainActivity.this, Alltask_activity.class);
                            startActivity(intent_search);

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

        recyclerView = findViewById(R.id.homeactivity_RV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<New_event> options = new FirebaseRecyclerOptions.Builder<New_event>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("EVENTS")
                        //.equalTo("8319085865p1")
                        , New_event.class).build();
       event_adapter = new EventAdapter( options);
        event_adapter.startListening();
        recyclerView.setAdapter(event_adapter);


        add_event = this.findViewById(R.id.floatingActionButton);
        add_event.setVisibility(View.INVISIBLE);
        if(LoginedUser.getIs_CG().length() == 3) add_event.setVisibility(View.INVISIBLE);
        else  add_event.setVisibility(View.VISIBLE);
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), add_event_activity.class);
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

    private void firebaseevents(){

        recyclerView.setVisibility(View.VISIBLE);

        Toast.makeText(getApplicationContext(),"search started",Toast.LENGTH_SHORT).show();

        Query search_query = FirebaseDatabase.getInstance().getReference().child("EVETNS");

        FirebaseRecyclerOptions<New_event> options =
                new FirebaseRecyclerOptions.Builder<New_event>().setQuery(search_query, New_event.class).build();

        event_adapter = new EventAdapter(options);
        event_adapter.startListening();
        recyclerView.setAdapter(event_adapter);
    }
}


