package com.example.amacle.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.amacle.ChatActivity;
import com.example.amacle.R;
import com.example.amacle.adapters.ChatAdapter;
import com.example.amacle.adapters.ChatListOfProjectsDOPTER;
import com.example.amacle.databinding.ActivityChatListsOfProjectsBinding;
import com.example.amacle.model.ChatList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatListsOfProjects extends AppCompatActivity {
    public ActivityChatListsOfProjectsBinding binding;
    public RecyclerView recyclerView;
    public ArrayList< ChatList> chatArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityChatListsOfProjectsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.projectList;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("projects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()) {
                    ChatList chatList = new ChatList();
                    chatList.name = snap.child("name").toString();
                    chatList.groupid = snap.child("groupId").toString();
                    chatArrayList.add(chatList);
                  //  Toast.makeText(ChatListsOfProjects.this, chatArrayList.size() , Toast.LENGTH_SHORT).show();
                }
                ChatListOfProjectsDOPTER chatAdopter = new ChatListOfProjectsDOPTER(getApplicationContext(),chatArrayList);
                recyclerView.setAdapter(chatAdopter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}