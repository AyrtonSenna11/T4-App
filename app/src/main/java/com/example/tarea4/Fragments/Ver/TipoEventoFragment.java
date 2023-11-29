package com.example.tarea4.Fragments.Ver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea4.AdapterRv.RecordatoriosAdapter;
import com.example.tarea4.AdapterRv.TipoEventoAdapter;
import com.example.tarea4.ModeloRv.TipoEvento;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TipoEventoFragment extends Fragment {
    RecyclerView RvTipoEvento;
    TipoEventoAdapter adaptador;
    FirebaseFirestore base_datos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View ver =inflater.inflate(R.layout.fragment_tipo_evento,container,false);
        base_datos=FirebaseFirestore.getInstance();
        RvTipoEvento=ver.findViewById(R.id.RVtipoevento);
        Query query = base_datos.collection("TipoEvento").whereEqualTo("id_usu", FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirestoreRecyclerOptions<TipoEvento> frot=new FirestoreRecyclerOptions.Builder<TipoEvento>().setQuery(query, TipoEvento.class).build();
        GridLayoutManager estilo=new GridLayoutManager(getContext(),1);
        RvTipoEvento.setLayoutManager(estilo);
        adaptador=new TipoEventoAdapter(frot, this);
        adaptador.notifyDataSetChanged();
        RvTipoEvento.setAdapter(adaptador);
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