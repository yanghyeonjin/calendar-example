package com.yanghyeonjin.calendar.weekcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yanghyeonjin.calendar.R;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<Todo> todoList;
    private Context context;

    public TodoAdapter(Context context, ArrayList<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }


    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int position) {
        holder.checkTodo.setChecked(todoList.get(position).isComplete());
        holder.tvTodo.setText(todoList.get(position).getTodoTitle());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkTodo;
        private TextView tvTodo;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            checkTodo = itemView.findViewById(R.id.checkTodo);
            tvTodo = itemView.findViewById(R.id.tvTodo);
        }
    }
}
