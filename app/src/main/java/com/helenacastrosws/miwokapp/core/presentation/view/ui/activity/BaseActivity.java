package com.helenacastrosws.miwokapp.core.presentation.view.ui.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mMediaPlayer;

    public void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}