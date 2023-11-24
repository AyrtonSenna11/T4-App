package com.example.tarea4.AdapterRv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.ModeloRv.TipoEvento;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TipoEventoAdapter extends FirestoreRecyclerAdapter<TipoEvento, TipoEventoAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TipoEventoAdapter(@NonNull FirestoreRecyclerOptions<TipoEvento> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TipoEventoAdapter.ViewHolder holder, int position, @NonNull TipoEvento model) {
        holder.titulo.setText(model.getNombre_tip());
    }

    @NonNull
    @Override
    public TipoEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo_evento,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.TvNomTipEve);
        }
    }
}
