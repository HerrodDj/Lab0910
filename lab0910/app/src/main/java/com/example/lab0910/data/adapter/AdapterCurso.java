package com.example.lab0910.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.lab0910.R;
import com.example.lab0910.curso.ListCurso;
import com.example.lab0910.model.Curso;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AdapterCurso extends RecyclerView.Adapter<AdapterCurso.MyViewHolder> implements Filterable {

    private ArrayList<Curso> cursosList;
    private ArrayList<Curso> cursosListFiltered;
    private Curso deletedItem;
    private AdapterCursoListener listener;

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
                    listener.onContactSelected(cursosListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public AdapterCurso(ArrayList<Curso> cursosList, AdapterCursoListener listener) {
        this.cursosList = cursosList;
        this.cursosListFiltered = cursosList;
        this.listener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final Curso curso = cursosListFiltered.get(position);
        holder.title1.setText(curso.getId());
        holder.title2.setText(curso.getDescripcion());
        holder.description.setText(String.valueOf(curso.getCreditos()));
    }

    @Override
    public int getItemCount() {
        return cursosListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = cursosListFiltered.remove(position);
        Iterator<Curso> iter = cursosList.iterator();
        while (iter.hasNext()) {
            Curso aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (cursosListFiltered.size() == cursosList.size()) {
            cursosListFiltered.add(position, deletedItem);
        } else {
            cursosListFiltered.add(position, deletedItem);
            cursosList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Curso getSwipedItem(int index) {
        if (this.cursosList.size() == this.cursosListFiltered.size()) { //not filtered yet
            return cursosList.get(index);
        } else {
            return cursosListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (cursosList.size() == cursosListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(cursosList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(cursosList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(cursosListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(cursosListFiltered, i, i - 1);
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
                    cursosListFiltered = cursosList;
                } else {
                    ArrayList<Curso> filteredList = new ArrayList<>();
                    for (Curso row : cursosList) {
                        // filter use two parameters
                        if (row.getId().toLowerCase().contains(charString.toLowerCase()) || row.getDescripcion().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    cursosListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cursosListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cursosListFiltered = (ArrayList<Curso>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface AdapterCursoListener {
        void onContactSelected(Curso curso);
    }
}
