package com.example.tarea4;

import static android.icu.text.Transliterator.getDisplayName;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tarea4.Fragments.Ver.CursoFragment;
import com.example.tarea4.Fragments.Ver.RecordatorioFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarRecordatorioActivity extends AppCompatActivity {


    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    int año = Integer.parseInt(new SimpleDateFormat("YYYY").format(fecha));
    int mes = Integer.parseInt(new SimpleDateFormat("MM").format(fecha));
    int dia = Integer.parseInt(new SimpleDateFormat("dd").format(fecha));
    int hora = Integer.parseInt(new SimpleDateFormat("HH").format(fecha));
    int minuto = Integer.parseInt(new SimpleDateFormat("mm").format(fecha));
    DateFormat dfhora = new SimpleDateFormat("HH:mm");

    TextView Tvfecha,Tvhora;
    ImageView close;
    Spinner repe;
    String itemRepeReco;
    FirebaseFirestore base_datos;

    FragmentManager fm;
    FragmentTransaction ft;
    private int PrimaryKey=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recordatorio);
        EditText NombreRecor=findViewById(R.id.EtNomReco);
        Tvfecha=findViewById(R.id.TvFechaInicioReco);
        Tvfecha.setText(new SimpleDateFormat("EE").format(fecha)+", "+dia+" de "+new SimpleDateFormat("MMM").format(fecha)+" de "+año);
        Tvhora=findViewById(R.id.TvHoraInicioReco);
        Tvhora.setText(dfhora.format(fecha));
        repe=findViewById(R.id.SpReco);
        List<String> tiprepeReco = Arrays.asList(
                "No repetir",
                "Diario",
                "Semanal",
                "Mensual",
                "Anual"
        );
        ArrayAdapter<String> adaptadorrepeticionReco = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tiprepeReco);
        repe.setAdapter(adaptadorrepeticionReco);

        repe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemRepeReco= parent.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
        Button guardar =findViewById(R.id.BtnGuardarReco);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NombreRecor.length()==0){
                    Toast.makeText(AgregarRecordatorioActivity.this,"Ingrese un nombre de recordatorio.",Toast.LENGTH_SHORT).show();
                } else {
                    int id = PrimaryKey++;
                    String titleRec=NombreRecor.getText().toString().trim();
                    String date=Tvfecha.getText().toString().trim();
                    String hour=Tvhora.getText().toString().trim();
                    Map<String, Object> recordatorio=new HashMap<>();
                    recordatorio.put("id_rec",id);
                    recordatorio.put("nombre_rec",titleRec);
                    recordatorio.put("fecha_rec",date);
                    recordatorio.put("hora_rec",hour);
                    recordatorio.put("repeticion_rec",itemRepeReco);
                    recordatorio.put("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    base_datos.collection("Recordatorios").add(recordatorio).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            finish();
                            fm=getSupportFragmentManager();
                            ft=fm.beginTransaction();
                            ft.add(R.id.ContenedorFragmentos,new RecordatorioFragment());
                            ft.commit();
                            Toast.makeText(AgregarRecordatorioActivity.this,"Se agrego nuevo recordatorio.",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AgregarRecordatorioActivity.this,"No se puedo conectar con la base de datos.",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        close = findViewById(R.id.IvClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void HoraRecordatorio(View view) {
        TimePickerDialog dt = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Tvhora.setText(String.format("%02d",hour)+" : "+String.format("%02d",minute));
            }
        },hora,minuto,true);
        dt.show();
    }

    public void FechaRecordatorio(View view) {
        DatePickerDialog di = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String diaSemana = new SimpleDateFormat("EE").format(new Date(year, month, dayOfMonth-1));
                String nombreMes = new SimpleDateFormat("MMM").format(new Date(year, month, dayOfMonth));
                Tvfecha.setText(diaSemana+", "+dayOfMonth+" de "+nombreMes+" de "+year);
            }
        },año,(mes-1),dia);
        di.show();
    }
}