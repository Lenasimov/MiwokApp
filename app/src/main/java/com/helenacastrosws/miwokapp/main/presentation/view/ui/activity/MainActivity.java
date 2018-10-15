package com.helenacastrosws.miwokapp.main.presentation.view.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.helenacastrosws.miwokapp.colors.presentation.view.ui.activity.ColorsActivity;
import com.helenacastrosws.miwokapp.family.presentation.view.ui.activity.FamilyActivity;
import com.helenacastrosws.miwokapp.numbers.presentation.view.ui.activity.NumbersActivity;
import com.helenacastrosws.miwokapp.phrases.presentation.view.ui.activity.PhrasesActivity;
import com.helenacastrosws.miwokapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.numbers)
    TextView numbers;

    @BindView(R.id.family)
    TextView family;

    @BindView(R.id.colors)
    TextView colors;

    @BindView(R.id.phrases)
    TextView phrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }

}