package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea4.Fragments.Ver.CursoFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarCursoActivity extends AppCompatActivity {

    ImageView cerrar;
    EditText curso,docente,descripcion;
    Button guardar;
    FirebaseFirestore base_datos;
    private static int PrimaryKey = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);
        cerrar=findViewById(R.id.IvClose);
        curso=findViewById(R.id.EtCurso);
        docente=findViewById(R.id.EtDocente);
        descripcion=findViewById(R.id.EtDescripcion);
        base_datos=FirebaseFirestore.getInstance();
        guardar=findViewById(R.id.BtnAgreCurso);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curso.getText().toString().length()==0){
                    Toast.makeText(AgregarCursoActivity.this,"Ingrese el nombre del curso.",Toast.LENGTH_SHORT).show();
                }else if(docente.getText().toString().length()==0){
                    Toast.makeText(AgregarCursoActivity.this,"Ingrese el nombre del docente del curso.",Toast.LENGTH_SHORT).show();
                }else{
                    int id = PrimaryKey++;
                    String course=curso.getText().toString().trim();
                    String teacher=docente.getText().toString().trim();
                    String description=descripcion.getText().toString().trim();
                    Map<String, Object>cursos=new HashMap<>();
                    cursos.put("id_cur",id);
                    cursos.put("nombre_cur",course);
                    cursos.put("nomDocete_cur",teacher);
                    cursos.put("descripcion_cur",description);
                    cursos.put("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    base_datos.collection("Cursos").add(cursos).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            finish();
                            Intent vercursos = new Intent(AgregarCursoActivity.this, CursoFragment.class);
                            startActivity(vercursos);
                            Toast.makeText(AgregarCursoActivity.this,"Se agrego nuevo curso.",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AgregarCursoActivity.this,"No se puedo conectar con la base de datos.",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    public void cerrar(View view) {
        finish();
    }
}