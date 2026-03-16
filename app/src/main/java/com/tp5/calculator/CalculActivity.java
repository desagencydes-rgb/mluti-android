package com.tp5.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        double val1 = getIntent().getDoubleExtra("VAL1", 0);
        double val2 = getIntent().getDoubleExtra("VAL2", 0);
        String op   = getIntent().getStringExtra("OP");

        double result = calculer(val1, val2, op);

        TextView textInfo = findViewById(R.id.textInfo);
        textInfo.setText(
            "La première valeur est: " + val1 + "\n" +
            "La seconde valeur est: " + val2 + "\n" +
            "L'opération est: " + op + "\n" +
            "==> Le résultat est: " + result
        );

        Button btnRetour = findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("RESULT", result);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private double calculer(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) return Double.NaN;
                return a / b;
            default: return 0;
        }
    }
}
