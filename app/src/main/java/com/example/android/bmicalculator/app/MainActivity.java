package com.example.android.bmicalculator.app;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    /**
     * declare variables for bmi calculator
     * */

    protected EditText input_height;
    protected EditText input_weight;
    protected TextView heightUnit;
    protected TextView messageResult;
    protected TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_height    = (EditText) findViewById(R.id.et_height);
        input_weight    = (EditText) findViewById(R.id.et_weight);
        heightUnit      = (TextView) findViewById(R.id.tv_height_unit);
        messageResult   = (TextView) findViewById(R.id.tv_results_text);
        resultView      = (TextView) findViewById(R.id.tv_results_bmi);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bmiInterpretation ();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        };

        input_height.addTextChangedListener(textWatcher);
        input_weight.addTextChangedListener(textWatcher);
    }

    /**
     * Calculate bmi(metric standard)
     * */
    protected double calculateBmi(double weightInKg, double heightInMeter) {
        double  result = weightInKg / (heightInMeter * heightInMeter);
        return result;
    }

    /**
     * Gives interpretation to results of bmi
     * */
    protected void bmiInterpretation() {
        try {

            double height = Double.valueOf(input_height.getText().toString());
            double weight = Double.valueOf(input_weight.getText().toString());
            double bmi = calculateBmi(weight, height);

            if (bmi < 15) {
                resultView.setText("< 15");
            } else if (bmi > 40) {
                resultView.setText("> 40");
            } else {
                resultView.setText(String.format("%.1f", bmi));
            }

            if (bmi < 15) {
                messageResult.setText("You are very underweight. You need to eat a lot of healthy food! " );
            }
            else if (bmi >= 15 && bmi < 16) {
                messageResult.setText("You are underweight." );
            }
            else if (bmi >= 16 && bmi < 18.5) {
                messageResult.setText("You are underweight");
            }
            else if (bmi >= 18.5 && bmi < 25) {
                messageResult.setText("Your BMI is normal.");
            }
            else if (bmi >= 25 && bmi < 30) {
                messageResult.setText("Opps.. You are overweight.");
            }
            else if (bmi >= 30 && bmi < 35) {
                messageResult.setText("Opps.. You're on the 1st stage of obesity.");
            }
            else if (bmi >= 35 && bmi < 40) {
                messageResult.setText("Opps.. You're on the 2nd stage of obesity.");
            }
            else {
                messageResult.setText("Opps.. You're on the 2nd stage of obesity. You need to undergo healthy diet.!");
            }
        } catch (NumberFormatException nfe) {
            resultView.setText("0.0");
            messageResult.setText(" Enter your height(meters) and weight(kgs) below.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}