package mx.edu.isc.tesoem.exap1bem;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView porcentajeTextView = findViewById(R.id.porcentajeTextView);
        TextView mensajeTextView = findViewById(R.id.mensajeTextView);
        Button volverButton = findViewById(R.id.volverButton);

        int puntaje = getIntent().getIntExtra("puntaje", 0);
        porcentajeTextView.setText(puntaje + "%");

        if (puntaje >= 70) {
            mensajeTextView.setText(R.string.felicidades);
            mensajeTextView.setTextColor(Color.GREEN);
        } else {
            mensajeTextView.setText(R.string.reprobado);
            mensajeTextView.setTextColor(Color.RED);
        }

        volverButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}