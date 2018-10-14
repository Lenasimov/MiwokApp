package com.helenacastrosws.miwokapp.numbers.presentation.view.ui.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.core.presentation.view.ui.activity.BaseActivity;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumbersActivity extends BaseActivity {

    public static final String TAG = "NumbersActivity";

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    @BindView(R.id.root_list_view)
    ListView numbersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("lutti", "un", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "deux", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "trois", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "quatre", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "cinq", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "sept", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "huit", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e", "neuf", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na'aacha", "dix", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        numbersListView.setAdapter(adapter);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
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