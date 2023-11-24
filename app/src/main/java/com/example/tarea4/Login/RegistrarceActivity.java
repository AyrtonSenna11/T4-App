package com.example.tarea4.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.initialization.qual.UnknownInitialization;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistrarceActivity extends AppCompatActivity{

    Button Registrarce;
    EditText Nombre,Apellidos,FechaNac,Correo,Contraseña;
    TextView Mostrar;
    ImageView cerrar;
    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    int año = Integer.parseInt(new SimpleDateFormat("YYYY").format(fecha));
    int mes = Integer.parseInt(new SimpleDateFormat("MM").format(fecha));
    int dia = Integer.parseInt(new SimpleDateFormat("dd").format(fecha));
    FirebaseFirestore base_datos;
    FirebaseAuth autenticacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarce);
        cerrar=findViewById(R.id.IvClose);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Nombre= findViewById(R.id.AddRegisNombre);
        Apellidos=findViewById(R.id.AddRegisApellidos);
        FechaNac = findViewById(R.id.AddRegisFecha);
        FechaNac.setText(dia+"/"+mes+"/"+año);
        Correo=findViewById(R.id.AddRegisCorreo);
        Contraseña = findViewById(R.id.AddRegisContraseña);
        Mostrar =findViewById(R.id.TvMostCondi);
        Mostrar.setText("");
        base_datos=FirebaseFirestore.getInstance();
        autenticacion=FirebaseAuth.getInstance();

        Registrarce = findViewById(R.id.BtnEnvRegistro);
        Registrarce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ObtencioAño = FechaNac.getText().toString().trim();
                Integer añovalido=Integer.parseInt(ObtencioAño.substring(ObtencioAño.length()-4));
                String name = Nombre.getText().toString().trim();
                String lastname = Apellidos.getText().toString().trim();
                String email = Correo.getText().toString().trim();
                String password = Contraseña.getText().toString().trim();

                if (Nombre.length() == 0) {
                    Toast.makeText(RegistrarceActivity.this, "Ingrese su Nombre", Toast.LENGTH_SHORT).show();
                    Mostrar.setText("");
                }else if (Apellidos.length() == 0) {
                    Toast.makeText(RegistrarceActivity.this, "Ingrese sus Apellidos", Toast.LENGTH_SHORT).show();
                    Mostrar.setText("");
                }else if (Correo.length() == 0) {
                    Toast.makeText(RegistrarceActivity.this, "Ingrese su Correo", Toast.LENGTH_SHORT).show();
                    Mostrar.setText("");
                }else if (Contraseña.length() == 0) {
                    Toast.makeText(RegistrarceActivity.this, "Ingrese una Contraeña", Toast.LENGTH_SHORT).show();
                    Mostrar.setText("");
                }else if (añovalido>=año-8) {
                    Toast.makeText(RegistrarceActivity.this, "*La edad minima requeridad para el uso de esta app es de 8 años", Toast.LENGTH_SHORT).show();
                }else if (!(Contraseña.getText().toString()).matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$")) {
                    Mostrar.setText("La contraseña debe tener al menos 1 número, 1 letra mayúscula y debe ser de al menos 8 caracteres");
                }else {
                    /*Toast.makeText(RegistrarceActivity.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent iniciarSession = new Intent(RegistrarceActivity.this, LoginActivity.class);
                    startActivity(iniciarSession);
                    finish();*/
                    enviarusuario(name,lastname,ObtencioAño,email,password);
                }
            }
        });
    }

    private void enviarusuario(String name, String lastname, String obtencioAño, String email, String password) {
      autenticacion.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              String id =autenticacion.getCurrentUser().getUid();
              Map<String,Object>usuario =new HashMap<>();
              usuario.put("id_usu",id);
              usuario.put("nombre_usu",name);
              usuario.put("apellidos_usua",lastname);
              usuario.put("fechaNac_usu",obtencioAño);
              usuario.put("correo_usua",email);
              usuario.put("contraseña_usua",password);
              usuario.put("estado_usua","0");
              base_datos.collection("Usuarios").document(id).set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      finish();
                      Intent registrado=new Intent(RegistrarceActivity.this,LoginActivity.class);
                      startActivity(registrado);
                      Toast.makeText(RegistrarceActivity.this,"Se ha registrado correctamente   ",Toast.LENGTH_SHORT).show();
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(RegistrarceActivity.this,"No se puedo guardar la informacion a la base de datos.",Toast.LENGTH_SHORT).show();
                  }
              });
          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(RegistrarceActivity.this,"No se puede registrarce.",Toast.LENGTH_SHORT).show();
          }
      });
    }


    public void FechaNacimiento(View view) {
            DatePickerDialog di = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    FechaNac.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },año,(mes-1),dia);
            di.show();
    }
}