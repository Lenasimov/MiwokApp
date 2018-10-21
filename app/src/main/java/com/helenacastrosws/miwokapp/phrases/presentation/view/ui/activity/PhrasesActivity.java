package com.helenacastrosws.miwokapp.phrases.presentation.view.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.phrases.presentation.view.ui.fragment.PhrasesFragment;

public class PhrasesActivity extends AppCompatActivity {

    public static final String TAG = "PhrasesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setupFragment();
    }

    private void setupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PhrasesFragment())
                .commit();
    }

}