package com.example.firsttestapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firsttestapp.R;
import com.example.firsttestapp.activities.ui.home.ReportAdapter;
import com.example.firsttestapp.data.ReportData;
import com.example.firsttestapp.operations.PersistentReportsProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment {

    private List<ReportData> reportDataList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        reportDataList = PersistentReportsProvider.getReports(getActivity());

        RecyclerView rvPastReports = (RecyclerView) view.findViewById(R.id.past_report_recycler);
        ReportAdapter adapter = new ReportAdapter(reportDataList);
        // Attach the adapter to the recyclerview to populate items
        rvPastReports.setAdapter(adapter);
        // Set layout manager to position the items
        rvPastReports.setLayoutManager(new LinearLayoutManager(getActivity()));

        TextView blankScreen =  view.findViewById(R.id.no_report);

        if(reportDataList.size() > 0){
            blankScreen.setVisibility(View.GONE);
        }

//        Intent intent = new Intent(getContext(), SubmitReportActivity.class);
//        startActivity(intent);

        return view;
    }
}
