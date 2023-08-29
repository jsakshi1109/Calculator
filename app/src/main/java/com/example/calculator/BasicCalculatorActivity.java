package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BasicCalculatorActivity extends AppCompatActivity {
    private TextView resultTextView;
    private String currentInput = "";
    private double operand1 = 0;
    private String operator = "";
    private ArrayList<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);
        resultTextView = findViewById(R.id.resultTextView);
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        currentInput += button.getText().toString();
        updateDisplay();
    }

    public void onOperatorClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            if (!currentInput.isEmpty()) {
                operand1 = Double.parseDouble(currentInput);
                operator = button.getText().toString();
                currentInput = "";
                updateDisplay();
            }
        }
    }

    public void onEqualClick(View view) {
        if (!operator.isEmpty() && !currentInput.isEmpty()) {
            double operand2 = Double.parseDouble(currentInput);
            double result = performOperation(operand1, operand2, operator);
            currentInput = String.valueOf(result);
            operator = "";
            updateDisplay();

            // Add the calculation to history before clearing the input
            addToHistory(operand1 + " " + operator + " " + operand2 + " = " + result);

            // Clear currentInput and set operand1 to the result for any further calculations
            currentInput = "";
            operand1 = result;
        }
    }

    public void openHistory(View view) {
        Intent intent = new Intent(this, CalculationHistoryActivity.class);
        intent.putStringArrayListExtra("historyList", historyList);
        startActivity(intent);
    }

    private void addToHistory(String calculation) {
        historyList.add(0, calculation);
    }

    public void onClearClick(View view) {
        currentInput = "";
        operand1 = 0;
        operator = "";
        updateDisplay();
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return Double.NaN;
                }
            default:
                return 0;
        }
    }

    private void updateDisplay() {
        resultTextView.setText(currentInput);
    }
}
