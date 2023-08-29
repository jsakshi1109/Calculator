package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculationHistoryActivity extends AppCompatActivity {
    private TextView historyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_history);

        historyTextView = findViewById(R.id.historyTextView);
        ArrayList<String> historyList = getIntent().getStringArrayListExtra("historyList");

        if (historyList != null && !historyList.isEmpty()) {
            StringBuilder historyText = new StringBuilder();
            for (String item : historyList) {
                historyText.append(item).append("\n");
            }
            historyTextView.setText(historyText.toString());
        } else {
            historyTextView.setText("No history available.");
        }
    }

    public void openCalculator(View view) {
        Intent intent = new Intent(this, BasicCalculatorActivity.class);
        startActivity(intent);
    }
}