package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.tarea4.Fragments.ModoVer.ModoMesFragment;
import com.example.tarea4.Fragments.ModoVer.ModoSemanaFragment;
import com.example.tarea4.Fragments.ResumenHoras.HorasDedicadasFragment;
import com.example.tarea4.Fragments.Ver.CursoFragment;
import com.example.tarea4.Fragments.Ver.PrincipalFragment;
import com.example.tarea4.Fragments.Ver.RecordatorioFragment;
import com.example.tarea4.Fragments.Ver.TipoEventoFragment;
import com.example.tarea4.Login.LoginActivity;
import com.example.tarea4.Fragments.ModoVer.ModoDiaFragment;
import com.example.tarea4.Fragments.ResumenHoras.HorasLibresFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {
    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    Toolbar toolbar;
    NavigationView nv;
    FirebaseAuth autenticacion;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = findViewById(R.id.toolbarPrincipal);
        //toolbar.setTitle("");
        setSupportActionBar(toolbar);
        dl = findViewById(R.id.DlDrawer);
        nv =findViewById(R.id.NvMenu);
        abdt = new ActionBarDrawerToggle(this,dl,toolbar,R.string.open,R.string.close);
        dl.addDrawerListener(abdt);
        abdt.setDrawerIndicatorEnabled(true);
        abdt.syncState();
        //Poder utilizar el Cloud Firestore(Instanciar)
        autenticacion = FirebaseAuth.getInstance();
        //Cargar Fragmentos
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.add(R.id.ContenedorFragmentos,new PrincipalFragment());
        ft.commit();
        //Ingresar a los items del NavigationView
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                dl.closeDrawer(GravityCompat.START);
                switch((String)item.getTitle()){
                    case "Dia": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new ModoDiaFragment());
                        ft.commit();
                        break;
                    case "Semana": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new ModoSemanaFragment());
                        ft.commit();
                        break;
                    case "Mes": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new ModoMesFragment());
                        ft.commit();
                        break;
                    case "Horas Dedicadas":fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new HorasDedicadasFragment());
                        ft.commit();
                        break;
                    case "Horas Libres": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new HorasLibresFragment());
                        ft.commit();
                        break;
                    case "Eventos": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new PrincipalFragment());
                        ft.commit();
                        break;
                    case "Recordatorios": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new RecordatorioFragment());
                        ft.commit();
                        break;
                    case "Cursos": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new CursoFragment());
                        ft.commit();
                        break;
                    case "Tipos de Eventos": fm=getSupportFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.ContenedorFragmentos,new TipoEventoFragment());
                        ft.commit();
                        break;
                    case "Cerrar Sesion": autenticacion.signOut();
                        finish();
                        Intent cerrarsesion = new Intent(PrincipalActivity.this, LoginActivity.class);
                        startActivity(cerrarsesion);
                        break;
                    default:
                }
                return false;
            }
        });


        FloatingActionButton agregarevento = findViewById(R.id.FabAgregarEvento);
        agregarevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEvento = new Intent(PrincipalActivity.this, AgregarEventoActivity.class);
                startActivity(addEvento);
            }
        });
        FloatingActionButton agregarrecordatorio = findViewById(R.id.FabAgregarRecordatorio);
        agregarrecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecordatorio = new Intent(PrincipalActivity.this, AgregarRecordatorioActivity.class);
                startActivity(addRecordatorio);
                //menubtn.collapse();
            }
        });
        FloatingActionButton agregarTipoEvento = findViewById(R.id.FabAgregarTipoEvento);
        agregarTipoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTipoEvento = new Intent(PrincipalActivity.this, AgregarTipoEventoActivity.class);
                startActivity(addTipoEvento);

            }
        });
        FloatingActionButton agregarCursos = findViewById(R.id.FabAgregarCursos);
        agregarCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCursos = new Intent(PrincipalActivity.this, AgregarCursoActivity.class);
                startActivity(addCursos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch((String)item.getTitle()){
            /*case "Perfil": Intent perfil = new Intent(this,PerfilActivity.class);
                startActivity(perfil);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}