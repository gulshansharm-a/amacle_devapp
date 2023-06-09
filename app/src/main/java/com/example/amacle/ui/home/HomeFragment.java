package com.example.amacle.ui.home;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.amacle.HomeTabs.CompletedProjectsFragment;
import com.example.amacle.HomeTabs.GrabProjectsFragment;
import com.example.amacle.HomeTabs.OnGoingProjectsFragment;
import com.example.amacle.R;
import com.example.amacle.adapters.HomeTabAdopter;
import com.example.amacle.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private OnGoingProjectsFragment onGoingProjectsFragment;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LineChart chart = root.findViewById(R.id.chart);
        ArrayList<Entry> data1 = new ArrayList<>();
        data1.add(new Entry(0, 4));
        data1.add(new Entry(1, 8));
        data1.add(new Entry(2, 6));
        data1.add(new Entry(3, 2));
        data1.add(new Entry(4, 7));
        ArrayList<Entry> data2 = new ArrayList<>();
        data2.add(new Entry(0, 8));
        data2.add(new Entry(1, 5));
        data2.add(new Entry(2, 0));
        data2.add(new Entry(3, 6));
        data2.add(new Entry(4, 4));
        LineDataSet dataSet1 = new LineDataSet(data1, "Line 1");
        dataSet1.setColor(Color.BLUE);
        dataSet1.setLineWidth(2f);
        dataSet1.setValueTextColor(Color.BLACK);
        LineDataSet dataSet2 = new LineDataSet(data2, "Line 2");
        dataSet2.setColor(Color.RED);
        dataSet2.setLineWidth(2f);
        dataSet2.setValueTextColor(Color.BLACK);
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);
        LineData lineData = new LineData(dataSets);
        chart.setData(lineData);
        chart.invalidate();
        //after

        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        HomeTabAdopter adapter = new HomeTabAdopter(fragmentManager);
        adapter.addFragment(new GrabProjectsFragment(), "Grab");
        adapter.addFragment(new OnGoingProjectsFragment(), "OnGoing");
        adapter.addFragment(new CompletedProjectsFragment(), "Completed");
        viewPager.setAdapter(adapter);
        onGoingProjectsFragment = new OnGoingProjectsFragment();
        // Connect the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }
    @Override
    public void onDestroyView(){super.onDestroyView();binding = null;}}