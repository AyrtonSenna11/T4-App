package com.example.tarea4.Fragments.Ver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea4.AdapterRv.CursosAdapter;
import com.example.tarea4.AdapterRv.RecordatoriosAdapter;
import com.example.tarea4.ModeloRv.Recordatorios;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RecordatorioFragment extends Fragment {
    RecyclerView RvRecordatorios;
    RecordatoriosAdapter adaptador;
    FirebaseFirestore base_datos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ver =inflater.inflate(R.layout.fragment_recordatorio,container,false);
        base_datos=FirebaseFirestore.getInstance();
        RvRecordatorios=ver.findViewById(R.id.RVrecordatorio);
        Query query = base_datos.collection("Recordatorios").whereEqualTo("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirestoreRecyclerOptions<Recordatorios> fror=new FirestoreRecyclerOptions.Builder<Recordatorios>().setQuery(query, Recordatorios.class).build();
        GridLayoutManager estilo=new GridLayoutManager(getContext(),1);
        RvRecordatorios.setLayoutManager(estilo);
        adaptador=new RecordatoriosAdapter(fror);
        adaptador.notifyDataSetChanged();
        RvRecordatorios.setAdapter(adaptador);
        return ver;
    }

    @Override
    public void onStart() {
        super.onStart();
        adaptador.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }
}