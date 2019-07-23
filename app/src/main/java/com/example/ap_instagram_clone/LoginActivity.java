package com.example.ap_instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email,login_password;
    private Button btnSignup,btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        login_email=findViewById(R.id.txtLoginEmail);
        login_password=findViewById(R.id.txtLoginPassword);

        btn_Login=findViewById(R.id.btnLogin2);
        btnSignup=findViewById(R.id.btnSignUp2);

        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }
    }
    public void Login(View view){

        try {
            ParseUser.logInInBackground(login_email.getText().toString(), login_password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user !=null && e==null){
                        FancyToast.makeText(LoginActivity.this,user.getUsername()
                                +" is logged in successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                    }
                }
            });
        }catch (Exception e){
            Log.d("ERROR",e.getLocalizedMessage());
        }
    }
    public void SignUp(View view){
        Intent intent=new Intent(LoginActivity.this,SignUp.class);
    }
}
