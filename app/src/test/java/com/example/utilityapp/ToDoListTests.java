package com.example.utilityapp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ToDoListTests {
    @Test
    public void addTodo() {
        ToDoList toDoList = new ToDoList();
        assertEquals(0, toDoList.size());
        toDoList.add("Task 1", "01/01/01");
        assertEquals(1, toDoList.size());
    }

    @Test
    public void getTodoString() {
        String testName = "Task Name";
        ToDoList toDoList = new ToDoList();
        toDoList.add(testName, "01/01/01");
        ArrayList todoStrings = toDoList.getTodoNames();
        assertEquals(testName, todoStrings.get(0));
    }

    @Test
    public void getTodoDate() {
        String testDate = "01/02/03";
        ToDoList toDoList = new ToDoList();
        toDoList.add("Task Name", testDate);
        ArrayList todoDates = toDoList.getTodoDates();
        assertEquals(testDate, todoDates.get(0));
    }

    @Test
    public void addTodoNoDate() {
        ToDoList toDoList = new ToDoList();
        assertEquals(0, toDoList.size());
        toDoList.add("Task 1");
        toDoList.add("Task 2");
        assertEquals(2, toDoList.size());
        assertEquals("", toDoList.getTodoDates().get(0));
    }
}