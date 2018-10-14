package com.helenacastrosws.miwokapp.family.presentation.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;
import com.helenacastrosws.miwokapp.word.presentation.view.ui.adapter.WordAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyActivity extends AppCompatActivity {

    public static final String TAG = "FamilyActivity";

    @BindView(R.id.root_list_view)
    ListView familyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("әpә", "père", R.drawable.family_father));
        words.add(new Word("әṭa", "mère", R.drawable.family_mother));
        words.add(new Word("angsi", "fils", R.drawable.family_son));
        words.add(new Word("tune", "fille", R.drawable.family_daughter));
        words.add(new Word("taachi", "frère aîné", R.drawable.family_older_brother));
        words.add(new Word("chalitti", "frère cadet", R.drawable.family_younger_brother));
        words.add(new Word("teṭe", "soeur aînée", R.drawable.family_older_sister));
        words.add(new Word("kolliti", "soeur cadette", R.drawable.family_younger_sister));
        words.add(new Word("ama", "grand-mère", R.drawable.family_grandmother));
        words.add(new Word("paapa", "grand-père", R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        familyListView.setAdapter(adapter);
    }

}