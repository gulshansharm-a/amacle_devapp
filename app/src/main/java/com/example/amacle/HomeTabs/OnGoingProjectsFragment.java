package com.example.amacle.HomeTabs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amacle.ChatActivity;
import com.example.amacle.R;
import com.example.amacle.adapters.ChatAdapter;
import com.example.amacle.adapters.HomeProjectAdopter;
import com.example.amacle.adapters.HomeTabAdopter;
import com.example.amacle.model.HomeProjectModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OnGoingProjectsFragment extends Fragment {
    private View myView;
    private ArrayList<HomeProjectModel> homeProjectModels;
    private HomeProjectAdopter homeProjectAdapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_on_going_projects, container, false);
       recyclerView  = myView.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (homeProjectModels == null) {
            homeProjectModels = new ArrayList<>();
            loadProjectDataFromFirebase(recyclerView);
        } else {
            homeProjectAdapter = new HomeProjectAdopter(getActivity(), homeProjectModels);
            recyclerView.setAdapter(homeProjectAdapter);
        }

        return myView;
    }

    public void onResumeFragment() {
        if (homeProjectAdapter != null) {
            homeProjectAdapter.notifyDataSetChanged();
        } else {
            loadProjectDataFromFirebase(recyclerView);
        }
    }

    private void loadProjectDataFromFirebase(RecyclerView recyclerView) {
        FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("projects").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        homeProjectModels.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            HomeProjectModel homeProjectModel = new HomeProjectModel();
                            homeProjectModel.setName(snapshot1.child("name").getValue().toString());
                            homeProjectModel.setImage(snapshot1.child("image").getValue().toString());
                            homeProjectModel.setCompleted(snapshot1.child("completed").getValue().toString());
                            homeProjectModels.add(homeProjectModel);
                        }

                            homeProjectAdapter = new HomeProjectAdopter(getActivity(), homeProjectModels);
                            recyclerView.setAdapter(homeProjectAdapter);
                            homeProjectAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
