package com.helenacastrosws.miwokapp.numbers.presentation.view.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.numbers.presentation.view.ui.fragment.NumbersFragment;

public class NumbersActivity extends AppCompatActivity {

    public static final String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setupFragment();
    }

    private void setupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }

}