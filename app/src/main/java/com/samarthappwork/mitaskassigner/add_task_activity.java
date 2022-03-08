package com.samarthappwork.mitaskassigner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class add_task_activity extends AppCompatActivity {


    int present_selected_item;
    BottomNavigationView bottom_nav_view;
    LoginedUser myApplication  = (LoginedUser) this.getApplication();
    // List<post> postList ;
    FloatingActionButton gallery_add ,gallery_add2;
    Boolean imgadd = false ;
    Uri filepath ;
    Bitmap bitmap ;
    ImageView galleryeditorselectediamge ;
    EditText name_text, description_text, deadline_text ;

    String new_task_name = "" , new_task_id = "",event_name = "" ,new_task_description = "" , new_deadline_text = "";

    DatabaseReference databaseReferencevalue;
    Query checkUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        name_text = this.findViewById(R.id.edit_text_Name);
        description_text = this.findViewById(R.id.edit_text_descripton);
        deadline_text = this.findViewById(R.id.edit_text_deadline);


        event_name = getIntent().getStringExtra("event_name");

        databaseReferencevalue = FirebaseDatabase.getInstance().getReference("users");
        checkUser = databaseReferencevalue.orderByKey().equalTo(LoginedUser.getMiNum());

        gallery_add2 = this.findViewById(R.id.gallery_activity_add2);


        gallery_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                new_task_name = String.valueOf(name_text.getText());
                new_task_id = LoginedUser.getDepartment_ID()+event_name;
                new_task_description = String.valueOf(description_text.getText());
                new_deadline_text = String.valueOf(deadline_text.getText());


                    uploadtask_details(new_task_name);



                    Toast.makeText(getApplicationContext(),"Task uploaded succesfully",Toast.LENGTH_SHORT).show();
                    name_text.setText(null);



            } } );





        bottom_nav_view = this.findViewById(R.id.bottom_nav_view);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK){

            filepath = data.getData();
            try
            {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                galleryeditorselectediamge.setImageBitmap(bitmap);
                gallery_add.setImageResource(R.drawable.outline_remove_circle_24);
                imgadd = true ;
                Toast.makeText(add_task_activity.this,imgadd.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),"error in loading image", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void uploadtask_details(String caption){

        New_task taskdatahelpervalues = new New_task(LoginedUser.getDepartment_ID(), event_name, new_task_id, new_task_name,new_deadline_text, new_deadline_text);

        // String NEW_POST_ONE = applicationStatus.getPhone()+"p"+applicationStatus.getTotal_post();

        Toast.makeText(getApplicationContext(),caption,Toast.LENGTH_SHORT).show();


        FirebaseDatabase rootnode ;
        DatabaseReference reference;
        rootnode =FirebaseDatabase.getInstance();
        reference = rootnode.getReference("TASKS").child(event_name);
//
        reference.child(new_task_name).setValue(taskdatahelpervalues);

        reference = rootnode.getReference("ALL_TASKS");
//
        reference.child(new_task_name).setValue(taskdatahelpervalues);
    }

}