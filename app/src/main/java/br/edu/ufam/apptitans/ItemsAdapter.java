package br.edu.ufam.apptitans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.ufam.apptitans.models.Item;
import br.edu.ufam.apptitans.usuariodao.ItemDAO;
import br.edu.ufam.apptitans.usuariodao.UsuarioDAO;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private Context context;
    private ArrayList<Item> items;
    ItemDAO itemDAO;

    public ItemsAdapter(Context context) {
        this.context = context;
        itemDAO = new ItemDAO(context);
        itemDAO.criarItem();
        update();
    }

    public void update() {
        items = itemDAO.getList();
    }

    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ItemsViewHolder vh = new ItemsViewHolder(v, context);
        return vh;
    }

    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        holder.mood.setText(items.get(position).getMood());
        holder.musica.setText(items.get(position).getMusica());
    }

    public int getItemCount() { return items.size(); }
}
