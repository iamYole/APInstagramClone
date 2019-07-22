package com.example.ap_instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    public void textClicked(View view){

        try {
            final ParseObject boxer = new ParseObject("Boxer");
            boxer.put("punch_speed", 200);

            boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, "The Boxer Object is saved", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    } else {
                        Log.d("ERROR", e.getLocalizedMessage());
                    }
                }
            });
        }
        catch (Exception ex){
            Log.d("ERROR", ex.getLocalizedMessage());
        }
    }
}
