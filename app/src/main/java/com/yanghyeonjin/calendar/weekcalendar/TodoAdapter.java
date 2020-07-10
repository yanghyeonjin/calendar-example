package com.yanghyeonjin.calendar.weekcalendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
    public void onBindViewHolder(@NonNull final TodoAdapter.TodoViewHolder holder, int position) {
        holder.checkTodo.setChecked(todoList.get(position).isComplete());
        holder.tvTodo.setText(todoList.get(position).getTodoTitle());


        if (todoList.get(position).isComplete()) {
            // 체크되어 있으면 배경색 바꾸기
            holder.vBlur.setBackgroundColor(ContextCompat.getColor(context, R.color.completeTodoBg));
        } else {
            holder.vBlur.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkTodo;
        private TextView tvTodo;
        private CardView todo;
        private View vBlur;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);


            // 아이디 연결
            checkTodo = itemView.findViewById(R.id.checkTodo);
            tvTodo = itemView.findViewById(R.id.tvTodo);
            todo = itemView.findViewById(R.id.todo);
            vBlur = itemView.findViewById(R.id.vBlur);


            // blur 뷰 높이 설정
            todo.post(new Runnable() {
                @Override
                public void run() {
                    int h = todo.getHeight(); // to do 박스의 높이 계산

                    ViewGroup.LayoutParams params = vBlur.getLayoutParams();
                    params.height = h;
                    vBlur.setLayoutParams(params);
                }
            });


            // 할 일 클릭
            todo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent todoIntent = new Intent(context, TodoDetailActivity.class);
                    context.startActivity(todoIntent);
                }
            });



            // 체크박스 클릭했을 때, 상태에 따라 아이템 position 이동하기
            checkTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int curPos = getAdapterPosition();
                    Todo todoBefore = todoList.get(curPos); // 지울 아이템 복사해두기

                    if (((CheckBox) view).isChecked()) {
                        // 체크 되었을 때
                        // isComplete만 바꾼 새로운 아이템 생성
                        Todo todoAfter = new Todo(true, todoBefore.getTodoTitle());

                        // 해당 포지션에 있는 아이템 삭제 후, 맨 아래에 데이터 추가 -> 아래로 이동한 것처럼 보이도록
                        todoList.remove(todoBefore);
                        todoList.add(todoAfter);

                        // 아이템 remove, add 알리기
                        notifyItemRemoved(curPos);
                        notifyItemInserted(getItemCount() - 1);
                    } else {
                        // 체크 해제 되었을 때, 가장 상단으로 이동
                        Todo todoAfter = new Todo(false, todoBefore.getTodoTitle());

                        todoList.remove(todoBefore);
                        todoList.add(0, todoAfter);

                        notifyItemRemoved(curPos);
                        notifyItemInserted(0);
                    }
                }
            });

        }
    }
}
