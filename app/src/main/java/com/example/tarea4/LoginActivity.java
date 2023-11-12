package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView CambContra,Registrarce;
    EditText correo, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo = findViewById(R.id.SesionEmail);
        contraseña = findViewById(R.id.SesionPassword);
        CambContra = findViewById(R.id.TvCamContra);
        Registrarce = findViewById(R.id.TvRegistrarce);
        correo.setText("aea@aea.com");
        contraseña.setText("aea123");
        Button BtnIngresar = findViewById(R.id.BtnIngresarUsua);
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(correo.getText().toString().equals("aea@aea.com"))) {
                    Toast.makeText(LoginActivity.this,"Correo Incorrecta",Toast.LENGTH_LONG).show();
                }else if (!(contraseña.getText().toString().equals("aea123"))) {
                    Toast.makeText(LoginActivity.this,"Contraseña Incorrecta",Toast.LENGTH_LONG).show();
                }else/* if (!(correo.getText().toString().equals("aea@aea.com")) && !(contraseña.getText().toString().equals("aea123")))*/ {
                    Intent IniSesion = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(IniSesion);
                }
            }
        });
    }

    public void CambiarContraseña(View view) {
        Intent CambContra = new Intent(LoginActivity.this,CambiarContrasenaActivity.class);
        startActivity(CambContra);
    }
    public void Registrarce(View view) {
        Intent Registrarce = new Intent(LoginActivity.this, RegistrarceActivity.class);
        startActivity(Registrarce);
    }
}