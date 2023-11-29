package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerEventoActivity extends AppCompatActivity {
    String ideve;
    FirebaseFirestore base_datos;
    TextView nomeve,fecha_hora,repeticiones,notificaciones,tipo_evento,curso;
    ImageView close,edit,delete,circulo,cuadrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_evento);
        ideve=getIntent().getStringExtra("posicionEve");
        base_datos=FirebaseFirestore.getInstance();
        nomeve=findViewById(R.id.TvNomEvent);
        fecha_hora=findViewById(R.id.TvFechaHora);
        repeticiones=findViewById(R.id.TvRepeticiones);
        notificaciones=findViewById(R.id.TvNotificaciones);
        tipo_evento=findViewById(R.id.TvTipEve);
        curso=findViewById(R.id.TvCurso);
        close=findViewById(R.id.IvClose);
        delete=findViewById(R.id.IvDelete);
        edit=findViewById(R.id.IvEdit);
        circulo=findViewById(R.id.imageViewcircle);
        cuadrado=findViewById(R.id.Ivcolortip);

        base_datos.collection("Eventos").document(ideve).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String title=documentSnapshot.getString("titulo_eve");
                String fi=documentSnapshot.getString("fechaIni_eve");
                String ff=documentSnapshot.getString("fechaFin_eve");
                String hi=documentSnapshot.getString("horaIni_eve");
                String hf=documentSnapshot.getString("horaFin_eve");
                String repe=documentSnapshot.getString("repeticion_eve");
                String notify=documentSnapshot.getString("notificacion_eve");
                String tipeve=documentSnapshot.getString("id_tip");
                String cur=documentSnapshot.getString("id_cur");
                nomeve.setText(title);
                if (fi.equals(ff)){
                    fecha_hora.setText(fi.substring(0,15)+" "+hi+" - "+hf);
                }else {
                    fecha_hora.setText(fi.substring(0,15)+" "+hi+" - "+ff.substring(0,15)+" "+hf);
                }
                repeticiones.setText(repe);
                notificaciones.setText(notify);
                base_datos.collection("TipoEvento").document(tipeve).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        tipo_evento.setText(documentSnapshot.getString("nombre_tip"));
                        circulo.setColorFilter(Color.parseColor(documentSnapshot.getString("color_tip")));
                        cuadrado.setColorFilter(Color.parseColor(documentSnapshot.getString("color_tip")));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                base_datos.collection("Cursos").document(cur).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        curso.setText(documentSnapshot.getString("nombre_cur"));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base_datos.collection("Eventos").document(ideve).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        Toast.makeText(VerEventoActivity.this, "Se elimino exitosamente.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VerEventoActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar=new Intent(VerEventoActivity.this,AgregarCursoActivity.class);
                editar.putExtra("idEvento",ideve);
                startActivity(editar);
            }
        });
    }
}