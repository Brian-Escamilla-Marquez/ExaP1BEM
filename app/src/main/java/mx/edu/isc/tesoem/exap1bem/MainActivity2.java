package mx.edu.isc.tesoem.exap1bem;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private TextView captchaTextView, errorTextView;
    private EditText captchaEditText, usuarioEditText, contrasenaEditText;
    private Button ingresarButton;
    private int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        captchaTextView = findViewById(R.id.captchaTextView);
        captchaEditText = findViewById(R.id.captchaEditText);
        errorTextView = findViewById(R.id.errorTextView);
        usuarioEditText = findViewById(R.id.usuarioEditText);
        contrasenaEditText = findViewById(R.id.contrasenaEditText);
        ingresarButton = findViewById(R.id.ingresarButton);

        generarCaptcha();

        ingresarButton.setOnClickListener(v -> validarAcceso());
    }

    private void generarCaptcha() {
        Random random = new Random();
        num1 = random.nextInt(10);
        num2 = random.nextInt(10);
        captchaTextView.setText(getString(R.string.captcha_question, num1, num2));
    }

    private void validarAcceso() {
        String respuestaStr = captchaEditText.getText().toString();
        String usuario = usuarioEditText.getText().toString();
        String contrasena = contrasenaEditText.getText().toString();

        // Validar campos vacíos
        if (usuario.isEmpty() || contrasena.isEmpty() || respuestaStr.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar usuario y contraseña (ejemplo: usuario="tsdmh", contraseña="7s11")
        if (!usuario.equalsIgnoreCase("tsdmh") || !contrasena.equals("7s11")) {
            errorTextView.setText("Usuario o contraseña incorrectos");
            errorTextView.setVisibility(View.VISIBLE);
            return; // Detener si las credenciales son incorrectas
        }

        // Validar CAPTCHA (solo si las credenciales son correctas)
        try {
            int respuesta = Integer.parseInt(respuestaStr);
            if (respuesta == (num1 + num2)) {
                startActivity(new Intent(this, MainActivity3.class));
                finish();
            } else {
                errorTextView.setText(R.string.error_captcha);
                errorTextView.setVisibility(View.VISIBLE);
                generarCaptcha(); // Generar nuevo CAPTCHA
                captchaEditText.setText(""); // Limpiar campo
            }
        } catch (NumberFormatException e) {
            errorTextView.setText(R.string.error_captcha);
            errorTextView.setVisibility(View.VISIBLE);
        }
    }
}