package com.example.amacle.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.R;
import com.example.amacle.model.TodoLIstModel;

import java.util.ArrayList;

public class TodoListAdopter extends RecyclerView.Adapter<TodoListAdopter.TodoHolder> {
    Context context;
    ArrayList<TodoLIstModel> todoLIstModels;


    public TodoListAdopter(Context context, ArrayList<TodoLIstModel> todoLIstModels) {
        this.context = context;
        this.todoLIstModels = todoLIstModels;
    }

    @NonNull
    @Override
    public TodoListAdopter.TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.todolistcart,parent,false);
        return new TodoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdopter.TodoHolder holder, int position) {
    TodoLIstModel todoLIstModel =  todoLIstModels.get(position);
    if(todoLIstModel.getIscompleted()) {
         holder.checkBox.setChecked(true);
    }else {
         holder.checkBox.setChecked(false);
    }
         holder.textView.setText(todoLIstModel.getTopic());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           // add the popup code here

            // Check if position exists in the list
            if (position != RecyclerView.NO_POSITION) {
                // Get the corresponding list item
                TodoLIstModel todoLIstModel = todoLIstModels.get(position);

                // Inflate your custom layout
                View dialogView = LayoutInflater.from(context).inflate(R.layout.popuptodo, null);

                // Find the TextView in the layout and set its text

                // Build the dialog and set its content
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogView);

                // Show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    });
    }


    @Override
    public int getItemCount() {
        return todoLIstModels.size();
    }

    public class TodoHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;
        public TodoHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.todaytask);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

    }

}
