package mx.edu.isc.tesoem.exap1bem;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    private RadioGroup radioGroupP1;
    private CheckBox opcion2a, opcion2b, opcion2c, opcion2d;
    private Spinner spinnerP3;
    private EditText respuestaP4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        radioGroupP1 = findViewById(R.id.radioGroupP1);
        opcion2a = findViewById(R.id.opcion2a);
        opcion2b = findViewById(R.id.opcion2b);
        opcion2c = findViewById(R.id.opcion2c);
        opcion2d = findViewById(R.id.opcion2d);
        spinnerP3 = findViewById(R.id.spinnerP3);
        respuestaP4 = findViewById(R.id.respuestaP4);
        Button calificarButton = findViewById(R.id.calificarButton);

        // Configurar spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lenguajes_android, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerP3.setAdapter(adapter);

        calificarButton.setOnClickListener(v -> calificarExamen());
    }

    private void calificarExamen() {
        int puntaje = 0;

        // Pregunta 1 (MVP - 25 puntos)
        if (radioGroupP1.getCheckedRadioButtonId() == R.id.opcion1a) {
            puntaje += 25;
        }

        // Pregunta 2 (JSP - 25 puntos)
        if (!opcion2a.isChecked() && !opcion2b.isChecked() &&
                !opcion2c.isChecked() && opcion2d.isChecked()) {
            puntaje += 25;
        }

        // Pregunta 3 (Kotlin - 25 puntos)
        if (spinnerP3.getSelectedItemPosition() == 1) { // Kotlin es la segunda opción
            puntaje += 25;
        }

        // Pregunta 4 (Respuesta abierta - 25 puntos si contiene "intent" o "intención")
        String respuesta = respuestaP4.getText().toString().toLowerCase();
        if (respuesta.contains("intent") || respuesta.contains("intención")) {
            puntaje += 25;
        }

        // Pasar a la pantalla de resultados
        Intent intent = new Intent(this, MainActivity4.class);
        intent.putExtra("puntaje", puntaje);
        startActivity(intent);
        finish();
    }
}