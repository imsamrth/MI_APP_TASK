package com.samarthappwork.mitaskassigner;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samarthappwork.mitaskassigner.databinding.ActivityLoginBinding;

public class Login_activity extends AppCompatActivity {

        TextView signup_text ;
        com.google.android.material.textfield.TextInputLayout phone,password;
        Button loginbutton;
        ConstraintLayout login_main_layout ;
        LoginedUser myapplication = (LoginedUser) this.getApplication();
        long backpressed ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_login);

            login_main_layout = this.findViewById(R.id.login_activity_xml);

            signup_text = this.findViewById(R.id.loginactivity_usersignup_text);
            signup_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login_activity.this,signup_activity.class);
                    startActivity(intent);
                }
            });

            phone = this.findViewById(R.id.login_activity_username_layout);
            password = this.findViewById(R.id.login_activity_password_layout);
            loginbutton = this.findViewById(R.id.loginactivity_loginbutton);

            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isUser();
                }
            });
        }

        private Boolean validatephone(){

            String entry = phone.getEditText().getText().toString();
            if(entry.isEmpty()){
                phone.setError("phone cannot be empty");
                return false;
            }else if(entry.length() != 10 ){
                phone.setError("type complete phone no.");
                return false ;
            }else { phone.setError(null);
                return true ;}
        }

        private Boolean validatepassword(){

            String entry = password.getEditText().getText().toString();

            if(entry.isEmpty()){
                password.setError("password  cannot be empty");
                return false;
            }else {
                password.setError(null);return true ;}

        }

        private void isUser(){

            String userenterenphoneno = phone.getEditText().getText().toString().trim();
            String userenterenpassword = password.getEditText().getText().toString().trim();

            DatabaseReference databaseReferencevalue = FirebaseDatabase.getInstance().getReference("USERS");

            Query checkUser = databaseReferencevalue.orderByKey().equalTo(userenterenphoneno);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists()) {
                        phone.setError(null);
                        phone.setErrorEnabled(false);
                        String passwordfromdb = snapshot.child(userenterenphoneno).child("password").getValue(String.class);


                        if (passwordfromdb.equals(userenterenpassword)) {
                            String namefromdb = snapshot.child(userenterenphoneno).child("name").getValue(String.class);
                            String emailfromdb = snapshot.child(userenterenphoneno).child("email").getValue(String.class);
                            String phonefromdb = snapshot.child(userenterenphoneno).child("phone").getValue(String.class);
                            String departmentfromdb = snapshot.child(userenterenphoneno).child("department").getValue(String.class);
                            String iscoordiefromdb = snapshot.child(userenterenphoneno).child("is_cordie").getValue(String.class);


                            startnewintent(namefromdb);

                            myapplication.setPhone(userenterenphoneno);
                            LoginedUser.setName(namefromdb);
                            LoginedUser.setDepartment_ID(departmentfromdb);
                            LoginedUser.setIs_CG(iscoordiefromdb);
                            LoginedUser.setEmail(emailfromdb);
                            LoginedUser.setMiNum(userenterenphoneno);
                            finish();
                        }
                        else {
                            password.setError("Wrongpassword");
                            password.requestFocus();
                        }
                    }
                    else {
                        phone.setError("No such user exist");
                        phone.requestFocus();
                        Toast.makeText(Login_activity.this,snapshot.toString(),Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        private void startnewintent(String namefromdb){
            // Construct an Intent as normal
            Intent intent = new Intent(Login_activity.this, MainActivity.class);
            intent.putExtra("username", namefromdb);

            // BEGIN_INCLUDE(start_activity)

            // Now we can start the Activity, providing the activity options as a bundle
            startActivity( intent);
        }

        @Override
        public void onBackPressed() {
            //super.onBackPressed();
            // backpressed = getTime
            Toast.makeText(getApplicationContext(),"press again to exit",Toast.LENGTH_SHORT).show();

        }
    }