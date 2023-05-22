package com.example.amacle.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.R;
import com.example.amacle.model.HomeProjectModel;

import java.util.ArrayList;

public class HomeProjectAdopter extends RecyclerView.Adapter<HomeProjectAdopter.holder> {
    Context context;
    ArrayList<HomeProjectModel> homeProjectModels;
    @NonNull
    @Override
    public HomeProjectAdopter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProjectAdopter.holder holder, int position) {
        int completionPercentage = 70; // Set your task completion percentage here
        holder.progressBar.setProgress(completionPercentage);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class holder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public holder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
