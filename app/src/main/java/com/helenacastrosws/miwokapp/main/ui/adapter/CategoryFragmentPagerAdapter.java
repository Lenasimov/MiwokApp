package com.helenacastrosws.miwokapp.main.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.helenacastrosws.miwokapp.R;
import com.helenacastrosws.miwokapp.colors.ui.fragment.ColorsFragment;
import com.helenacastrosws.miwokapp.family.ui.fragment.FamilyFragment;
import com.helenacastrosws.miwokapp.numbers.ui.fragment.NumbersFragment;
import com.helenacastrosws.miwokapp.phrases.ui.fragment.PhrasesFragment;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CategoryFragmentPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_numbers);
            case 1:
                return mContext.getString(R.string.category_family);
            case 2:
                return mContext.getString(R.string.category_colors);
            case 3:
                return mContext.getString(R.string.category_phrases);
            default:
                return null;
        }
    }

}