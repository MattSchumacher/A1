package com.example.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final Calculator calculator = new Calculator();
    private TextView numberView;
    private Button advanceButton;
    private boolean isAdvance = false;
    private LinearLayout advanceRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberView = (TextView) findViewById(R.id.mian_tv_numbers);
        advanceButton = (Button) findViewById(R.id.main_btn_advance);
        advanceRow = (LinearLayout) findViewById(R.id.main_ll_advance_option);
    }

    @Override
    protected void onStart() {
        super.onStart();

        calculator.clear();
    }

    public void onNumberButtonClick(View view) {
        Button currentButton = (Button) view;
        String numberText = currentButton.getText().toString();
        numberView.append(String.format(" %s", numberText));

        calculator.push(numberText);
    }

    public void onClearButtonClick(View _) {
        numberView.setText("");
        calculator.clear();
    }

    public void onEqualButtonClick(View _) {
        numberView.append(" = ");
        try {
            double value = calculator.calculate();
            numberView.append(String.format(" %s", value));
        } catch (Exception e) {
            numberView.append(e.getMessage());
        }
    }

    public void toggleAdvancedOptions(View _) {
        if (isAdvance){
            advanceButton.setText(R.string.main_advance);
            advanceRow.setVisibility(View.INVISIBLE);
        } else {
            advanceButton.setText(R.string.main_standard);
            advanceRow.setVisibility(View.VISIBLE);
        }

        // toggle flag
        isAdvance = !isAdvance;
    }
}