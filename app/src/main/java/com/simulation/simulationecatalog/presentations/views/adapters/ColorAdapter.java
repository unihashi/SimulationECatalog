package com.simulation.simulationecatalog.presentations.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.ListSelectedItemListener;
import com.simulation.simulationecatalog.data.components.roomdatabases.entity.SelectiveTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {

    private List<SelectiveTask> colorLists;
    private ListSelectedItemListener listener;

    public ColorAdapter(List<SelectiveTask> colorLists, ListSelectedItemListener listener) {
        this.colorLists = colorLists;
        this.listener = listener;
    }

    class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_selectiveColor)
        ImageView ivSelectiveColor;

        public ColorViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            listener.onListSelectedItemListener(getAdapterPosition(),null);
        }
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_selective_color,  parent, false);
        ColorViewHolder viewHolder = new ColorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        final SelectiveTask item = colorLists.get(position);
        if (item != null) {
            holder.ivSelectiveColor.setBackgroundResource(item.getSourceColor());
        }
    }

    @Override
    public int getItemCount() {
        return colorLists == null ? 0 : colorLists.size();
    }

    public interface FragmentInteraction {
        void process(String id);
    }

}
