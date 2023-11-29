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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class VerCursoActivity extends AppCompatActivity {

    String idcur;
    FirebaseFirestore base_datos;
    TextView curso,docente,descripcion;
    ImageView close,edit,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_curso);
        base_datos=FirebaseFirestore.getInstance();
        idcur=getIntent().getStringExtra("posicion");
        curso=findViewById(R.id.TvCurso);
        docente=findViewById(R.id.TvDocente);
        descripcion=findViewById(R.id.TvDescripcion);
        close=findViewById(R.id.IvClose);
        edit=findViewById(R.id.IvEdit);
        delete=findViewById(R.id.IvDelete);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base_datos.collection("Cursos").document(idcur).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        Toast.makeText(VerCursoActivity.this, "Se elimino exitosamente.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VerCursoActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar=new Intent(VerCursoActivity.this,AgregarCursoActivity.class);
                editar.putExtra("idCurso",idcur);
                startActivity(editar);
            }
        });
        base_datos.collection("Cursos").document(idcur).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String nombreCurso = documentSnapshot.getString("nombre_cur");
                    String nombreDocente = documentSnapshot.getString("nomDocente_cur");
                    String descripcionCurso = documentSnapshot.getString("descripcion_cur");

                    curso.setText(nombreCurso);
                    docente.setText(nombreDocente);
                    descripcion.setText(descripcionCurso);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}