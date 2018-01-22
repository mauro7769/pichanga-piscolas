package cl.duoc.pichangaspiscolas.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import cl.duoc.pichangaspiscolas.R;
import cl.duoc.pichangaspiscolas.adapter.AdapterMensajes;
import cl.duoc.pichangaspiscolas.controlador.AdministrarPartido;
import cl.duoc.pichangaspiscolas.model.MensajeEnviar;
import cl.duoc.pichangaspiscolas.model.MensajeRecibir;
import cl.duoc.pichangaspiscolas.model.RegistrarPartido;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private TextView nombre;
    private RecyclerView rvMensajes;
    private EditText txtMensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;



    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat, container, false);

            nombre = (TextView) v.findViewById(R.id.nombreUsuario);
            rvMensajes = (RecyclerView) v.findViewById(R.id.rvMensajes);
            txtMensaje = (EditText) v.findViewById(R.id.txtMensaje);
            btnEnviar = (Button)v.findViewById(R.id.btnEnviar);

            database = FirebaseDatabase.getInstance();

        final  String idItem = getArguments() != null ? getArguments().getString("id") : "";

        final RegistrarPartido PARTIDO = AdministrarPartido.getInstancia().Buscar(idItem);

            String salaChat = "chat_partido_id_"+PARTIDO.getId();

            databaseReference = database.getReference(salaChat);//Sala de chat

            String email;
            SharedPreferences prefe = v.getContext().getSharedPreferences("datos", Context.MODE_PRIVATE);
            email=prefe.getString("usuario","");
            nombre.setText(email);

            adapter = new AdapterMensajes(v.getContext());
            LinearLayoutManager l = new LinearLayoutManager(v.getContext());
            rvMensajes.setLayoutManager(l);
            rvMensajes.setAdapter(adapter);

            btnEnviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.push().setValue(new MensajeEnviar(txtMensaje.getText().toString(),nombre.getText().toString(), ServerValue.TIMESTAMP));
                    txtMensaje.setText("");
                }
            });

            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    setScrollbar();
                }
            });

            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                    adapter.addMensaje(m);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        return v;
    }
    private void setScrollbar(){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }

}
