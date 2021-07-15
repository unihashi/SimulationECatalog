package com.simulation.simulationecatalog.presentations.views.activities;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.ListSelectedItemListener;
import com.simulation.simulationecatalog.cores.CoreActivity;
import com.simulation.simulationecatalog.data.components.CustomEditText;
import com.simulation.simulationecatalog.data.components.InformationDialog;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;
import com.simulation.simulationecatalog.interfaces.MainInterface;
import com.simulation.simulationecatalog.presentations.presenters.MainPresenter;
import com.simulation.simulationecatalog.presentations.views.adapters.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CoreActivity implements ListSelectedItemListener, MainInterface.View {

    @BindView(R.id.et_searchCatalog)
    CustomEditText etSearchCatalog;
    @BindView(R.id.rv_catalog)
    RecyclerView reCatalog;
    @BindView(R.id.option_list)
    LinearLayout optionList;
    @BindView(R.id.tv_option)
    TextView tvOption;
    @BindView(R.id.tv_office)
    TextView tvOffice;
    @BindView(R.id.tv_public)
    TextView tvPublic;
    @BindView(R.id.tv_residential)
    TextView tvResidential;
    @BindView(R.id.tv_furniture)
    TextView tvFurniture;
    @BindView(R.id.tv_hotel)
    TextView tvHotel;
    @BindView(R.id.tv_chainStore)
    TextView tvChainStore;
    @BindView(R.id.tv_education)
    TextView tvEducation;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;

    private MainInterface.Presenter presenter;
    private MainAdapter adapter;
    private List<SimulationTask> catalogLists = new ArrayList<>();

    private String search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        etSearchCatalog.setOnEditorActionListener((v,actionId,event) -> {
            if (actionId != 0 || event.getAction() == KeyEvent.ACTION_DOWN) {
                searchCatalog();
                hideSoftKeyboard();
                return true;
            } else {
                return false;
            }
        });

//        loading.show();

        adapter = new MainAdapter(null,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        reCatalog.setLayoutManager(layoutManager);
        reCatalog.setAdapter(adapter);

        presenter.initUseCase();
        presenter.onGetCatalogFromDatabase();

    }

    private void searchCatalog() {
//        loading.dismiss();
        search = etSearchCatalog.getText().toString().trim().toLowerCase();
        if (search.length() < 3 && !search.isEmpty()) {
            reCatalog.setVisibility(View.GONE);
            onSearchLengthError();
        } else {
            loading.show();
            reCatalog.setVisibility(View.GONE);
            adapter.UpdateCatalogList(null);
            presenter.onGetCatalogFromDatabase();
        }
    }

    private void onSearchLengthError() {
        loading.dismiss();
        InformationDialog.show(
                this,
                getResources().getString(R.string.alert_message),
                getResources().getString(R.string.txt_search_length_error),
                null);
    }

    @Override
    public void onListSelectedItemListener(int position, String name) {
        // go to slide
        Intent intent = new Intent(context(), SlideActivity.class);
        intent.putExtra(SlideActivity.KEY_POSITION, position);
//        intent.putExtra(SlideActivity.KEY_ADD_AVAILABLE, true);
        startActivity(intent);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onResultList(List<SimulationTask> result) {
        reCatalog.setVisibility(View.VISIBLE);
        adapter.UpdateCatalogList(result);
        if (!search.isEmpty()) {
            adapter.searchItem(search);
        }
        loading.dismiss();
    }
}