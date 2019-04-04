package com.example.utilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    static final int SETTINGS_REQUEST = 0;
    static final int SMALL_TEXT_SIZE = 14;
    static final int REGULAR_TEXT_SIZE = 18;
    static final int LARGE_TEXT_SIZE = 24;
    public static final String EXTRA_TEXTSIZE = "textSize";
    public static final String EXTRA_CLEAR = "clearTasks";
    int textSize;
    boolean clearTasks;

    RadioGroup radioGroup;
    RadioButton smallRadio, regularRadio, largeRadio;
    CheckBox clearCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroup = findViewById(R.id.radioGroup);
        smallRadio = findViewById(R.id.smallRadio);
        regularRadio = findViewById(R.id.regularRadio);
        largeRadio = findViewById(R.id.largeRadio);
        clearCheckBox = findViewById(R.id.clearCheckBox);

        smallRadio.setTextSize(SMALL_TEXT_SIZE);
        regularRadio.setTextSize(REGULAR_TEXT_SIZE);
        largeRadio.setTextSize(LARGE_TEXT_SIZE);

        Intent intent = getIntent();
        int currentTextSize = intent.getIntExtra(MainActivity.EXTRA_CURRENT_TEXTSIZE, REGULAR_TEXT_SIZE);

        switch (currentTextSize) {
            case SMALL_TEXT_SIZE:
                smallRadio.setChecked(true);
                break;
            case LARGE_TEXT_SIZE:
                largeRadio.setChecked(true);
                break;
            default:
                regularRadio.setChecked(true);
        }
    }

    public void onDoneClicked(View view) {
        RadioButton selectedRadio = findViewById(radioGroup.getCheckedRadioButtonId());
        String newTextSize = selectedRadio.getText().toString();
        switch (newTextSize) {
            case "Small":
                textSize = SMALL_TEXT_SIZE;
                break;
            case "Large":
                textSize = LARGE_TEXT_SIZE;
                break;
            default:
                textSize = REGULAR_TEXT_SIZE;
        }
        clearTasks = clearCheckBox.isChecked();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEXTSIZE, textSize);
        intent.putExtra(EXTRA_CLEAR, clearTasks);
        setResult(RESULT_OK, intent);
        finish();
    }
}
