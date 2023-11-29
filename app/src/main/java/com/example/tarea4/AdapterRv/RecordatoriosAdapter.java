package com.example.tarea4.AdapterRv;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.Fragments.Ver.RecordatorioFragment;
import com.example.tarea4.ModeloRv.Recordatorios;
import com.example.tarea4.R;
import com.example.tarea4.VerRecordatorioActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class RecordatoriosAdapter extends FirestoreRecyclerAdapter<Recordatorios, RecordatoriosAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     * @param recordatorioFragment
     */
    Fragment context;
    public RecordatoriosAdapter(@NonNull FirestoreRecyclerOptions<Recordatorios> options, RecordatorioFragment recordatorioFragment) {
        super(options);
        this.context=recordatorioFragment;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecordatoriosAdapter.ViewHolder holder, int position, @NonNull Recordatorios model) {
        DocumentSnapshot ds= getSnapshots().getSnapshot(holder.getAdapterPosition());
        String posicionReco= ds.getId();
        holder.titulo.setText(model.getNombre_rec());
        holder.hora.setText(model.getHora_rec());
        holder.repe.setText(model.getRepeticion_rec());
        holder.CvReco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getContext(), VerRecordatorioActivity.class);
                intent.putExtra("posicionReco",posicionReco);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public RecordatoriosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recordatorio,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, hora, repe;
        CardView CvReco;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.TvNomReco);
            hora=itemView.findViewById(R.id.TvHoraReco);
            repe=itemView.findViewById(R.id.TvRepeReco);
            CvReco=itemView.findViewById(R.id.CvRecordatorio);
        }
    }
}
