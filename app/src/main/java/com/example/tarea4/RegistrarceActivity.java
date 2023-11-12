package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class RegistrarceActivity extends AppCompatActivity{

    Button Registrarce;
    EditText Nombre,Apellidos,FechaNac,Correo,Contraseña;
    TextView Mostrar;
    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    int año = Integer.parseInt(new SimpleDateFormat("YYYY").format(fecha));
    int mes = Integer.parseInt(new SimpleDateFormat("MM").format(fecha));
    int dia = Integer.parseInt(new SimpleDateFormat("dd").format(fecha));
    //String salida = df.format(fecha);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarce);
        Nombre= findViewById(R.id.AddRegisNombre);
        Apellidos=findViewById(R.id.AddRegisApellidos);
        FechaNac = findViewById(R.id.AddRegisFecha);
        FechaNac.setText(dia+"/"+mes+"/"+(año-18));
        Correo=findViewById(R.id.AddRegisCorreo);
        Contraseña = findViewById(R.id.AddRegisContraseña);
        Mostrar =findViewById(R.id.TvMostCondi);
        Mostrar.setText("");
        /*Contraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if(arg0.length()<8) {
                    Toast.makeText(getApplicationContext(), "La cantidad mínima de caracteres para la contraseña es de 8.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {}
            @Override
            public void afterTextChanged(Editable arg13) { }
        });*/

        Registrarce = findViewById(R.id.BtnEnvRegistro);
        Registrarce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ObtencioAño = FechaNac.getText().toString();
                Integer añovalido=Integer.parseInt(ObtencioAño.substring(ObtencioAño.length()-4));

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
                    Toast.makeText(RegistrarceActivity.this, "La edad minima requeridad para el uso de esta app es de 8 años", Toast.LENGTH_SHORT).show();
                }else if (!(Contraseña.getText().toString()).matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$")) {
                    Mostrar.setText("La contraseña debe tener al menos 1 número y 1 letra mayúscula");
                }else {
                    Toast.makeText(RegistrarceActivity.this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent iniciarSession = new Intent(RegistrarceActivity.this, LoginActivity.class);
                    startActivity(iniciarSession);
                }
            }
        });
    }


    public void FechaNacimiento(View view) {
            DatePickerDialog di = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    FechaNac.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },año-18,(mes-1),dia);
            di.show();
    }

}