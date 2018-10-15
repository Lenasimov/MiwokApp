package com.helenacastrosws.miwokapp.numbers.presentation.view.ui.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumbersActivity extends AppCompatActivity {

    public static final String TAG = "NumbersActivity";
    @BindView(R.id.root_list_view)
    ListView numbersListView;

    // MediaPlayer instance;
    private MediaPlayer mMediaPlayer;

    // MediaPlayer Interface (anonymous class);
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    // AudioManager instance;
    private AudioManager mAudioManager;

    // AudioManager Interface (anonymous class);
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // pause the playback;
                mMediaPlayer.pause();
                // "restart" the audio from the beginning;
                mMediaPlayer.seekTo(0);

            } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // App regained focus and can resume playback
                mMediaPlayer.start();

            } else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // App have lost audio focus, stop playback and clean up resources;
                    releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

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
                releaseMediaPlayer();

                Word word = words.get(position);

                int focusRequestResult =  mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(focusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}