package com.example.login;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private List<ListElement> mData;
    private LayoutInflater mInflanter;
    private Context context;

    final ListAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListElement item);
    }

    public ListAdapter(List<ListElement> itemList,Context context, ListAdapter.OnItemClickListener listener)
    {
        this.mInflanter = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    public int getItemCount()
    {
        return mData.size();
    }

    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewTipe)
    {
        View view = mInflanter.inflate(R.layout.list_element,null);
        return new ListAdapter.ViewHolder(view);
    }

    public void onBindViewHolder (final ListAdapter.ViewHolder holder, final int posicion)
    {
        holder.binData(mData.get(posicion));
    }

    public void setItems(List<ListElement> items)
    {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombre, animal,estado, fecha, codigoPostal;

        ViewHolder(View itemView)
        {
            super(itemView);
            nombre = itemView.findViewById(R.id.name);
            animal = itemView.findViewById(R.id.animal);
            estado = itemView.findViewById(R.id.estado);
            fecha = itemView.findViewById(R.id.fecha);
            codigoPostal = itemView.findViewById(R.id.cp);
        }
        void binData(final ListElement item)
        {
            nombre.setText(item.getNombre());
            animal.setText(item.getAnimal());
            estado.setText(item.getEstado());
            fecha.setText(item.getFecha());
            codigoPostal.setText(item.getCodigoPostal());
            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            }));
        }
    }

}
