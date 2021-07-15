package com.simulation.simulationecatalog.presentations.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.ListSelectedItemListener;
import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SimulationTask;
import com.simulation.simulationecatalog.domain.models.SelectedList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<SimulationTask> catalogLists;
    private ListSelectedItemListener listener;

    public MainAdapter(List<SimulationTask> catalogLists, ListSelectedItemListener listener) {
        this.catalogLists = catalogLists;
        this.listener = listener;
    }

    public void UpdateCatalogList(List<SimulationTask> catalogLists) {
        this.catalogLists = catalogLists;
        notifyDataSetChanged();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_catalogName)
        TextView tvCatalogName;
        @BindView(R.id.iv_catalogImage)
        ImageView ivCatalogImage;

        public MainViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            listener.onListSelectedItemListener(getAdapterPosition(),tvCatalogName.getText().toString());
        }
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main_catalog,  parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final SimulationTask item = catalogLists.get(position);
        if (item != null) {
            holder.tvCatalogName.setText(item.getHeaderName());
            holder.ivCatalogImage.setImageResource(item.getOriginalImage());
            holder.ivCatalogImage.setBackgroundResource(R.color.grey);
        }
    }

    @Override
    public int getItemCount() {
        return catalogLists == null ? 0 : catalogLists.size();
    }

    public void searchItem(String search) {
        List<SelectedList> selectedList = new ArrayList<>();
        for (SimulationTask list:catalogLists){
            SelectedList selected = new SelectedList();
            if(list.getHeaderName().toLowerCase().contains(search.toLowerCase())){
                selected.isSelected = true;
            } else {
                selected.isSelected = false;
            }
            selectedList.add(selected);
        }
        removeUnSelectedItem(selectedList);
    }

    private void removeUnSelectedItem(List<SelectedList> selectedList) {
        List<SimulationTask> temUserList = new ArrayList<>();
        for (int i = 0; i < selectedList.size(); i++) {
            if (selectedList.get(i).isSelected) {
                temUserList.add(catalogLists.get(i));
            }
        }
        catalogLists.clear();
        Singleton.instance().simulationTaskList = new ArrayList<>();
        Singleton.instance().simulationTaskList.addAll(temUserList);
        catalogLists.addAll(temUserList);
        notifyDataSetChanged();
        listener.onRefreshItem(catalogLists.size());
    }

}
