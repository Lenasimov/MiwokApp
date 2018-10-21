package com.helenacastrosws.miwokapp.word.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.word.model.domain.entity.Word;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    public static final String TAG = WordAdapter.class.getSimpleName();

    private int mColorResourceId;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int colorResourceId) {
        super(context, 0, objects);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View recycledView, ViewGroup parent) {
        View listItemView = recycledView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        setData(position, listItemView);

        return listItemView;
    }

    private void setData(int position, View listItemView) {
        Word currentWordPosition = getItem(position);

        TextView miwokTrans = listItemView.findViewById(R.id.tv_miwok_trans);
        miwokTrans.setText(currentWordPosition.getMiwokTranslation());

        TextView defaultTrans = listItemView.findViewById(R.id.tv_default_trans);
        defaultTrans.setText(currentWordPosition.getDefaultTranslation());

        ImageView imageView = listItemView.findViewById(R.id.imgv_icon);
        if(currentWordPosition.hasImage()) {
            imageView.setImageResource(currentWordPosition.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        View container = listItemView.findViewById(R.id.ll_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        container.setBackgroundColor(color);
    }

}