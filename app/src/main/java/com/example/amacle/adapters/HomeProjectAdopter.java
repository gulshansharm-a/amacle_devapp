package com.example.amacle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.R;
import com.example.amacle.model.HomeProjectModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeProjectAdopter extends RecyclerView.Adapter<HomeProjectAdopter.holder> {
    Context context;
    ArrayList<HomeProjectModel> homeProjectModels;

    public HomeProjectAdopter(Context context, ArrayList<HomeProjectModel> homeProjectModels) {
        this.context = context;
        this.homeProjectModels = homeProjectModels;
    }

    @NonNull
    @Override
    public HomeProjectAdopter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_project_list,parent,false);
        return new HomeProjectAdopter.holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProjectAdopter.holder holder, int position) {
        HomeProjectModel homeProjectModel  = homeProjectModels.get(position);
        holder.percentage.setText(homeProjectModel.getCompleted().toString()+"%");

        holder.name.setText(homeProjectModel.getName().toString());
        Picasso.get()
                .load(homeProjectModel.getImage()
                        .toString())
                .error(R.drawable.buttonback) // Placeholder image to show in case of an error
                .into(holder.circleImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Image loaded successfully
                    }

                    @Override
                    public void onError(Exception e) {
                        // Handle error here
                        e.printStackTrace();
                        Toast.makeText(context, e.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return homeProjectModels.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name,percentage;
        CardView parent;
        public holder(@NonNull View itemView) {
            super(itemView);
        circleImageView= itemView.findViewById(R.id.circleImageView_home_project_list);
        name = itemView.findViewById(R.id.projectName);
        percentage = itemView.findViewById(R.id.percentage);
       // parent = itemView.findViewById(R.id.p)
        }
    }
}
