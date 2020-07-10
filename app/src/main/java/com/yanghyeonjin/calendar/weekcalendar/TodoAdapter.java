package com.yanghyeonjin.calendar.weekcalendar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yanghyeonjin.calendar.R;

import java.util.ArrayList;
import java.util.Collections;

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
        private CardView todo;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            checkTodo = itemView.findViewById(R.id.checkTodo);
            tvTodo = itemView.findViewById(R.id.tvTodo);
            todo = itemView.findViewById(R.id.todo);


            // 할 일 클릭
            todo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent todoIntent = new Intent(context, TodoDetailActivity.class);
                    context.startActivity(todoIntent);
                }
            });



            // checkbox 클릭했을 때, 제일 아래로 이동
            checkTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((CheckBox) view).isChecked()) {
                        // 체크 되어 있으면 아래로 이동
                        int curPos = getAdapterPosition();
                        Todo completeTodo = todoList.get(curPos); // 클릭한 아이템
                        completeTodo.setComplete(true); // 체크 상태로 변경

                        todoList.remove(todoList.get(curPos)); // 해당 위치에 아이템 삭제하고
                        todoList.add(todoList.size(), completeTodo); // 맨 아래에 추가
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
