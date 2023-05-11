package com.example.amacle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.ChatActivity;
import com.example.amacle.R;
import com.example.amacle.model.ChatList;

import java.util.ArrayList;

public class ChatListOfProjectsDOPTER extends RecyclerView.Adapter<ChatListOfProjectsDOPTER.Holder> {
    Context context;
    ArrayList<ChatList> chatLists;
    @NonNull
    @Override
    public ChatListOfProjectsDOPTER.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_of_chat_list,parent,false);
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
    holder.name.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, ChatActivity.class);
            i.putExtra("groupId",chatListSingle.groupid);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line
            context.startActivity(i);
        }
    });
    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.numberofmessages);

        }
    }
}
