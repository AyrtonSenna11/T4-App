package com.example.tarea4.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea4.R;
import com.example.tarea4.PrincipalActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView CambContra,Registrarce;
    EditText correo, contraseña;
    FirebaseAuth autenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo = findViewById(R.id.SesionEmail);
        contraseña = findViewById(R.id.SesionPassword);
        CambContra = findViewById(R.id.TvCamContra);
        Registrarce = findViewById(R.id.TvRegistrarce);
        autenticacion = FirebaseAuth.getInstance();
        //String Id_usuario=getIntent().getExtras().getString("IdUsuraio");
        Button BtnIngresar = findViewById(R.id.BtnIngresarUsua);
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString().trim();
                String password = contraseña.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Ingrese correo",Toast.LENGTH_LONG).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"Ingrese contraseña",Toast.LENGTH_LONG).show();
                }else {
                    autenticacionuser(email,password);
                }
            }
        });
    }

    private void autenticacionuser(String email, String password) {
        autenticacion.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    Intent IniSesion = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(IniSesion);
                }else {
                    Toast.makeText(LoginActivity.this,"Correo o contraseña incorrecta.",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this,"No disponible",Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser datosusuario = autenticacion.getCurrentUser();
        if (datosusuario!=null){
            Intent f1 = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(f1);
            finish();
        }
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