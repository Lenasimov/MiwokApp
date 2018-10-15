package com.helenacastrosws.miwokapp.colors.presentation.view.ui.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.family.presentation.view.ui.activity.FamilyActivity;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorsActivity extends AppCompatActivity {

    public static final String TAG = "ColorsActivity";
    @BindView(R.id.root_list_view)
    ListView colorsListView;

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
                releaseMediaPlayer();

                Word word = words.get(position);

                int focusRequestResult =  mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(focusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
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