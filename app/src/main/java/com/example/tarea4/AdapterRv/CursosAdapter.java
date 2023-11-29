package com.example.tarea4.AdapterRv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.Fragments.Ver.CursoFragment;
import com.example.tarea4.ModeloRv.Cursos;
import com.example.tarea4.R;
import com.example.tarea4.VerCursoActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class CursosAdapter extends FirestoreRecyclerAdapter<Cursos, CursosAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Fragment context;
    public CursosAdapter(@NonNull FirestoreRecyclerOptions<Cursos> options, CursoFragment context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull CursosAdapter.ViewHolder holder, int position, @NonNull Cursos model) {
        DocumentSnapshot ds= getSnapshots().getSnapshot(holder.getAdapterPosition());
        String posicion= ds.getId();
        holder.curso.setText(model.getNombre_cur());
        holder.profe.setText(model.getNomDocente_cur());
        holder.vercurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getContext(), VerCursoActivity.class);
                intent.putExtra("posicion", posicion);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public CursosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView curso, profe;
        CardView vercurso;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curso=itemView.findViewById(R.id.TvNomCurso);
            profe=itemView.findViewById(R.id.TvNomDocente);
            vercurso=itemView.findViewById(R.id.CvCurso);
        }
    }
}
