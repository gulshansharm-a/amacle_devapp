package com.example.amacle.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.model.TopListModel;

import java.util.ArrayList;

public class TopListAdopter extends RecyclerView.Adapter<TopListAdopter.holder> {
    Context context;
    ArrayList<TopListModel> topListModels;
    @NonNull
    @Override
    public TopListAdopter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TopListAdopter.holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return topListModels.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        public holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
