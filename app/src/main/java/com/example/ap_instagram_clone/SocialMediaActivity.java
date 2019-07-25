package com.example.ap_instagram_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


public class SocialMediaActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewPager;
    private com.google.android.material.tabs.TabLayout tabLayout;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        setTitle("Social Media App");

        toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.myViewPager);
        tabAdapter=new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,false);

    }
}
