/**
 * Mursal Fahimi
 * IT 373
 * Professor Jost
  */


package it372.mfahimi.proj2fahimi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText editPrincipal;
    private EditText editRate;
    private RadioGroup radioGroupYears;
    private TextView textResult;
    private Button buttonCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrincipal = findViewById(R.id.editPrincipal);
        editRate = findViewById(R.id.editRate);
        radioGroupYears = findViewById(R.id.radioGroupYears);
        textResult = findViewById(R.id.textResult);
        buttonCompute = findViewById(R.id.buttonCompute);

        buttonCompute.setOnClickListener(v -> {
            computeMonthlyPayment();
        });
    }

    private void computeMonthlyPayment() {
        String principalText = editPrincipal.getText().toString();
        String rateText = editRate.getText().toString();

        if (principalText.isEmpty() || rateText.isEmpty()) {
            textResult.setText("Please enter all values.");
            return;
        }

        double p = Double.parseDouble(principalText);
        double r = Double.parseDouble(rateText);
        int n = 30;

        int selectedId = radioGroupYears.getCheckedRadioButtonId();

        if (selectedId == R.id.radio10) {
            n = 10;
        } else if (selectedId == R.id.radio15) {
            n = 15;
        } else if (selectedId == R.id.radio30) {
            n = 30;
        }

        double monthlyRate = r / 1200.0;
        double m = (p * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -12 * n));

        textResult.setText(String.format("Monthly Payment: $%.2f", m));
    }
}