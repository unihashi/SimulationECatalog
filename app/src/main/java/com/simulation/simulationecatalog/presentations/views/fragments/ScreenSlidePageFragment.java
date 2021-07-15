package com.simulation.simulationecatalog.presentations.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.ListSelectedItemListener;
import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.cores.CoreFragment;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;
import com.simulation.simulationecatalog.presentations.views.adapters.ColorAdapter;
import com.simulation.simulationecatalog.presentations.views.adapters.MainAdapter;
import com.simulation.simulationecatalog.presentations.views.adapters.SlideAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScreenSlidePageFragment extends CoreFragment implements ListSelectedItemListener {

    @BindView(R.id.tv_header)
    TextView tvHeader;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.iv_original)
    ImageView ivOriginal;
    @BindView(R.id.iv_simulation)
    ImageView ivSimulation;
    @BindView(R.id.rv_selectiveList)
    RecyclerView rvSelectiveList;

    private int currentPosition;

    private ColorAdapter adapter;
    private ColorAdapter.FragmentInteraction listener;

    public ScreenSlidePageFragment(int position) {
        currentPosition = position;
    }

    // TODO : receive values from Test Activity with newInstance
    public static ScreenSlidePageFragment newInstance(int i, ColorAdapter.FragmentInteraction listener) {
        Bundle args = new Bundle();
        args.putInt("i", i);
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment(i);
        fragment.setListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(ColorAdapter.FragmentInteraction listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        ButterKnife.bind(this, view);

        SimulationTask task = Singleton.instance().simulationTaskList.get(currentPosition);
        tvHeader.setText(task.getHeaderName());
        tvDescription.setText(task.getDescriptionInfo());
        ivOriginal.setImageResource(task.getOriginalImage());
        ivSimulation.setImageResource(task.getSimulationImage());

        adapter = new ColorAdapter(Singleton.instance().selectiveTaskList,this);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        rvSelectiveList.setLayoutManager(layoutManager);
        rvSelectiveList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onListSelectedItemListener(int position, String name) {
        if (position == 0) {
            ivOriginal.setVisibility(View.VISIBLE);
            ivSimulation.setVisibility(View.GONE);
        } else {
            ivOriginal.setVisibility(View.GONE);
            ivSimulation.setVisibility(View.VISIBLE);
            ivSimulation.setBackgroundResource(Singleton.instance().selectiveTaskList.get(position).getSourceColor());
        }
    }
}