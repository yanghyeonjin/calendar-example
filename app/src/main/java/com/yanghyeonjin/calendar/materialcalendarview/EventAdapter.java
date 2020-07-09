package com.yanghyeonjin.calendar.materialcalendarview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rd.PageIndicatorView;
import com.yanghyeonjin.calendar.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> events;
    private Context context;
    private PageIndicatorView pageIndicatorView;
    private DiscreteScrollView discreteScrollView;

    public EventAdapter(ArrayList<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    public EventAdapter(Context context, ArrayList<Event> events, PageIndicatorView pageIndicatorView, DiscreteScrollView discreteScrollView) {
        this.context = context;
        this.events = events;
        this.pageIndicatorView = pageIndicatorView;
        this.discreteScrollView = discreteScrollView;
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


    public class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEventTitle;
        private CardView eventBody;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEventTitle = itemView.findViewById(R.id.tvEventTitle);
            eventBody = itemView.findViewById(R.id.eventBody);

            // 이벤트 클릭 했을 때
            eventBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 클릭한 아이템의 위치 얻어와서 indicator 및 현재 selection 변경
                    int curPos = getAdapterPosition(); // 현재 클릭한 아이템의 position (0부터 시작)

                    pageIndicatorView.setSelection(curPos);
                    discreteScrollView.smoothScrollToPosition(curPos);
                }
            });
        }
    }
}
