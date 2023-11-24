package com.example.tarea4.AdapterRv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.ModeloRv.Recordatorios;
import com.example.tarea4.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class RecordatoriosAdapter extends FirestoreRecyclerAdapter<Recordatorios, RecordatoriosAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecordatoriosAdapter(@NonNull FirestoreRecyclerOptions<Recordatorios> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecordatoriosAdapter.ViewHolder holder, int position, @NonNull Recordatorios model) {
        holder.titulo.setText(model.getNombre_rec());
        holder.hora.setText(model.getHora_rec());
        holder.repe.setText(model.getRepeticion_rec());
    }

    @NonNull
    @Override
    public RecordatoriosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recordatorio,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, hora, repe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.TvNomReco);
            hora=itemView.findViewById(R.id.TvHoraReco);
            repe=itemView.findViewById(R.id.TvRepeReco);
        }
    }
}
