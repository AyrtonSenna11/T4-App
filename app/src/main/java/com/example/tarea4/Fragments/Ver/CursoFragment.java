package com.example.tarea4.Fragments.Ver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea4.AdapterRv.CursosAdapter;
import com.example.tarea4.ModeloRv.Cursos;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CursoFragment extends Fragment {
    RecyclerView RvCursos;
    CursosAdapter adaptador;
    FirebaseFirestore base_datos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ver =inflater.inflate(R.layout.fragment_curso,container,false);
        base_datos=FirebaseFirestore.getInstance();
        RvCursos=ver.findViewById(R.id.RVcurso);
        String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query=base_datos.collection("Cursos").whereEqualTo("id_usu", id);
        FirestoreRecyclerOptions<Cursos> froc=new FirestoreRecyclerOptions.Builder<Cursos>().setQuery(query, Cursos.class).build();
        GridLayoutManager estilo=new GridLayoutManager(getContext(),1);
        RvCursos.setLayoutManager(estilo);
        adaptador=new CursosAdapter(froc);
        adaptador.notifyDataSetChanged();
        RvCursos.setAdapter(adaptador);
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