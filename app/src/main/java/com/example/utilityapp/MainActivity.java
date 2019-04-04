package com.example.utilityapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String EXTRA_CURRENT_TEXTSIZE = "currentTextSize";
    private static final String TODO_NAMES = "todoNames";
    private static final String TODO_DATES = "todoDates";
    private static final String TEXT_SIZE = "textSize";
    ToDoList toDoList;
    LinearLayout checkGroup;
    Button addTaskButton;
    EditText taskEditText, dueEditText;
    Switch dueSwitch;
    int textSize;
    boolean clearTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkGroup = findViewById(R.id.group);
        addTaskButton = findViewById(R.id.addBtn);
        taskEditText = findViewById(R.id.taskEditText);
        dueSwitch = findViewById(R.id.dueSwitch);
        dueEditText = findViewById(R.id.dueEditText);

        toDoList = new ToDoList();

        if (savedInstanceState == null) {
            textSize = SettingsActivity.REGULAR_TEXT_SIZE;
        } else {
            textSize = savedInstanceState.getInt(TEXT_SIZE);
            ArrayList<String> todoNames = savedInstanceState.getStringArrayList(TODO_NAMES);
            ArrayList<String> todoDates = savedInstanceState.getStringArrayList(TODO_DATES);
            if (todoNames != null && todoDates != null && todoNames.size() != 0 && todoDates.size() != 0) {
                for (int i = 0; i < todoNames.size(); i++) {
                    toDoList.add(todoNames.get(i), todoDates.get(i));
                }
                displayCheckBoxes();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(TODO_NAMES, toDoList.getTodoNames());
        outState.putStringArrayList(TODO_DATES, toDoList.getTodoDates());
        outState.putInt(TEXT_SIZE, textSize);
    }

    public void onAddTaskClicked(View view) {
        String taskName = taskEditText.getText().toString();
        if (dueSwitch.isChecked()) {
            String dueDate = dueEditText.getText().toString();
            toDoList.add(taskName, dueDate);
            dueEditText.setText("");
        } else {
            toDoList.add(taskName);
        }
        taskEditText.setText("");
        displayCheckBoxes();
    }

    public void displayCheckBoxes() {
        checkGroup.removeAllViewsInLayout();
        ArrayList todoNames = toDoList.getTodoNames();
        ArrayList todoDates = toDoList.getTodoDates();
        for (int i = 0; i < toDoList.size(); i++) {
            CheckBox checkbtn = new CheckBox(getApplicationContext());
            checkbtn.setId(i);
            StringBuilder taskText = new StringBuilder();
            taskText.append(todoNames.get(i).toString());
            if (!todoDates.get(i).equals("")) {
                String dueText = " (Due " + todoDates.get(i) + ")";
                taskText.append(dueText);
            }
            checkbtn.setText(taskText);
            checkbtn.setTextColor(Color.BLACK);
            checkbtn.setTextSize(textSize);
            LinearLayout.LayoutParams checkParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            checkParams.setMargins(10, 10, 10, 10);
            checkParams.gravity = Gravity.START;
            checkbtn.setLayoutParams(checkParams);
            checkGroup.addView(checkbtn);
        }
    }

    public void onSwitchClicked(View view) {
        if (dueSwitch.isChecked()) {
            dueEditText.setEnabled(true);
        } else {
            dueEditText.setEnabled(false);
        }
    }

    public void onSettingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra(EXTRA_CURRENT_TEXTSIZE, textSize);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    textSize = data.getIntExtra(SettingsActivity.EXTRA_TEXTSIZE, SettingsActivity.REGULAR_TEXT_SIZE);
                    clearTasks = data.getBooleanExtra(SettingsActivity.EXTRA_CLEAR, false);
                    if (clearTasks) {
                        toDoList = new ToDoList();
                    }
                    displayCheckBoxes();
                }
            }
        }
    }
}
