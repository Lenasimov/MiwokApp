package com.helenacastrosws.miwokapp.colors.presentation.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorsActivity extends AppCompatActivity {

    public static final String TAG = "ColorsActivity";

    @BindView(R.id.root_list_view)
    ListView colorsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("weṭeṭṭi", "rouge", R.drawable.color_red));
        words.add(new Word("chokokki", "vert", R.drawable.color_green));
        words.add(new Word("takaakki", "brun", R.drawable.color_brown));
        words.add(new Word("topoppi", "gris", R.drawable.color_gray));
        words.add(new Word("kululli", "noir", R.drawable.color_black));
        words.add(new Word("kelelli", "blanc", R.drawable.color_white));
        words.add(new Word("topiisә", "jaune poussiéreux", R.drawable.color_dusty_yellow));
        words.add(new Word("chiwiiṭә", "jaune moutard", R.drawable.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        colorsListView.setAdapter(adapter);
    }

}