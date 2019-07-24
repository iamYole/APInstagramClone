package com.example.ap_instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity {

    private EditText sign_up_email,sign_up_username,sign_up_password;
    private Button btnSignup,btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        sign_up_email=findViewById(R.id.txtSignUpEmail);
        sign_up_username=findViewById(R.id.txtSignUpUsername);
        sign_up_password=findViewById(R.id.txtSignUpPassword);

        /*sign_up_password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && keyEvent.getAction()==KeyEvent.ACTION_DOWN)
                {
                    onClick(btnSignup);
                }
                return false;
            }
        });*/

        btn_Login=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignUp);

        if(ParseUser.getCurrentUser()!=null){
            //ParseUser.getCurrentUser().logOut();
            openSocialMediaActivity();
        }
    }

    public void Login(View view){

        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void SignUp(View view) {

        if (sign_up_username.getText().toString().equals("") ||
                sign_up_password.getText().toString().equals("") ||
                sign_up_email.getText().toString().equals("")) {
            FancyToast.makeText(SignUpActivity.this, "Email, Username and Password is required!" ,
                    FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
        }
        else {
            try {
                final ParseUser appUser = new ParseUser();
                appUser.setEmail(sign_up_email.getText().toString());
                appUser.setUsername(sign_up_username.getText().toString());
                appUser.setPassword(sign_up_password.getText().toString());

                final ProgressDialog progressBar = new ProgressDialog(this);
                progressBar.setMessage("Signing up " + sign_up_username.getText().toString());
                progressBar.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            progressBar.dismiss();
                            FancyToast.makeText(SignUpActivity.this, appUser.getUsername() + " signed up successfully",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            openSocialMediaActivity();
                        } else {
                            progressBar.dismiss();
                            FancyToast.makeText(SignUpActivity.this, "There was an Error! " + e.getLocalizedMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                    }
                });
            } catch (Exception e) {
                FancyToast.makeText(SignUpActivity.this, "There was an Error! " + e.getLocalizedMessage(),
                        FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                Log.d("ERROR", e.getLocalizedMessage());
            }
        }
    }
    public void hideKeyboard(View view){
        try {
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){

        }
    }
    private void openSocialMediaActivity(){
        Intent intent=new Intent(SignUpActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
