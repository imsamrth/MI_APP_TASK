package com.samarthappwork.mitaskassigner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

class mainactivity_postfragment extends Fragment {

    RecyclerView recyclerView ;
   EventAdapter adapter;

    public mainactivity_postfragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main_activity_adapter, container, false);
        recyclerView = view.findViewById(R.id.profile_activity_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<New_event> options = new FirebaseRecyclerOptions.Builder<New_event>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("EVENTS")
                        //.equalTo("8319085865p1")
                        ,New_event.class).build();
        adapter = new EventAdapter( options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        return  view ;
    }

}
