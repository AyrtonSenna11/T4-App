package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerRecordatorioActivity extends AppCompatActivity {
    String idreco;
    FirebaseFirestore base_datos;
    TextView nomreco,fecha_hora, repeticion;
    ImageView close,edit,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_recordatorio);
        idreco=getIntent().getStringExtra("posicionReco");
        base_datos=FirebaseFirestore.getInstance();
        nomreco=findViewById(R.id.TvNomReco);
        fecha_hora=findViewById(R.id.TvFechaHora);
        repeticion=findViewById(R.id.TvRepeticiones);
        close=findViewById(R.id.IvClose);
        edit=findViewById(R.id.IvEdit);
        delete=findViewById(R.id.IvDelete);

        base_datos.collection("Recordatorios").document(idreco).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombre=documentSnapshot.getString("nombre_rec");
                String fecha=documentSnapshot.getString("fecha_rec");
                String hora=documentSnapshot.getString("hora_rec");
                String repe=documentSnapshot.getString("repeticion_rec");

                nomreco.setText(nombre);
                fecha_hora.setText(fecha+" - "+hora);
                repeticion.setText(repe);
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
                base_datos.collection("Recordatorios").document(idreco).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        Toast.makeText(VerRecordatorioActivity.this, "Se elimino exitosamente.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VerRecordatorioActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar=new Intent(VerRecordatorioActivity.this,AgregarCursoActivity.class);
                editar.putExtra("idReco",idreco);
                startActivity(editar);
            }
        });
    }
}