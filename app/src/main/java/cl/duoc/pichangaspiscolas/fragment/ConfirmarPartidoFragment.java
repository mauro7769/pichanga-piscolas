package cl.duoc.pichangaspiscolas.fragment;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cl.duoc.pichangaspiscolas.R;
import cl.duoc.pichangaspiscolas.adapter.ListJugadorAdapter;
import cl.duoc.pichangaspiscolas.controlador.AdministrarPartido;
import cl.duoc.pichangaspiscolas.model.Jugador;
import cl.duoc.pichangaspiscolas.model.RegistrarPartido;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmarPartidoFragment extends Fragment {

    private TextView fecha,cancha,direcion,contador;
    private Button confirmar,chat;
    private ListView lvJugadores;
    private ListJugadorAdapter adapter;
    private int cant;

    public ConfirmarPartidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_confirmar_partido, container, false);

        fecha = (TextView) v.findViewById(R.id.tvFechaConfirmar);
        cancha = (TextView) v.findViewById(R.id.tvCanchaConfirmar);
        direcion = (TextView) v.findViewById(R.id.tvDireccionConfirmar);
        contador = (TextView) v.findViewById(R.id.tvContador);
        confirmar = (Button) v.findViewById(R.id.btnConfirmar);
        chat = (Button) v.findViewById(R.id.btnChat);
        lvJugadores = (ListView) v.findViewById(R.id.lvJugadores);

        final  String idItem = getArguments() != null ? getArguments().getString("id") : "";

        final RegistrarPartido PARTIDO = AdministrarPartido.getInstancia().Buscar(idItem);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChatFragment();

                Bundle args = new Bundle();
                args.putString("id", idItem);
                fragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment,"listado");
                transaction.commit();
            }
        });

        if (PARTIDO!=null){
            fecha.setText(PARTIDO.getFechaPartido().toString());
            cancha.setText(PARTIDO.getNombreCancha());
            direcion.setText(PARTIDO.getDireccion());
            cant = PARTIDO.getJugadores().size();
            contador.setText(String.valueOf(cant)+"/14");
            if (cant==14){
                PARTIDO.setEstado("Completo");
            }
            else {

                confirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nombre;
                        SharedPreferences usuario = v.getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
                        nombre=usuario.getString("usuario","");

                        boolean flag=false;
                        for (Jugador item :PARTIDO.getJugadores()) {
                            if (item.getNombre().compareTo(nombre)==0) {
                                Toast.makeText(getView().getContext(), "estas confirmado", Toast.LENGTH_SHORT).show();
                                flag=true;
                                break;
                            }
                        }
                        if (!flag){
                            Jugador j = new Jugador(nombre);
                            boolean resp = PARTIDO.getJugadores().add(j);
                            if (resp) {
                                Toast.makeText(v.getContext().getApplicationContext(), "Jugador confirmado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(v.getContext().getApplicationContext(), "Lo sentimos el equipo ya esta completo", Toast.LENGTH_SHORT).show();
                                PARTIDO.setEstado("Completo");
                            }
                        }

                        adapter.notifyDataSetChanged();
                        cant = PARTIDO.getJugadores().size();
                        contador.setText(String.valueOf(cant)+"/14");


                   }
                });

            }
        }

        adapter = new ListJugadorAdapter(v.getContext(),PARTIDO.getJugadores());
        lvJugadores.setAdapter(adapter);


        return v;
    }


}
