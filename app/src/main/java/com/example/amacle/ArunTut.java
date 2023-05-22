package com.example.amacle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.amacle.adapters.ArunAdopter;
import com.example.amacle.databinding.ActivityArunTutBinding;
import com.example.amacle.model.ArunM;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArunTut extends AppCompatActivity {
ActivityArunTutBinding binding;
ArrayList<ArunM> arunMS;
ArunAdopter arunAdopter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArunTutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycle.setHasFixedSize(true);
        binding.recycle.setLayoutManager(new LinearLayoutManager(this));
        arunMS = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("ddd").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arunMS.clear();
                for (DataSnapshot sanp: snapshot.getChildren()) {
                    ArunM arunMSingle = new ArunM();
                    if (sanp.child("anu").getValue()!=null)
                    arunMSingle.setData(sanp.child("anu").getValue().toString());
                    arunMS.add(arunMSingle);
                }
                arunAdopter = new ArunAdopter(getApplicationContext(),arunMS);
                binding.recycle.setAdapter(arunAdopter);
                arunAdopter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}