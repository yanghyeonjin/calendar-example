package com.yanghyeonjin.calendar.materialcalendarview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rd.PageIndicatorView;
import com.yanghyeonjin.calendar.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> events;
    private Context context;
    private PageIndicatorView pageIndicatorView;

    public EventAdapter(ArrayList<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    public EventAdapter(Context context, ArrayList<Event> events, PageIndicatorView pageIndicatorView) {
        this.context = context;
        this.events = events;
        this.pageIndicatorView = pageIndicatorView;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.tvEventTitle.setText(events.get(position).getEventTitle());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEventTitle;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEventTitle = itemView.findViewById(R.id.tvEventTitle);
        }
    }
}
