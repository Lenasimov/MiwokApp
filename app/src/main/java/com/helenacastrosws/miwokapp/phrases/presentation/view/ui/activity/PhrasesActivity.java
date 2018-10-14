package com.helenacastrosws.miwokapp.phrases.presentation.view.ui.activity;

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

public class PhrasesActivity extends BaseActivity {

    public static final String TAG = "PhrasesActivity";

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    @BindView(R.id.root_list_view)
    ListView phrasesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

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
                Word word = words.get(position);

                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());

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