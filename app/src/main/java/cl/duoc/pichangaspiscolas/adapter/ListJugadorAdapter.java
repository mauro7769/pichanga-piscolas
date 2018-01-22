package cl.duoc.pichangaspiscolas.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cl.duoc.pichangaspiscolas.R;
import cl.duoc.pichangaspiscolas.model.Jugador;
import cl.duoc.pichangaspiscolas.model.RegistrarPartido;



public class ListJugadorAdapter extends ArrayAdapter<Jugador> {
    private List<Jugador> list;
    private Context context;


    public ListJugadorAdapter(Context context,List<Jugador> list) {
        super(context, R.layout.items_jugadores, list);
        this.list=list;
        this.context=context;
    }

    static class ViewHolder{
        protected TextView tvNombre;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        ListJugadorAdapter.ViewHolder holder;
        if (v == null) {
            LayoutInflater infla = LayoutInflater.from(context);
            v = infla.inflate(R.layout.items_jugadores,null);
            holder = new ListJugadorAdapter.ViewHolder();

            holder.tvNombre=(TextView)v.findViewById(R.id.tvNombreItem);


            v.setTag(holder);
        }
        else{

            holder = (ListJugadorAdapter.ViewHolder) v.getTag();
        }

        holder.tvNombre.setText(list.get(position).getNombre());

        return v;
    }
}
