package com.example.amacle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Toast;

import com.example.amacle.adapters.ChatAdapter;
import com.example.amacle.databinding.ActivityChatBinding;
import com.example.amacle.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
public ActivityChatBinding binding ;
public String GroupId="akdsjkjdjfd";
public String userId;
public String position="dev";
public RecyclerView recyclerView;
ChatAdapter chatAdopter;
ArrayList<Chat> chatArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        userId = FirebaseAuth.getInstance().getUid().toString();
        recyclerView = binding.chatRecycle;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatArrayList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("groups").child(GroupId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot shapshotC:snapshot.getChildren()) {
                Chat chat = new Chat();
                    if (shapshotC.child("position").getValue() != null) {
                        chat.userId = shapshotC.child("position").getValue().toString();
                    }
                    if (shapshotC.child("message").getValue() != null) {
                        chat.message = shapshotC.child("message").getValue().toString();
                    }
                    if (shapshotC.child("position").getValue() != null) {
                        chat.position = shapshotC.child("position").getValue().toString();
                    }
                    chatArrayList.add(chat);
            }
                Toast.makeText(ChatActivity.this, "size "+chatArrayList.size(), Toast.LENGTH_SHORT).show();
                chatAdopter = new ChatAdapter(ChatActivity.this,chatArrayList);
                recyclerView.scrollToPosition(chatAdopter.getItemCount() - 1);
                recyclerView.setAdapter(chatAdopter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
