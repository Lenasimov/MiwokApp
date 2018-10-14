package com.helenacastrosws.miwokapp.numbers.presentation.view.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);
        ButterKnife.bind(this);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("lutti", "un", R.drawable.number_one));
        words.add(new Word("otiiko", "deux", R.drawable.number_two));
        words.add(new Word("tolookosu", "trois", R.drawable.number_three));
        words.add(new Word("oyyisa", "quatre", R.drawable.number_four));
        words.add(new Word("massokka", "cinq", R.drawable.number_five));
        words.add(new Word("temmokka", "six", R.drawable.number_six));
        words.add(new Word("kenekaku", "sept", R.drawable.number_seven));
        words.add(new Word("kawinta", "huit", R.drawable.number_eight));
        words.add(new Word("wo'e", "neuf", R.drawable.number_nine));
        words.add(new Word("na'aacha", "dix", R.drawable.number_ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        numbersListView.setAdapter(adapter);
    }

}