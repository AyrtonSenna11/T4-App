package com.example.tarea4.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea4.R;
import com.google.firebase.auth.FirebaseAuth;

public class CambiarContrasenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);
        FirebaseAuth autenticacion = FirebaseAuth.getInstance();
        EditText emailContra=findViewById(R.id.CambContraEmail);
        Button EnvContra = findViewById(R.id.BtnEnvContraseña);
        EnvContra.setOnClickListener(new View.OnClickListener() {
            String email = emailContra.getText().toString().trim();
            @Override
            public void onClick(View view) {
                if (email.isEmpty()){
                    Toast.makeText(CambiarContrasenaActivity.this,"Ingrese su Correo",Toast.LENGTH_SHORT).show();
                }else {
                    autenticacion.sendPasswordResetEmail(email);
                    finish();
                    Toast.makeText(CambiarContrasenaActivity.this, "Se envio exitosamente el correo a " + email, Toast.LENGTH_SHORT).show();
                    Intent principal = new Intent(CambiarContrasenaActivity.this, LoginActivity.class);
                    startActivity(principal);
                }
            }
        });
    }
}