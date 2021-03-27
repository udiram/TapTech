package com.example.firsttestapp.activities.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.ReportData;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private List<ReportData> reportDataList;

    public ReportAdapter(List<ReportData> reportData) {
        reportDataList = reportData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View reportView = inflater.inflate(R.layout.report_data_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(reportView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReportData reportData = reportDataList.get(position);

        // Set item views based on your views and data model
        holder.modelTextView.setText(reportData.getModelNumber());
        holder.serialTextView.setText(reportData.getSerialNumber());
        holder.requestTextView.setText(reportData.getRequestType());
        holder.meterTextView.setText(reportData.getMeterRead());
        holder.timestampTextView.setText(reportData.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return reportDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView modelTextView;
        public TextView serialTextView;
        public TextView meterTextView;
        public TextView requestTextView;
        public TextView timestampTextView;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            modelTextView = (TextView) itemView.findViewById(R.id.model);
            serialTextView = (TextView) itemView.findViewById(R.id.serial);
            meterTextView = (TextView) itemView.findViewById(R.id.meter);
            requestTextView = (TextView) itemView.findViewById(R.id.request);
            timestampTextView = (TextView) itemView.findViewById(R.id.timestamp);

        }
    }
}
