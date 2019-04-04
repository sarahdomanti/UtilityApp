package com.example.utilityapp;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<ArrayList<String>> todos;

    ToDoList() {
        this.todos = new ArrayList<>();
    }

    void add(String task, String dueDate) {
        ArrayList<String> newTodo = new ArrayList<>();
        newTodo.add(task);
        newTodo.add(dueDate);
        todos.add(newTodo);
    }

    void add(String task) {
        ArrayList<String> newTodo = new ArrayList<>();
        newTodo.add(task);
        newTodo.add("");
        todos.add(newTodo);
    }

    int size() {
        return todos.size();
    }

    ArrayList<String> getTodoNames() {
        ArrayList<String> todoNames = new ArrayList<>();
        for (int i = 0; i < this.todos.size(); i++) {
            ArrayList todo = this.todos.get(i);
            String todoName = todo.get(0).toString();
            todoNames.add(todoName);
        }
        return todoNames;
    }

    ArrayList<String> getTodoDates() {
        ArrayList<String> todoDates = new ArrayList<>();
        for (int i = 0; i < this.todos.size(); i++) {
            ArrayList todo = this.todos.get(i);
            String todoDate = todo.get(1).toString();
            todoDates.add(todoDate);
        }
        return todoDates;
    }
}
