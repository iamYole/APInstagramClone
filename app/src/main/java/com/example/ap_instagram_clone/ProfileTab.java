package com.example.ap_instagram_clone;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText profileName,profileBio,profileProfession,profileHobbies,profileFavSport;
    private Button updateInfo;

    private ParseUser user;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_profile_tab, container, false);

        profileName=view.findViewById(R.id.txtProfileName) ;
        profileBio=view.findViewById(R.id.txtProfileBio) ;
        profileProfession=view.findViewById(R.id.txtProfileProfession) ;
        profileHobbies=view.findViewById(R.id.txtProfileHubbies) ;
        profileFavSport=view.findViewById(R.id.txtProfileFavSport) ;

        updateInfo=view.findViewById(R.id.btnProfileUpdate) ;
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

        user=ParseUser.getCurrentUser();
        updateUserDetails(user);

        return view;
    }

    private void updateInfo() {

        if (profileName.getText().toString().equals("") || profileBio.getText().toString().equals("") ||
                profileProfession.getText().toString().equals("") || profileHobbies.getText().toString().equals("") ||
                profileFavSport.getText().toString().equals("")) {
            FancyToast.makeText(getContext(), "Please input all required fields",
                    FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();

        } else {
            final ProgressDialog progressBar = new ProgressDialog(getContext());
            progressBar.setMessage("Saving");
            try {

                progressBar.show();

                user.put("profileName", profileName.getText().toString());
                user.put("profileBio", profileBio.getText().toString());
                user.put("profileProfession", profileProfession.getText().toString());
                user.put("profileHobbies", profileHobbies.getText().toString());
                user.put("profileFavSport", profileFavSport.getText().toString());
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            progressBar.dismiss();
                            FancyToast.makeText(getContext(), "Info Updated",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                        } else {
                            progressBar.dismiss();
                            FancyToast.makeText(getContext(), "There was an error",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            Log.d("ERROR",e.getLocalizedMessage());
                        }
                    }
                });
            } catch (Exception e) {
                progressBar.dismiss();
                FancyToast.makeText(getContext(), "There was an error",
                        FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
            }

        }
    }
    private void updateUserDetails(ParseUser user){
       if (user.get("profileName")==null){
           profileName.setHint("Enter your Fullname");
       }
       else {
           profileName.setText(user.get("profileName").toString());
       }

        if (user.get("profileBio")==null){
            profileBio.setHint("Enter your bio...");
        }
        else {
            profileBio.setText(user.get("profileBio").toString());
        }

        if (user.get("profileProfession")==null){
            profileProfession.setHint("Enter your profession ...");
        }
        else {
            profileProfession.setText(user.get("profileProfession").toString());
        }

        if (user.get("profileHobbies")==null){
            profileHobbies.setHint("Enter your hobbiess ...");
        }
        else {
            profileHobbies.setText(user.get("profileHobbies").toString());
        }

        if (user.get("profileFavSport")==null){
            profileFavSport.setHint("Enter your favorite sport ...");
        }
        else {
            profileFavSport.setText(user.get("profileFavSport").toString());
        }
    }
//    public void hideKeyboard(View view){
//        try {
//            InputMethodManager inputMethodManager=(InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(getContext().getCurrentFocus().getWindowToken(),0);
//        }catch (Exception e){
//
//        }
//    }
}
