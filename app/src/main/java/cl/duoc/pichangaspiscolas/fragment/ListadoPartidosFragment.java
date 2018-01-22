package cl.duoc.pichangaspiscolas.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cl.duoc.pichangaspiscolas.LoginActivity;
import cl.duoc.pichangaspiscolas.R;
import cl.duoc.pichangaspiscolas.adapter.ListaPartidosAdapter;
import cl.duoc.pichangaspiscolas.controlador.AdministrarPartido;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoPartidosFragment extends Fragment {
    private ListView lvPartidos;
    private ListaPartidosAdapter adapter;

    public ListadoPartidosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_listado_partidos, container, false);

        lvPartidos = (ListView)v.findViewById(R.id.lvlPartidos);

        adapter = new ListaPartidosAdapter(v.getContext(), AdministrarPartido.getInstancia().listar());

        lvPartidos.setAdapter(adapter);

        lvPartidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idItem = (TextView) view.findViewById(R.id.tvIdItem);
                String idIt = idItem.getText().toString();

                Fragment fragment = new ConfirmarPartidoFragment();

                Bundle args = new Bundle();
                args.putString("id", idIt);
                fragment.setArguments(args);

                //Toast.makeText(view.getContext().getApplicationContext(), idIt, Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment,"listado");
                transaction.commit();

            }
        });

        return v;
    }





}
