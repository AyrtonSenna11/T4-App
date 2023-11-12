package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CambiarContrasenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);
        TextView emailContra=findViewById(R.id.CambContraEmail);
        Button EnvContra = findViewById(R.id.BtnEnvContraseña);
        EnvContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CambiarContrasenaActivity.this, "Se envio el correo exitosamente a "+emailContra.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent principal = new Intent(CambiarContrasenaActivity.this,LoginActivity.class);
                startActivity(principal);
            }
        });
    }
}