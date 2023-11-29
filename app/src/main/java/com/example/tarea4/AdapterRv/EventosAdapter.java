package com.example.tarea4.AdapterRv;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.Fragments.Ver.PrincipalFragment;
import com.example.tarea4.ModeloRv.Eventos;
import com.example.tarea4.R;
import com.example.tarea4.VerCursoActivity;
import com.example.tarea4.VerEventoActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class EventosAdapter extends FirestoreRecyclerAdapter<Eventos, EventosAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Fragment context;
    public EventosAdapter(@NonNull FirestoreRecyclerOptions<Eventos> options, PrincipalFragment principalFragment) {
        super(options);
        this.context=principalFragment;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Eventos model) {
        DocumentSnapshot ds= getSnapshots().getSnapshot(holder.getAdapterPosition());
        String posicionEve= ds.getId();
        holder.titulo.setText(model.getTitulo_eve());
        holder.hora.setText(model.getHoraIni_eve()+" - "+model.getHoraFin_eve());
        FirebaseFirestore.getInstance().collection("TipoEvento").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String idTip = document.getId();
                    if(idTip.equals(model.getId_tip())){
                        holder.color_cardview.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(document.getString("color_tip"))));
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        holder.color_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getContext(), VerEventoActivity.class);
                intent.putExtra("posicionEve", posicionEve);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_principal,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, hora;
        CardView color_cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.TvNomEvent);
            hora=itemView.findViewById(R.id.TvHora);
            color_cardview=itemView.findViewById(R.id.carviewpricipal);
        }
    }
}

