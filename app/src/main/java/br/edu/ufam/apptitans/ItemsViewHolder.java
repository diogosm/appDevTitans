package br.edu.ufam.apptitans;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView mood, musica;
    public int id;

    public ItemsViewHolder(ConstraintLayout v, Context context) {
        super(v);
        this.context = context;
        mood = v.findViewById(R.id.mood);
        musica = v.findViewById(R.id.musica);
        v.setOnClickListener(this);
    }

    public void onClick(View v) {
        Toast.makeText(context, "Olá ", Toast.LENGTH_LONG)
                .show();
    }
}

