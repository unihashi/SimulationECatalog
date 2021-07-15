package com.simulation.simulationecatalog.presentations.views.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.simulation.simulationecatalog.presentations.views.fragments.ScreenSlidePageFragment;

import java.util.ArrayList;

public class SlideAdapter extends FragmentStateAdapter {
    private ArrayList<ScreenSlidePageFragment> fragments;

    public SlideAdapter(FragmentActivity fa, ArrayList<ScreenSlidePageFragment> fragments) {
        super(fa);
        this.fragments = fragments;
    }

    @Override
    public Fragment createFragment(int position) {
        return new ScreenSlidePageFragment(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}