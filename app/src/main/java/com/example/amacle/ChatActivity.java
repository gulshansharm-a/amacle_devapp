package com.example.amacle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.amacle.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class ChatActivity extends AppCompatActivity {
public ActivityChatBinding binding ;
public String GroupId="akdsjkjdjfd";
public String userId;
public String position="dev";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        userId = FirebaseAuth.getInstance().getUid().toString();
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference().child("groups").child(GroupId).push();
             chatRef.child("userID").setValue(userId);
             chatRef.child("position").setValue(position);
             chatRef.child("message").setValue(binding.message.getText().toString());
             binding.message.setText("");

            }
        });
    }
}
