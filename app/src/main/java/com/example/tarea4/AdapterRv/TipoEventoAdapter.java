package com.example.tarea4.AdapterRv;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea4.Fragments.Ver.TipoEventoFragment;
import com.example.tarea4.ModeloRv.TipoEvento;
import com.example.tarea4.R;
import com.example.tarea4.VerTipoEventoActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class TipoEventoAdapter extends FirestoreRecyclerAdapter<TipoEvento, TipoEventoAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     * @param tipoEventoFragment
     */
    Fragment context;
    public TipoEventoAdapter(@NonNull FirestoreRecyclerOptions<TipoEvento> options, TipoEventoFragment tipoEventoFragment) {
        super(options);
        this.context=tipoEventoFragment;
    }

    @Override
    protected void onBindViewHolder(@NonNull TipoEventoAdapter.ViewHolder holder, int position, @NonNull TipoEvento model) {
        DocumentSnapshot ds= getSnapshots().getSnapshot(holder.getAdapterPosition());
        String posicionTipEve= ds.getId();
        holder.titulo.setText(model.getNombre_tip());
        if (model.getColor_tip() != null) {
            holder.color.setColorFilter(Color.parseColor(model.getColor_tip()));
        } else {
            holder.color.setColorFilter(Color.parseColor("#3F51B5")); // Color por defecto
        }
        holder.CvTipEve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getContext(), VerTipoEventoActivity.class);
                intent.putExtra("posicionTipEve",posicionTipEve);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public TipoEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo_evento,parent,false);
        return new ViewHolder(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageView color;
        CardView CvTipEve;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.TvNomTipEve);
            color=itemView.findViewById(R.id.IvColor);
            CvTipEve=itemView.findViewById(R.id.CvTipoEvento);
        }
    }
}
