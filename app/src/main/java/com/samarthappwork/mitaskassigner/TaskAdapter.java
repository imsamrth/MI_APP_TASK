package com.samarthappwork.mitaskassigner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class TaskAdapter extends FirebaseRecyclerAdapter<New_task, TaskAdapter.myviewholder> {

        public TaskAdapter(@NonNull FirebaseRecyclerOptions<New_task> options) {
            super(options); }

        @Override
        protected void onBindViewHolder (@NonNull myviewholder holder, int position,
                                         @NonNull New_task model){
            final Boolean[] bool = new Boolean[1];
            holder.department_name.setText(model.getDepartmentId());
            holder.task_name.setText(model.getEvent_name());
            holder.deadline.setText(model.getDeadline());
            holder.ispending.setText(model.getIspending());

            FirebaseDatabase storage = FirebaseDatabase.getInstance();

            //TODO: TURN ON COLOUR OF PENDING BUTTON

//        DatabaseReference databaseReferencevalue = storage.getReference("likes");
//        Query checkUser = databaseReferencevalue.child(post_url).orderByKey().equalTo(loggined_user_details.getPhone());

            holder.task_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), tasks_activity.class);
                    intent.putExtra("task_name", model.getTask_name());
                    intent.putExtra("task_deadline", model.getDeadline());
                    intent.putExtra("task_description", model.getTask_description());
                    intent.putExtra("event_name",model.getEvent_name());
                    v.getContext().startActivity(intent);


                }});

        }

        @NonNull
        @Override
        public myviewholder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
            return new myviewholder(view); }

        class myviewholder extends RecyclerView.ViewHolder {
            ImageView sharebutton;
            TextView department_name, ispending, deadline, task_name;

            public myviewholder(@NonNull View itemView) {
                super(itemView);

                //comment_field.setHeight(0);
                sharebutton = itemView.findViewById(R.id.task_layout_share);
                ispending = itemView.findViewById(R.id.task_layout_pending);
                department_name = itemView.findViewById(R.id.task_layout_name);
                deadline = itemView.findViewById(R.id.task_layout_daystogo);
                task_name = itemView.findViewById(R.id.task_layout_eventname);
            }
        }



    }


