package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea4.Fragments.Ver.RecordatorioFragment;
import com.example.tarea4.Fragments.Ver.TipoEventoFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarTipoEventoActivity extends AppCompatActivity {
    private int PrimaryKey=1;
    FirebaseFirestore base_datos;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tipo_evento);
        ImageView close = findViewById(R.id.IvClose);
        base_datos=FirebaseFirestore.getInstance();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        EditText tipoEvent = findViewById(R.id.EtAgrTipEven);
        Button agregar = findViewById(R.id.BtnAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipoEvent.length()==0){
                    Toast.makeText(AgregarTipoEventoActivity.this,"Ingrese un Tipo de evento.",Toast.LENGTH_SHORT).show();
                }else {
                    int id=PrimaryKey++;
                    String titleTip=tipoEvent.getText().toString().trim();
                    Map<String, Object>tipeve= new HashMap<>();
                    tipeve.put("id_tip",id);
                    tipeve.put("nombre_tip",titleTip);
                    tipeve.put("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    base_datos.collection("TipoEvento").add(tipeve).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            finish();
                            fm=getSupportFragmentManager();
                            ft=fm.beginTransaction();
                            ft.add(R.id.ContenedorFragmentos,new TipoEventoFragment());
                            ft.commit();
                            Toast.makeText(AgregarTipoEventoActivity.this,"Se agrego nuevo tipo de evento.",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AgregarTipoEventoActivity.this,"No se puedo conectar con la base de datos.",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

}