package com.samarthappwork.mitaskassigner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;


public class userhomeadapter extends FirebaseRecyclerAdapter<New_user, userhomeadapter.myuserviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public userhomeadapter(@NonNull FirebaseRecyclerOptions<New_user> options) {
        super(options);
    }


    DatabaseReference databaseReferencevalue = FirebaseDatabase.getInstance().getReference("USERS");
    Query checkUser ;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("USERS");





    @Override
    protected void onBindViewHolder(@NonNull myuserviewholder holder, int position, @NonNull New_user model) {

        holder.user_query_username.setText(model.getName());

        holder.user_query_DEPARTMENT.setText(model.getDepartment());

        holder.user_query_PHONE.setText(model.getPhone());



    }

    @NonNull
    @Override
    public myuserviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new myuserviewholder(view);
    }


    class myuserviewholder extends RecyclerView.ViewHolder{

        TextView user_query_username;
        Button user_query_PHONE ;
        TextView user_query_DEPARTMENT;
        View user_query_parentlayout ;
        StorageReference mstorgerefrence_profile;
        public myuserviewholder(@NonNull View itemView){
            super(itemView);

            user_query_PHONE = itemView.findViewById(R.id.single_user_phone_button);
            user_query_username= itemView.findViewById(R.id.list_item_username);
            user_query_DEPARTMENT= itemView.findViewById(R.id.textView4);
            user_query_parentlayout = itemView.findViewById(R.id.list_item_user);
        }
    }



}




