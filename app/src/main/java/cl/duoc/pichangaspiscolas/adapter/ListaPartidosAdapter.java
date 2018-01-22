package cl.duoc.pichangaspiscolas.adapter;

import android.content.Context;
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
import cl.duoc.pichangaspiscolas.model.RegistrarPartido;



public class ListaPartidosAdapter extends ArrayAdapter<RegistrarPartido> {

    private List<RegistrarPartido> list;
    private Context context;

    public ListaPartidosAdapter(Context context,List<RegistrarPartido> list) {
        super(context, R.layout.items_partidos, list);
        this.list=list;
        this.context=context;
    }

    static class ViewHolder{
        protected TextView tvCancha;
        protected TextView tvEstado;
        protected TextView tvId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        ViewHolder holder;
        if (v == null) {
            LayoutInflater infla = LayoutInflater.from(context);
            v = infla.inflate(R.layout.items_partidos,null);
            holder = new ViewHolder();

            holder.tvCancha=(TextView)v.findViewById(R.id.tvCanchaItem);
            holder.tvEstado=(TextView)v.findViewById(R.id.tvEstadoItem);
            holder.tvId=(TextView)v.findViewById(R.id.tvIdItem);

            v.setTag(holder);
        }
        else{

            holder = (ViewHolder) v.getTag();
        }

        holder.tvCancha.setText(list.get(position).getNombreCancha());
        holder.tvEstado.setText(list.get(position).getEstado());
        holder.tvId.setText(list.get(position).getId());

        return v;
    }
}
