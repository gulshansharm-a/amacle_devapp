package com.example.amacle;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.amacle.adapters.ChatAdapter;
import com.example.amacle.databinding.ActivityChatBinding;
import com.example.amacle.model.Chat;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
public ActivityChatBinding binding ;
    public String groupId = "akdsjkjdjfd";
    public String userId;
    public String position = "dev";
    public RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    private FirebaseDatabase database;
    private DatabaseReference chatRef;

    ArrayList<Chat> chatArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            userId = FirebaseAuth.getInstance().getUid().toString();
        }

        recyclerView = binding.chatRecycle;
        database = FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatArrayList = new ArrayList<>();

        // Enable offline persistence
        FirebaseDatabase.getInstance().getReference().child("groups").child(groupId).keepSynced(true);

        // Retrieve data from the database
        DatabaseReference groupRef = database.getReference().child("groups").child(groupId);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                // Check if the first visible item is at the top of the list
                if (layoutManager != null && layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    // Query Firebase for more data
                    int itemsToLoad = 10;
                    DatabaseReference ref = groupRef;
                    Query query = ref.limitToLast(chatArrayList.size() + itemsToLoad).orderByKey();
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<Chat> newChatList = new ArrayList<>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Chat chat = snapshot.getValue(Chat.class);
                                newChatList.add(chat);
                            }
                            chatArrayList.clear();
                            chatArrayList.addAll(0, newChatList);
                            chatAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });

        groupRef.limitToLast(15).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    chatArrayList.add(chat);
                }
                chatAdapter = new ChatAdapter(ChatActivity.this, chatArrayList);
                recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                recyclerView.setAdapter(chatAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setdata(binding.message.getText().toString());

            }
        });
        FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("unreadedChat").child(groupId).removeValue();

    }
    public void setdata(String message) {

            DatabaseReference groupRef = database.getReference().child("groups").child(groupId);
            DatabaseReference chatRef = groupRef.push();
            Chat chat = new Chat();
            chat.setUserId(userId);
            chat.setPosition(position);
            String s= message;
            chat.setMessage(s);
            chatRef.setValue(chat);
            FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("unreadedChat").child(groupId).setValue(chat);
            binding.message.setText("");

    }
}