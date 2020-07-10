package com.yanghyeonjin.calendar.weekcalendar;

public class Todo {
    private boolean isComplete;
    private String todoTitle;

    public Todo(boolean isComplete, String todoTitle) {
        this.isComplete = isComplete;
        this.todoTitle = todoTitle;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }
}
