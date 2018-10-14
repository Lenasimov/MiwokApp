package com.helenacastrosws.miwokapp.colors.presentation.view.ui.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.core.presentation.view.ui.activity.BaseActivity;
import com.helenacastrosws.miwokapp.numbers.presentation.view.ui.activity.NumbersActivity;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorsActivity extends BaseActivity {

    public static final String TAG = "ColorsActivity";

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    @BindView(R.id.root_list_view)
    ListView colorsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("weṭeṭṭi", "rouge", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("chokokki", "vert", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("takaakki", "brun", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("topoppi", "gris", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("kululli", "noir", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("kelelli", "blanc", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("topiisә", "jaune poussiéreux", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("chiwiiṭә", "jaune moutard", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        colorsListView.setAdapter(adapter);

        colorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());

                mMediaPlayer.start();

//                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void releaseMediaPlayer() {
        super.releaseMediaPlayer();
    }

}