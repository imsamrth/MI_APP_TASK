package com.samarthappwork.mitaskassigner;

import android.Manifest;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;


public class add_event_activity extends AppCompatActivity {



    int present_selected_item;
    BottomNavigationView bottom_nav_view;
    LoginedUser myApplication  = (LoginedUser) this.getApplication();
    // List<post> postList ;
    FloatingActionButton gallery_add ,gallery_add2;
    Boolean imgadd = false ;
    Uri filepath ;
    Bitmap bitmap ;
    ImageView galleryeditorselectediamge ;
    EditText name_text, description_text ;

    String new_event_id = "",new_event_name = "" ,new_event_description = "";

    DatabaseReference databaseReferencevalue;
    Query checkUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        galleryeditorselectediamge = this.findViewById(R.id.galleryeditor_activity_selected_image);
        name_text = this.findViewById(R.id.edit_text_Name);
        description_text = this.findViewById(R.id.edit_text_descripton);


        databaseReferencevalue = FirebaseDatabase.getInstance().getReference("users");
        checkUser = databaseReferencevalue.orderByKey().equalTo(LoginedUser.getMiNum());

        gallery_add = this.findViewById(R.id.gallery_activity_add);

        gallery_add2 = this.findViewById(R.id.gallery_activity_add2);

        gallery_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(imgadd == false) {
                    Toast.makeText(getApplicationContext(), "permission granted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");

                    startActivityForResult(intent.createChooser(intent, "please select image"), 1);

                    Dexter.withActivity(add_event_activity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Toast.makeText(getApplicationContext(), "permission granted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "please select image"), 1);

                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            Toast.makeText(getApplicationContext(), "permission asked", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {galleryeditorselectediamge.setImageResource(R.drawable.outline_image_24);
                    imgadd = false ;
                    gallery_add.setImageResource(R.drawable.outline_add_a_photo_24);} }
        });

        gallery_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                new_event_name = String.valueOf(name_text.getText());
                new_event_id = LoginedUser.getDepartment_ID()+new_event_name;
                new_event_description = String.valueOf(description_text.getText());
                if(imgadd == true){

                        uploadimage();
                        uploadpost_details(new_event_name);



                    galleryeditorselectediamge.setImageResource(R.drawable.outline_image_24);
                    imgadd = false ;
                    gallery_add.setImageResource(R.drawable.outline_add_a_photo_24);
                    Toast.makeText(getApplicationContext(),"Image uploaded succesfully",Toast.LENGTH_SHORT).show();
                    name_text.setText(null);
                }


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
                Toast.makeText(add_event_activity.this,imgadd.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),"error in loading image", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //todo go to profile section and icrease icon size
        switch (item.getItemId()){
            case R.id.galleryeditor_done:

                Toast.makeText(this,"you clicked messages",Toast.LENGTH_SHORT).show();



                Toast.makeText(getApplicationContext(),"Ioaded succesfully",Toast.LENGTH_SHORT).show();

                new_event_name = String.valueOf(name_text.getText());
            if(imgadd == true){
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(getApplicationContext(),"Image upded succesfully",Toast.LENGTH_SHORT).show();
                        Toast.makeText(add_event_activity.this,"inside if pass",Toast.LENGTH_SHORT).show();
                        uploadimage();
                        uploadpost_details(new_event_name);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });


                galleryeditorselectediamge.setImageResource(R.drawable.outline_image_24);
                imgadd = false ;
                gallery_add.setImageResource(R.drawable.outline_add_a_photo_24);
                Toast.makeText(getApplicationContext(),"Image uploaded succesfully",Toast.LENGTH_SHORT).show();
                name_text.setText(null);
            }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void uploadimage() {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        Toast.makeText(getApplicationContext(),new_event_name,Toast.LENGTH_SHORT).show();

        StorageReference uploader = storage.getReference().child(new_event_name);
        uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"filesuplosded",Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
            }
        });
        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();
    }

    public void uploadpost_details(String caption){

        New_event postdatahelpervalues = new New_event(new_event_name,new_event_id,new_event_description);

        // String NEW_POST_ONE = applicationStatus.getPhone()+"p"+applicationStatus.getTotal_post();

        Toast.makeText(getApplicationContext(),caption,Toast.LENGTH_SHORT).show();


        FirebaseDatabase rootnode ;
        DatabaseReference reference;
        rootnode =FirebaseDatabase.getInstance();
        reference = rootnode.getReference("EVENTS");
//
        reference.child(new_event_name).setValue(postdatahelpervalues);
    }

}