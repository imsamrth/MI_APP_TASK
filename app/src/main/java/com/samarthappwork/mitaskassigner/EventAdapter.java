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

public class EventAdapter extends FirebaseRecyclerAdapter<New_event, EventAdapter.myviewholder> {

        public EventAdapter(@NonNull FirebaseRecyclerOptions<New_event> options) {
        super(options); }

        @Override
        protected void onBindViewHolder (@NonNull myviewholder holder,int position,
        @NonNull New_event model){
        final Boolean[] bool = new Boolean[1];
        holder.department_name.setText(model.getDepartmentId());
        holder.event_name.setText(model.getEvent_name());
        holder.daystogo.setText(model.getTimestamp());
        String event_url = model.event_name;

        FirebaseDatabase storage = FirebaseDatabase.getInstance();

        //TODO: TURN ON COLOUR OF PENDING BUTTON

//        DatabaseReference databaseReferencevalue = storage.getReference("likes");
//        Query checkUser = databaseReferencevalue.child(post_url).orderByKey().equalTo(loggined_user_details.getPhone());



        holder.mstorgerefrence_event = FirebaseStorage.getInstance().getReference().child(event_url);
        try {
            final File localfile2 = File.createTempFile(event_url, "jpeg");
            holder.mstorgerefrence_event.getFile(localfile2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    //Toast.makeText(getApplicationContext(),"file retrived",Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile2.getAbsolutePath());
                    holder.postimage.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(getApplicationContext(),"error in retriving",Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) holder.postimage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                String bitmappath = MediaStore.Images.Media.insertImage(v.getContext().getContentResolver(), bitmap, "post", null);

                Uri uri = Uri.parse(bitmappath);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.putExtra(Intent.EXTRA_TEXT, "checkout this post");
                v.getContext().startActivity(Intent.createChooser(intent, "share"));
            }
        });

            holder.event_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), Event_activity.class);
                    intent.putExtra("event_name", model.getEvent_name());
                    v.getContext().startActivity(intent);


                }});

    }

        @NonNull
        @Override
        public myviewholder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout,parent,false);
        return new myviewholder(view); }

        static class myviewholder extends RecyclerView.ViewHolder {
            ImageView postimage, sharebutton;
            TextView department_name, ispending, daystogo, event_name;
            StorageReference mstorgerefrence_event ;

            public myviewholder(@NonNull View itemView) {
                super(itemView);

                //comment_field.setHeight(0);
                sharebutton = itemView.findViewById(R.id.task_layout_share);
                postimage = itemView.findViewById(R.id.event_layout_image);
                ispending = itemView.findViewById(R.id.task_layout_pending);
                department_name = itemView.findViewById(R.id.task_layout_name);
                daystogo = itemView.findViewById(R.id.task_layout_daystogo);
                event_name = itemView.findViewById(R.id.task_layout_eventname);
            }
        }



}
