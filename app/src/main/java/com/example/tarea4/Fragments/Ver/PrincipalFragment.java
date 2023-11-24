package com.example.tarea4.Fragments.Ver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.AdapterRv.EventosAdapter;
import com.example.tarea4.ModeloRv.Eventos;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class PrincipalFragment extends Fragment {
    RecyclerView RvEventos;
    EventosAdapter adaptador;
    FirebaseFirestore base_datos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ver =inflater.inflate(R.layout.fragment_principal,container,false);
        base_datos=FirebaseFirestore.getInstance();
        RvEventos=ver.findViewById(R.id.RVpricipal);
        Query query = base_datos.collection("Eventos").whereEqualTo("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirestoreRecyclerOptions<Eventos>firestoreRecyclerOptions=new FirestoreRecyclerOptions.Builder<Eventos>().setQuery(query, Eventos.class).build();
        GridLayoutManager estilo=new GridLayoutManager(getContext(),1);
        RvEventos.setLayoutManager(estilo);
        adaptador = new EventosAdapter(firestoreRecyclerOptions);
        adaptador.notifyDataSetChanged();
        RvEventos.setAdapter(adaptador);
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
