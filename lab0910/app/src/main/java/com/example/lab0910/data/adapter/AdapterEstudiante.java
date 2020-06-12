package com.example.lab0910.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lab0910.R;
import com.example.lab0910.model.Curso;
import com.example.lab0910.model.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AdapterEstudiante extends RecyclerView.Adapter<AdapterEstudiante.MyViewHolder> implements Filterable {

    private ArrayList<Usuario> estList;
    private ArrayList<Usuario> estListFiltered;
    private Usuario deletedItem;
    private AdapterEstudianteListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, title2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            title1 = view.findViewById(R.id.titleFirstLbl);
            title2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(estListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public AdapterEstudiante(ArrayList<Usuario> estList, AdapterEstudianteListener listener) {
        this.estList = estList;
        this.estListFiltered = estList;
        this.listener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_rowest, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final Usuario usuario = estListFiltered.get(position);
        holder.title1.setText(usuario.getId());
        holder.title2.setText(usuario.getNombre());
        holder.description.setText(usuario.getApellido());
    }

    @Override
    public int getItemCount() {
        return estListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = estListFiltered.remove(position);
        Iterator<Usuario> iter = estList.iterator();
        while (iter.hasNext()) {
            Usuario aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (estListFiltered.size() == estList.size()) {
            estListFiltered.add(position, deletedItem);
        } else {
            estListFiltered.add(position, deletedItem);
            estList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Usuario getSwipedItem(int index) {
        if (this.estList.size() == this.estListFiltered.size()) { //not filtered yet
            return estList.get(index);
        } else {
            return estListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (estList.size() == estListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(estList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(estList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(estListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(estListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    estListFiltered = estList;
                } else {
                    ArrayList<Usuario> filteredList = new ArrayList<>();
                    for (Usuario row : estList) {
                        // filter use two parameters
                        if (row.getId().toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    estListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = estListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                estListFiltered = (ArrayList<Usuario>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface AdapterEstudianteListener {
        void onContactSelected(Usuario usuario);
    }
}
