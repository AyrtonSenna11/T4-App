package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import yuku.ambilwarna.AmbilWarnaDialog;

public class AgregarTipoEventoActivity extends AppCompatActivity {
    private int PrimaryKey=1;
    FirebaseFirestore base_datos;
    ImageView Ivcolor;
    TextView nomcolor;
    String nom_color;
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

        nomcolor=findViewById(R.id.TvColorTipEven);
        Ivcolor=findViewById(R.id.IvColor);
        Ivcolor.setColorFilter(Color.parseColor("#3F51B5"));
        nomcolor.setText("#3F51B5");
        nom_color="#3F51B5";
        Ivcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmbilWarnaDialog dialogocolor= new AmbilWarnaDialog(AgregarTipoEventoActivity.this, Color.BLACK, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        Ivcolor.setColorFilter(color);
                        nom_color=String.format("#%06x", color);
                        nomcolor.setText(nom_color);
                    }
                });dialogocolor.show();
            }
        });


        nomcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmbilWarnaDialog dialogocolor= new AmbilWarnaDialog(AgregarTipoEventoActivity.this, Color.BLACK, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        Ivcolor.setColorFilter(color);
                        nom_color=String.format("#%06x", color);
                        nomcolor.setText(nom_color);
                    }
                });dialogocolor.show();
            }
        });

        Button agregar = findViewById(R.id.BtnAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipoEvent.length()==0){
                    Toast.makeText(AgregarTipoEventoActivity.this,"Ingrese un titulo de Tipo de evento.",Toast.LENGTH_SHORT).show();
                }else {
                    int id=PrimaryKey++;
                    String titleTip=tipoEvent.getText().toString().trim();
                    Map<String, Object>tipeve= new HashMap<>();
                    tipeve.put("id_tip",id);
                    tipeve.put("nombre_tip",titleTip);
                    tipeve.put("color_tip",nom_color);
                    tipeve.put("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    base_datos.collection("TipoEvento").add(tipeve).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            finish();
                            /*Intent principal=new Intent(AgregarTipoEventoActivity.this,PrincipalActivity.class);
                            startActivity(principal);*/
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