package com.example.amacle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.R;
import com.example.amacle.model.ChatList;

import java.util.ArrayList;

public class ChatListOfProjectsDOPTER extends RecyclerView.Adapter<ChatListOfProjectsDOPTER.Holder> {
    Context context;
    ArrayList<ChatList> chatLists;
    @NonNull
    @Override
    public ChatListOfProjectsDOPTER.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_chat_lists_of_projects,parent,false);
        return new Holder(v);
    }

    public ChatListOfProjectsDOPTER(Context context, ArrayList<ChatList> chatLists) {
        this.context = context;
        this.chatLists = chatLists;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListOfProjectsDOPTER.Holder holder, int position) {
    ChatList chatListSingle  = chatLists.get(position);
    holder.name.setText(chatListSingle.getName());
    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        public Holder(@NonNull View itemView) {
            super(itemView)
            ;
            name = itemView.findViewById(R.id.projectName);
        }
    }
}
