package com.example.tarea4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        Toolbar myToolbar = findViewById(R.id.toolbarPrincipal);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch((String)item.getTitle()){
            case "Buscar": Toast.makeText(PrincipalActivity.this, "Buscar", Toast.LENGTH_LONG).show();
                return true;
            case "Recordatorio": Intent recordatorio = new Intent(PrincipalActivity.this,RecordatorioActivity.class);
                startActivity(recordatorio);
                return true;
            case "Cursos": Intent cursos = new Intent(PrincipalActivity.this,CursoActivity.class);
                startActivity(cursos);
                return true;
            case "Tipo de Evento": Intent TipEven = new Intent(PrincipalActivity.this,TipoEventoActivity.class);
                startActivity(TipEven);
                return true;
            case "Modo Dia": Intent dia = new Intent(PrincipalActivity.this,ModoDiaActivity.class);
                startActivity(dia);
                return true;
            case "Modo Semana": Intent semana = new Intent(PrincipalActivity.this,ModoSemanaActivity.class);
                startActivity(semana);
                return true;
            case "Modo Mes": Intent mes = new Intent(PrincipalActivity.this,ModoMesActivity.class);
                startActivity(mes);
                return true;
            case "Horas Dedicdas": Intent horasDedicadas = new Intent(PrincipalActivity.this,HorasDedicadasActivity.class);
                startActivity(horasDedicadas);
                return true;
            case "Horas Libres": Intent horasLibres = new Intent(PrincipalActivity.this,HorasLibresActivity.class);
                startActivity(horasLibres);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}