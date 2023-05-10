package com.example.amacle.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amacle.R;

public class AddTodayTask extends AppCompatActivity {
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_today_task);
        button=(Button) findViewById(R.id.arun);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog(){
        Popup exampleDialog= new Popup();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");


    }

}