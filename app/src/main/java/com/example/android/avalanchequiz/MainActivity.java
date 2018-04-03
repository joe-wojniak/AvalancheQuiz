package com.example.android.avalanchequiz;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    double quizScore;

    /**
     * This method is called when the score quiz button is clicked
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void scoreQuiz(View view) {
        /**
         * Checks the answer to the first question: Number of avalanches in Colorado: 276
         */
        EditText numberOfAvalanches = (EditText) findViewById(R.id.numberOfAvalanches);
        double avalanche = Integer.valueOf(numberOfAvalanches.getText().toString());
        if (avalanche <= 276) {
            quizScore = (avalanche / 276) * 10;
        }
        if (avalanche > 276) {
            quizScore = (276 / avalanche) * 10;
        }
        /**
         * Formats the Quiz Score to 2 decimal places
         */
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);
        quizScore = Double.parseDouble(nf.format(quizScore));

        Log.i("MainActivity", "Number of avalanches: " + avalanche);
        Log.i("MainActivity", "Score: " + quizScore);

        /**
         * Checks the Avalanche triangle question: Terrain, Snowpack, Weather are correct.
         */
        CheckBox avvyTriangle = (CheckBox) findViewById(R.id.terrainCheckBox);
        if (avvyTriangle.isChecked()) {
            quizScore = quizScore + 10;
        }
        avvyTriangle = (CheckBox) findViewById(R.id.treesAndRocksCheckBox);
        if (avvyTriangle.isChecked()) {
            quizScore = quizScore - 40;
        }
        avvyTriangle = (CheckBox) findViewById(R.id.snowpackCheckBox);
        if (avvyTriangle.isChecked()) {
            quizScore = quizScore + 10;
        }
        avvyTriangle = (CheckBox) findViewById(R.id.weatherCheckBox);
        if (avvyTriangle.isChecked()) {
            quizScore = quizScore + 10;
        }
        Log.i("MainActivity", "Avalanche triangle points: " + quizScore);

        RadioButton Avvy = (RadioButton) findViewById(R.id.AvvyYes);
        if (Avvy.isChecked()) {
            quizScore = quizScore + 10;
        }
        Avvy = (RadioButton) findViewById(R.id.AvvyNo);
        if (Avvy.isChecked()) {
            quizScore = quizScore - 20;
        }
        Log.i("MainActivity", "Avalanches occur naturally? Points: " + quizScore);

        RadioButton forecast = (RadioButton) findViewById(R.id.forecastYes);
        if (forecast.isChecked()) {
            quizScore = quizScore + 10;
        }
        forecast = (RadioButton) findViewById(R.id.forecastNo);
        if (forecast.isChecked()) {
            quizScore = quizScore - 20;
        }
        Log.i("MainActivity", "CAIC provides forecasts? Points: " + quizScore);
        /**
         * Check the Danger Scale answers. 5 need to be picked.
         */
        int dangerScaleCounter = 0;

        CheckBox dangerScale = (CheckBox) findViewById(R.id.minusculeCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore - 33;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.moderateCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore + 10;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.extremeCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore + 10;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.lowCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore + 10;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.gargantuanCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore - 33;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.mediumCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore - 33;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.considerableCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore + 10;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        dangerScale = (CheckBox) findViewById(R.id.highCheckBox);
        if (dangerScale.isChecked()) {
            quizScore = quizScore + 10;
            dangerScaleCounter = dangerScaleCounter + 1;
        }
        if (dangerScaleCounter < 5) {
            Toast.makeText(this, "Please choose at least 5 Danger Scale check boxes: " + dangerScaleCounter, Toast.LENGTH_LONG).show();
        }
        if (dangerScaleCounter > 5) {
            Toast.makeText(this, "Please choose no more than 5 Danger Scale check boxes: " + dangerScaleCounter, Toast.LENGTH_LONG).show();
        }
        Log.i("MainActivity", "Number of Danger scale check boxes: " + dangerScaleCounter);
        Log.i("MainActivity", "Danger scale points: " + quizScore);

        /**
         * Check Question: Accidents only happen Danger scale 3-Considerable or higher
         */
        ToggleButton considerable = (ToggleButton) findViewById(R.id.considerableToggleButton);
        if (considerable.isChecked()) {
            quizScore = quizScore - 30;
        } else {
            quizScore = quizScore + 10;
        }
        Log.i("MainActivity", "Accidents only happen 3-Considerable? Points: " + quizScore);
        /**
         * Show Quiz score
         */

        if (quizScore < 50) {
            Toast.makeText(this, ("The avalanche won this time! Your score: " + quizScore), Toast.LENGTH_LONG).show();
        }
        if (quizScore < 100) {
            if (quizScore > 50) {
                Toast.makeText(this, ("Close one. Study more! Your score: " + quizScore), Toast.LENGTH_LONG).show();
            }
        }
        if (quizScore > 100) {
            Toast.makeText(this, ("Good Job! Your score: " + quizScore), Toast.LENGTH_LONG).show();
        }
    }
}