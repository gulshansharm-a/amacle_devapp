package com.example.amacle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.R;
import com.example.amacle.model.ArunM;

import java.util.ArrayList;
import java.util.Arrays;

public class ArunAdopter extends RecyclerView.Adapter<ArunAdopter.holder> {
    Context context;
    ArrayList<ArunM> arunMS;

    public ArunAdopter(Context context, ArrayList<ArunM> arunMS) {
        this.context = context;
        this.arunMS = arunMS;
    }

    @NonNull
    @Override
    public ArunAdopter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.aruncard,parent,false);
        return  new ArunAdopter.holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArunAdopter.holder holder, int position) {
     ArunM arunM = arunMS.get(position);
    holder.name.setText(arunM.getData());
    }

    @Override
    public int getItemCount() {
        return arunMS.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView name;
        public holder(@NonNull View itemView) {
            super(itemView);
         name = itemView.findViewById(R.id.arunText);
        }
    }
}
