package com.helenacastrosws.miwokapp.family.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.family.ui.fragment.FamilyFragment;

public class FamilyActivity extends AppCompatActivity {

    public static final String TAG = "FamilyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setupFragment();
    }

    private void setupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FamilyFragment())
                .commit();
    }

}