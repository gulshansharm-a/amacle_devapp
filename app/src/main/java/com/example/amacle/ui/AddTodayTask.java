package com.example.amacle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amacle.R;
import com.example.amacle.adapters.TodoListAdopter;
import com.example.amacle.model.TodoLIstModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddTodayTask extends AppCompatActivity {
    public Button button;
    ArrayList<TodoLIstModel> todoLIstModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_today_task);
        button=(Button) findViewById(R.id.arun);
        RecyclerView recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoLIstModels  = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("todayTasks").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        todoLIstModels.clear();
                        for (DataSnapshot snap:snapshot.getChildren()) {
                            TodoLIstModel todoLIstModel = new TodoLIstModel();
                            if(snap.child("isCompleted").getValue()==null) {
                                todoLIstModel.setIscompleted(false);
                            }
                            if(snap.child("topic").getValue()!=null) {
                                todoLIstModel.setTopic(snap.child("topic").getValue().toString());
                            }
                            todoLIstModel.setToDoid(snap.getKey());
                            todoLIstModels.add(todoLIstModel);
                        }

                    recyclerView.setAdapter(new TodoListAdopter(getApplicationContext(),todoLIstModels));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog(){
        Popup exampleDialog= new Popup();
        exampleDialog.show(getSupportFragmentManager(),"example dialog0");

    }

}