package com.example.amacle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.ChatActivity;
import com.example.amacle.R;
import com.example.amacle.model.Chat;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    Context context;

    ArrayList<Chat> chatList;
    public ChatAdapter(ChatActivity contextv, ArrayList<Chat> chatListv) {
        this.context = contextv;
        this.chatList = chatListv;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_chat,parent,false);
        return  new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
    Chat chat = chatList.get(position);
    holder.name.setText(chat.getUserId());
    holder.message.setText(chat.getMessage());
    holder.position.setText(chat.position);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView position,message,name;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            message = itemView.findViewById(R.id.message);
            name = itemView.findViewById(R.id.name);

        }
    }
}
