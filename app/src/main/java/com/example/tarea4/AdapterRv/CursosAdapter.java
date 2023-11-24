package com.example.tarea4.AdapterRv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.ModeloRv.Cursos;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CursosAdapter extends FirestoreRecyclerAdapter<Cursos, CursosAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CursosAdapter(@NonNull FirestoreRecyclerOptions<Cursos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CursosAdapter.ViewHolder holder, int position, @NonNull Cursos model) {
        holder.curso.setText(model.getNombre_cur());
        holder.profe.setText(model.getNomDocente_cur());
    }

    @NonNull
    @Override
    public CursosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView curso, profe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            curso=itemView.findViewById(R.id.TvNomCurso);
            profe=itemView.findViewById(R.id.TvNomDocente);
        }
    }
}
