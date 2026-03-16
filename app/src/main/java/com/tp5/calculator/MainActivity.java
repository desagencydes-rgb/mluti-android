package com.tp5.calculator;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editPositif;
    private EditText editSigne;
    private TextView textResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPositif = findViewById(R.id.editPositif);
        editSigne   = findViewById(R.id.editSigne);
        textResultat = findViewById(R.id.textResultat);

        Button btnPlus  = findViewById(R.id.btnPlus);
        Button btnMoins = findViewById(R.id.btnMoins);
        Button btnMul   = findViewById(R.id.btnMul);
        Button btnDiv   = findViewById(R.id.btnDiv);

        btnPlus.setOnClickListener(v  -> lancerCalcul("+"));
        btnMoins.setOnClickListener(v -> lancerCalcul("-"));
        btnMul.setOnClickListener(v   -> lancerCalcul("*"));
        btnDiv.setOnClickListener(v   -> lancerCalcul("/"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh result if returning from Activity2
        double result = getIntent().getDoubleExtra("RESULT", Double.NaN);
        if (!Double.isNaN(result)) {
            textResultat.setText("Le résultat est : " + result);
        }
    }

    private void lancerCalcul(String operation) {
        String val1Str = editPositif.getText().toString().trim();
        String val2Str = editSigne.getText().toString().trim();

        if (val1Str.isEmpty() || val2Str.isEmpty()) {
            new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage("Il faut d'abord saisir un chiffre!")
                .setPositiveButton("OK", null)
                .show();
            return;
        }

        try {
            double val1 = Double.parseDouble(val1Str);
            double val2 = Double.parseDouble(val2Str);

            Intent intent = new Intent(this, CalculActivity.class);
            intent.putExtra("VAL1", val1);
            intent.putExtra("VAL2", val2);
            intent.putExtra("OP", operation);
            startActivityForResult(intent, 1);

        } catch (NumberFormatException e) {
            new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage("Il faut d'abord saisir un chiffre!")
                .setPositiveButton("OK", null)
                .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            double result = data.getDoubleExtra("RESULT", 0);
            textResultat.setText("Le résultat est : " + result);
        }
    }
}
