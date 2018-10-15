package com.helenacastrosws.miwokapp.phrases.presentation.view.ui.activity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.colors.presentation.view.ui.activity.ColorsActivity;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhrasesActivity extends AppCompatActivity {

    public static final String TAG = "PhrasesActivity";
    @BindView(R.id.root_list_view)
    ListView phrasesListView;

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

        words.add(new Word("minto wuksus?", "Où allez-vous?", R.raw.phrase_where_are_you_going));
        words.add(new Word("tinnә oyaase'nә?", "Comment appelez-vous?", R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset...", "Je m'appelle...", R.raw.phrase_my_name_is));
        words.add(new Word("michәksәs?", "Comment ça va?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("kuchi achit", "Ça va bien!", R.raw.phrase_im_feeling_good));
        words.add(new Word("әәnәs'aa?", "Venez-vous?", R.raw.phrase_are_you_coming));
        words.add(new Word("hәә’ әәnәm", "Oui, je viens", R.raw.phrase_yes_im_coming));
        words.add(new Word("әәnәm", "Je viens", R.raw.phrase_im_coming));
        words.add(new Word("yoowutis", "Allons-y", R.raw.phrase_lets_go));
        words.add(new Word("әnni'nem", "Venez ici", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        phrasesListView.setAdapter(adapter);

        phrasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();

                Word word = words.get(position);

                int focusRequestResult =  mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(focusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
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