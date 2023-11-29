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

public class VerTipoEventoActivity extends AppCompatActivity {
    String idtipeve;
    FirebaseFirestore base_datos;
    TextView nomtipeve;
    ImageView close,edit,delete,cuadrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tipo_evento);
        idtipeve=getIntent().getStringExtra("posicionTipEve");
        base_datos=FirebaseFirestore.getInstance();
        nomtipeve=findViewById(R.id.TvNomTipEvent);
        close=findViewById(R.id.IvClose);
        edit=findViewById(R.id.IvEdit);
        delete=findViewById(R.id.IvDelete);
        cuadrado=findViewById(R.id.Ivcolortip);

        base_datos.collection("TipoEvento").document(idtipeve).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombre=documentSnapshot.getString("nombre_tip");
                nomtipeve.setText(nombre);
                cuadrado.setColorFilter(Color.parseColor(documentSnapshot.getString("color_tip")));
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
                base_datos.collection("TipoEvento").document(idtipeve).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        Toast.makeText(VerTipoEventoActivity.this, "Se elimino exitosamente.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VerTipoEventoActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar=new Intent(VerTipoEventoActivity.this,AgregarCursoActivity.class);
                editar.putExtra("idEvento",idtipeve);
                startActivity(editar);
            }
        });
    }
}