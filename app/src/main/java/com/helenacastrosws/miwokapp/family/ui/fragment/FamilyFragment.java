package com.helenacastrosws.miwokapp.family.ui.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyFragment extends Fragment {

    @BindView(R.id.root_list_view)
    ListView familyListView;

    // MediaPlayer instance;
    private MediaPlayer mMediaPlayer;

    // AudioManager instance;
    private AudioManager mAudioManager;

    // AudioManager Interface (anonymous class);
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // pause the playback;
                mMediaPlayer.pause();
                // "restart" the audio from the beginning;
                mMediaPlayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // App regained focus and can resume playback
                mMediaPlayer.start();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // App have lost audio focus, stop playback and clean up resources;
                releaseMediaPlayer();
            }
        }
    };

    // MediaPlayer Interface (anonymous class);
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list, container, false);
        ButterKnife.bind(this, rootView);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("әpә", "père", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("әṭa", "mère", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("angsi", "fils", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("tune", "fille", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("taachi", "frère aîné", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("chalitti", "frère cadet", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("teṭe", "soeur aînée", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("kolliti", "soeur cadette", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("ama", "grand-mère", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("paapa", "grand-père", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        familyListView.setAdapter(adapter);

        familyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();

                Word word = words.get(position);

                int focusRequestResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (focusRequestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        return rootView;
    }

    public void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}