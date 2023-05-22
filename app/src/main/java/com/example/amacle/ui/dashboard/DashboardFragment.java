package com.example.amacle.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amacle.adapters.ChatListOfProjectsDOPTER;
import com.example.amacle.databinding.FragmentDashboardBinding;
import com.example.amacle.model.ChatList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    public RecyclerView recyclerView;
    public ArrayList< ChatList> chatArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.projectList;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).child("projects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap: snapshot.getChildren()) {
                    ChatList chatList = new ChatList();
                    if(snap.child("name")!= null) {
                        chatList.name = snap.child("name").getValue().toString();
                        chatList.groupid = snap.child("groupId").getValue().toString();
                        chatArrayList.add(chatList);
                    }
                    //  Toast.makeText(ChatListsOfProjects.this, chatArrayList.size() , Toast.LENGTH_SHORT).show();
                }
                ChatListOfProjectsDOPTER chatAdopter = new ChatListOfProjectsDOPTER(getActivity(),chatArrayList);
                recyclerView.setAdapter(chatAdopter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}