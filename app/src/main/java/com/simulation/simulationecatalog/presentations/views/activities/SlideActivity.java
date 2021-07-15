package com.simulation.simulationecatalog.presentations.views.activities;

import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.cores.CoreActivity;
import com.simulation.simulationecatalog.interfaces.SlideInterface;
import com.simulation.simulationecatalog.presentations.presenters.SlidePresenter;
import com.simulation.simulationecatalog.presentations.views.adapters.ColorAdapter;
import com.simulation.simulationecatalog.presentations.views.adapters.SlideAdapter;
import com.simulation.simulationecatalog.presentations.views.fragments.ScreenSlidePageFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlideActivity extends CoreActivity implements SlideInterface.View,
        ColorAdapter.FragmentInteraction {

    public static final String KEY_POSITION = "key_position";

    @BindView(R.id.ib_pressBack)
    ImageButton ibPressBack;
    @BindView(R.id.vp_catalog)
    ViewPager2 vpCatalog;

    private SlideInterface.Presenter presenter;
    private List<ScreenSlidePageFragment> fragmentList;
    public static HashMap<String, String> mHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        ButterKnife.bind(this);

        presenter = new SlidePresenter(this);

        fragmentList = new ArrayList<>();
        mHashMap.clear();

        SlideAdapter adapter = new SlideAdapter(this, (ArrayList<ScreenSlidePageFragment>) fragmentList);
        vpCatalog.setAdapter(adapter);

        for (int i = 0; i < Singleton.instance().simulationTaskList.size(); i++) {
            fragmentList.add(ScreenSlidePageFragment.newInstance(i, this));
        }

        if (getIntent() != null) {
            int position = getIntent().getIntExtra(KEY_POSITION,0);
            vpCatalog.setCurrentItem(position);
        }
    }

    @OnClick(R.id.ib_pressBack)
    public void onPressBack() {
        this.onBackPressed();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void process(String id) {
        mHashMap.put(id, id);
    }
}