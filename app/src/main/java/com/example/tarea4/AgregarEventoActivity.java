package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.metrics.Event;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarEventoActivity extends AppCompatActivity {

    long ahora = System.currentTimeMillis();
    Date fecha = new Date(ahora);
    int año = Integer.parseInt(new SimpleDateFormat("YYYY").format(fecha));
    int mes = Integer.parseInt(new SimpleDateFormat("MM").format(fecha));
    int dia = Integer.parseInt(new SimpleDateFormat("dd").format(fecha));
    int hora = Integer.parseInt(new SimpleDateFormat("HH").format(fecha));
    int minuto = Integer.parseInt(new SimpleDateFormat("mm").format(fecha));
    DateFormat dfhora = new SimpleDateFormat("HH:mm");
    TextView fechaInicio,fechaFin,horaInicio,horaFin;
    EditText titulo;
    Button guardareve;
    ImageView close;
    FirebaseFirestore base_datos;
    private static  int PrimaryKey=1;
    Spinner repeticion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        titulo=findViewById(R.id.EtNomEven);
        fechaInicio=findViewById(R.id.TvFechaInicio);
        fechaFin=findViewById(R.id.TvFechaFin);
        horaInicio=findViewById(R.id.TvHoraInicio);
        horaFin=findViewById(R.id.TvHoraFin);
        close=findViewById(R.id.IvClose);
        fechaInicio.setText(new SimpleDateFormat("EE").format(fecha)+", "+dia+" de "+new SimpleDateFormat("MMM").format(fecha)+" de "+año);
        fechaFin.setText(fechaInicio.getText().toString());
        horaInicio.setText(dfhora.format(fecha));
        horaFin.setText(hora+1+":"+String.format("%02d",minuto));
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM YYYY");
        Date date = null;
        try {
            date = dateFormat.parse(fechaInicio.getText().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar calendar = date.;*/

        repeticion=findViewById(R.id.repetir);
        List<String> tiprepe = Arrays.asList(
                "No repetir",
                "Diario",
                "Semanal",
                "Mensual",
                "Anual"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tiprepe);
        repeticion.setAdapter(adapter);

        repeticion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenga el item seleccionado
                String item = tiprepe.get(position);

                /*calendar.setRepeatRule(
                        switch (item) {
                            case "No repetir" -> Calendar.NO_REPEAT;
                            case "Diario" -> Calendar.DAILY;
                            case "Semanal" -> Calendar.WEEKLY;
                            case "Mensual" -> Calendar.MONTHLY;
                            case "Anual" -> Calendar.YEARLY;
                        }
                );*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        base_datos=FirebaseFirestore.getInstance();
        guardareve=findViewById(R.id.BtnGuardarEve);
        guardareve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ObtFecha = fechaInicio.getText().toString();
                /*int obtdia=Integer.parseInt(ObtFecha.substring(5,7));
                int obtmes=Integer.parseInt(ObtFecha.substring(11,14));
                int obtaño=Integer.parseInt(ObtFecha.substring(ObtFecha.length()-4));*/

                /*Date obtFechIni = null;
                try {
                    obtFechIni = new SimpleDateFormat("EEE, dd    MMM    yyyy").parse(fechaInicio.getText().toString());
                } catch (ParseException e) {
                }
                Date obtFechFin =null;
                try {
                    obtFechFin = new SimpleDateFormat("EEE, dd    MMM    yyyy").parse(fechaFin.getText().toString());
                } catch (ParseException e) {
                }*/
                LocalTime hora1 = LocalTime.parse(horaInicio.getText().toString());
                LocalTime hora2 = LocalTime.parse(horaFin.getText().toString());
                if (titulo.length()==0){
                    Toast.makeText(AgregarEventoActivity.this,"Ingresar un titulo.",Toast.LENGTH_SHORT).show();
                }/*else if (obtFechIni.compareTo(obtFechFin)>0){
                    Toast.makeText(AgregarEventoActivity.this,"Verfique la Fecha de Inicio.",Toast.LENGTH_SHORT).show();
                }*/else if (hora1.compareTo(hora2)>0 && !(fechaInicio.getText().toString().equals(fechaFin.getText().toString()))){
                    Toast.makeText(AgregarEventoActivity.this,"Verfique la Hora de Inicio.",Toast.LENGTH_SHORT).show();
                }else {
                    int id = PrimaryKey++;
                    String title=titulo.getText().toString().trim();
                    String fi=fechaInicio.getText().toString().trim();
                    String ff=fechaFin.getText().toString().trim();
                    String hi=horaInicio.getText().toString().trim();
                    String hf=horaFin.getText().toString().trim();
                    Map<String, Object>eventos=new HashMap<>();
                    eventos.put("id_eve",id);
                    eventos.put("titulo_eve",title);
                    eventos.put("fechaIni_eve",fi);
                    eventos.put("fechafin_eve",ff);
                    eventos.put("horaIni_eve",hi);
                    eventos.put("horaFin_eve",hf);
                    eventos.put("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    base_datos.collection("Eventos").add(eventos).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            finish();
                            Intent vereven=new Intent(AgregarEventoActivity.this, PrincipalActivity.class);
                            startActivity(vereven);
                            Toast.makeText(AgregarEventoActivity.this,"Se agrego nuevo evento.",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AgregarEventoActivity.this,"No se puedo conectar con la base de datos.",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void FechaInicio(View view) {
        DatePickerDialog dfi = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String diaSemana = new SimpleDateFormat("EE").format(new Date(year, month, dayOfMonth-1));
                String nombreMes = new SimpleDateFormat("MMM").format(new Date(year, month, dayOfMonth));
                fechaInicio.setText(diaSemana+", "+dayOfMonth+" de "+nombreMes+" de "+year);
                fechaFin.setText(diaSemana+", "+dayOfMonth+" de "+nombreMes+" de "+year);
            }
        },año,(mes-1),dia);
        dfi.show();
    }

    public void FechaFin(View view) {
        DatePickerDialog dff = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String diaSemana = new SimpleDateFormat("EE").format(new Date(year, month, dayOfMonth-1));
                String nombreMes = new SimpleDateFormat("MMM").format(new Date(year, month, dayOfMonth));
                fechaFin.setText(diaSemana+", "+dayOfMonth+" de "+nombreMes+" de "+year);
            }
        },año,(mes-1),dia);
        dff.show();
    }

    public void HoraInicio(View view) {
        TimePickerDialog dti = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                horaInicio.setText(String.format("%02d",hour)+":"+String.format("%02d",minute));
            }
        },hora,minuto,true);
        dti.show();
    }

    public void HoraFin(View view) {
        TimePickerDialog dtf = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                horaFin.setText(String.format("%02d",hour)+":"+String.format("%02d",minute));
            }
        },hora,minuto,true);
        dtf.show();
    }
}