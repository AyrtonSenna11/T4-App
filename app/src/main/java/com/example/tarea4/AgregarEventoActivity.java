package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
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
    Spinner repeticion, notifiaciones, tipo_evento, curso;
    //NotificationManager notificacion;
    String itemRepe, itemNotify, itemtipeven, itemcurso, id_curso, id_tipeve;
    ImageView IvColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);
        titulo=findViewById(R.id.EtNomEven);
        fechaInicio=findViewById(R.id.TvFechaInicio);
        fechaFin=findViewById(R.id.TvFechaFin);
        horaInicio=findViewById(R.id.TvHoraInicio);
        horaFin=findViewById(R.id.TvHoraFin);
        IvColor=findViewById(R.id.imageViewcolor);
        close=findViewById(R.id.IvClose);
        base_datos=FirebaseFirestore.getInstance();
        fechaInicio.setText(new SimpleDateFormat("EE").format(fecha)+", "+dia+" de "+new SimpleDateFormat("MMM").format(fecha)+" de "+año);
        fechaFin.setText(fechaInicio.getText().toString());
        horaInicio.setText(dfhora.format(fecha));
        horaFin.setText(String.format("%02d",hora+1)+":"+String.format("%02d",minuto));
        //Spiner de Repeticiones
        repeticion=findViewById(R.id.repetir);
        List<String> tiprepe = Arrays.asList(
                "No repetir",
                "Diario",
                "Semanal",
                "Mensual",
                "Anual"
        );
        ArrayAdapter<String> adaptadorrepeticion = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tiprepe);
        repeticion.setAdapter(adaptadorrepeticion);

        repeticion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemRepe= parent.getSelectedItem().toString().trim();
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
        //Spinner de Notificaciones
        notifiaciones=findViewById(R.id.SpNotify);
        List<String> notifica = Arrays.asList(
                "Sin notificar",
                "5 minutos antes",
                "10 minutos antes",
                "15 minutos antes",
                "1 hora antes",
                "1 dia antes"
        );

        ArrayAdapter<String> adaptadorNotificaciones = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notifica);
        notifiaciones.setAdapter(adaptadorNotificaciones);
        notifiaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemNotify= adapterView.getSelectedItem().toString().trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Spinner de Tipo de Evento
        tipo_evento=findViewById(R.id.SpTipEve);
        base_datos.collection("TipoEvento").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<String> tipevent=new ArrayList<>();
                tipevent.add("Seleccione un Tipo de Evento");
                tipevent.add("Agregar un nuevo Tipo de Evento");
                for (QueryDocumentSnapshot document : task.getResult()){
                    String nombreitem= document.getString("nombre_tip");
                    String id_usuario= document.getString("id_usu");
                    if(id_usuario.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        tipevent.add(nombreitem);
                    }
                }
                ArrayAdapter<String> adaptadortipoeven = new ArrayAdapter<String>(AgregarEventoActivity.this, android.R.layout.simple_spinner_item, tipevent);
                tipo_evento.setAdapter(adaptadortipoeven);
                tipo_evento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        itemtipeven = adapterView.getSelectedItem().toString().trim();
                        if (itemtipeven.equals("Seleccione un Tipo de Evento")){
                            IvColor.setColorFilter(getResources().getColor(R.color.black));
                        } else if (itemtipeven.equals("Agregar un nuevo Tipo de Evento")){
                            Intent agregartip=new Intent(AgregarEventoActivity.this,AgregarTipoEventoActivity.class);
                            startActivity(agregartip);
                        }else {
                            Query idtipeve= base_datos.collection("TipoEvento").whereEqualTo("nombre_tip",itemtipeven);
                            idtipeve.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    /*if (error != null) {
                                        Log.w(TAG, "Error getting documents: ", error);
                                        return;
                                    }*/
                                    for (QueryDocumentSnapshot document : value) {
                                        id_tipeve = document.getId();
                                        IvColor.setColorFilter(Color.parseColor(document.getString("color_tip")));
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
        //Spinner de Curso
        curso=findViewById(R.id.SpCursos);
        base_datos.collection("Cursos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<String> cursos=new ArrayList<>();
                cursos.add("Seleccione un Curso");
                cursos.add("Agregar un nuevo Curso");
                for (QueryDocumentSnapshot documentocurso : task.getResult()){
                    String nombcurso=documentocurso.getString("nombre_cur");
                    String idcurso=documentocurso.getString("id_usu");
                    if (idcurso.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        cursos.add(nombcurso);
                    }
                }
                ArrayAdapter<String> adaptadorcursos = new ArrayAdapter<String>(AgregarEventoActivity.this, android.R.layout.simple_spinner_item, cursos);
                curso.setAdapter(adaptadorcursos);
                curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        itemcurso = adapterView.getSelectedItem().toString().trim();
                        if (itemcurso.equals("Agregar un nuevo Curso")){
                            Intent agregarcurso=new Intent(AgregarEventoActivity.this, AgregarCursoActivity.class);
                            startActivity(agregarcurso);
                        }else if (itemcurso.equals("Seleccione un Curso")){}else {
                            Query idcurso= base_datos.getInstance().collection("Cursos").whereEqualTo("nombre_cur",itemcurso);
                            idcurso.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    /*if (error != null) {
                                        Log.w(TAG, "Error getting documents: ", error);
                                        return;
                                    }*/
                                    for (QueryDocumentSnapshot document : value) {
                                        id_curso = document.getId();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

        guardareve=findViewById(R.id.BtnGuardarEve);
        guardareve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }*/ else if (itemtipeven.equals("Seleccione un Tipo de Evento")) {
                    Toast.makeText(AgregarEventoActivity.this,"Seleccione un Tipo de Evento.",Toast.LENGTH_SHORT).show();
                } else if (itemcurso.equals("Seleccione un Curso")) {
                    Toast.makeText(AgregarEventoActivity.this,"Selccione un Curso.",Toast.LENGTH_SHORT).show();
                }else if (hora1.compareTo(hora2)>0 && !(fechaInicio.getText().toString().equals(fechaFin.getText().toString()))){
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
                    eventos.put("fechaFin_eve",ff);
                    eventos.put("horaIni_eve",hi);
                    eventos.put("horaFin_eve",hf);
                    eventos.put("repeticion_eve",itemRepe);
                    eventos.put("notificacion_eve",itemNotify);
                    eventos.put("id_tip",id_tipeve);
                    eventos.put("id_cur",id_curso);
                    eventos.put("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());

                    base_datos.collection("Eventos").add(eventos).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            finish();
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