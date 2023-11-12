package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class PrincipalActivity extends AppCompatActivity {

    DrawerLayout dl;
    ActionBarDrawerToggle abdt;
    Toolbar toolbar;
    NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        dl = findViewById(R.id.DlDrawer);
        nv =findViewById(R.id.NvMenu);
        abdt = new ActionBarDrawerToggle(this,dl,toolbar,R.string.open,R.string.close);
        dl.addDrawerListener(abdt);
        abdt.setDrawerIndicatorEnabled(true);
        abdt.syncState();
        FloatingActionButton agregarevento = findViewById(R.id.FabAgregarEvento);
        agregarevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEvento = new Intent(PrincipalActivity.this,AgregarEventoActivity.class);
                startActivity(addEvento);
            }
        });
        FloatingActionButton agregarrecordatorio = findViewById(R.id.FabAgregarRecordatorio);
        agregarrecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecordatorio = new Intent(PrincipalActivity.this,AgregarRecordatorioActivity.class);
                startActivity(addRecordatorio);
                //menubtn.collapse();
            }
        });
        FloatingActionButton agregarTipoEvento = findViewById(R.id.FabAgregarTipoEvento);
        agregarTipoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTipoEvento = new Intent(PrincipalActivity.this,AgregarTipoEventoActivity.class);
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
            case "Buscar": Toast.makeText(this, "Buscar", Toast.LENGTH_LONG).show();
                return true;
            case "Recordatorio": Intent recordatorio = new Intent(this,RecordatorioActivity.class);
                startActivity(recordatorio);
                return true;
            case "Cursos": Intent cursos = new Intent(this,CursoActivity.class);
                startActivity(cursos);
                return true;
            case "Tipo de Evento": Intent TipEven = new Intent(this,TipoEventoActivity.class);
                startActivity(TipEven);
                return true;
            case "Modo Dia": Intent dia = new Intent(this,ModoDiaActivity.class);
                startActivity(dia);
                return true;
            case "Modo Semana": Intent semana = new Intent(this,ModoSemanaActivity.class);
                startActivity(semana);
                return true;
            case "Modo Mes": Intent mes = new Intent(this,ModoMesActivity.class);
                startActivity(mes);
                return true;
            case "Horas Dedicdas": Intent horasDedicadas = new Intent(this,HorasDedicadasActivity.class);
                startActivity(horasDedicadas);
                return true;
            case "Horas Libres": Intent horasLibres = new Intent(this,HorasLibresActivity.class);
                startActivity(horasLibres);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}