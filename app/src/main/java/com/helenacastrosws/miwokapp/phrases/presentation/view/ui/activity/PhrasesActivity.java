package com.helenacastrosws.miwokapp.phrases.presentation.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhrasesActivity extends AppCompatActivity {

    public static final String TAG = "PhrasesActivity";

    @BindView(R.id.root_list_view)
    ListView phrasesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("minto wuksus?", "Où allez-vous?"));
        words.add(new Word("tinnә oyaase'nә?", "Comment appelez-vous?"));
        words.add(new Word("oyaaset...", "Je m'appelle..."));
        words.add(new Word("michәksәs?", "Comment ça va?"));
        words.add(new Word("kuchi achit", "Ça va bien!"));
        words.add(new Word("әәnәs'aa?", "Venez-vous?"));
        words.add(new Word("hәә’ әәnәm", "Oui, je viens"));
        words.add(new Word("әәnәm", "Je viens"));
        words.add(new Word("yoowutis", "Allons-y"));
        words.add(new Word("әnni'nem", "Venez ici"));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);
        phrasesListView.setAdapter(adapter);
    }

}