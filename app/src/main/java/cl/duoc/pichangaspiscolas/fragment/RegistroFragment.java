package cl.duoc.pichangaspiscolas.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import cl.duoc.pichangaspiscolas.R;
import cl.duoc.pichangaspiscolas.api.ApiCallBack;
import cl.duoc.pichangaspiscolas.api.ApiConnection;
import cl.duoc.pichangaspiscolas.controlador.AdministrarPartido;
import cl.duoc.pichangaspiscolas.interfaces.ApiEndPointInterface;
import cl.duoc.pichangaspiscolas.model.Jugador;
import cl.duoc.pichangaspiscolas.model.RegistrarPartido;
import cl.duoc.pichangaspiscolas.model.Usuarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment {


    public RegistroFragment() {
        // Required empty public constructor
    }
    private EditText nombreCancha,ubicacion,fecha,hora;
    private Button btnAgregar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registro, container, false);

        nombreCancha = (EditText)v.findViewById(R.id.edtNombreCancha);
        ubicacion = (EditText)v.findViewById(R.id.edtUbicacion);
        fecha = (EditText)v.findViewById(R.id.edtFecha);
        hora = (EditText)v.findViewById(R.id.edtHora);

        btnAgregar = (Button) v.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar(nombreCancha.getText().toString(), ubicacion.getText().toString(), fecha.getText().toString(), hora.getText().toString())) {
                    agregar(nombreCancha.getText().toString(), ubicacion.getText().toString(), fecha.getText().toString(), hora.getText().toString());
                }
            }
        });


        return v;

    }

    private void agregar(String nombre, String ubicacion, String fecha, String hora) {
        /*
        para conectar con un servicio queda comentado hasta hacer los servicios
        * ApiEndPointInterface ser = ApiConnection.getApi(v.getContext());
                Call<Jugador> api = ser.getJugador();
                api.enqueue(new ApiCallBack<Jugador>() {
                    @Override
                    public void onInternetConnectionError() {

                    }

                    @Override
                    public void onFailured(Throwable t) {

                    }

                    @Override
                    public void onRequestSuccess(Response<Jugador> response, int statusCode) {
                        Jugador list = response.body();

                    }

                    @Override
                    public void onRequestError(Response<Jugador> response, int statusCode) {

                    }
                });
                mientras usamos array
        * */
        String usuario="",password="";

        SharedPreferences prefe = this.getContext().getSharedPreferences("datos",Context.MODE_PRIVATE);
        usuario=prefe.getString("usuario","");
        password=prefe.getString("pass","");

        Usuarios usu = new Usuarios(usuario,password);
        Date fec = new Date(fecha);
        String estado="Incompleto";
        int ultimoId = AdministrarPartido.getInstancia().listar().size();
        int id = ultimoId+1;
        String idPartido= String.valueOf(id);
        RegistrarPartido r = new RegistrarPartido(idPartido,usu,nombre,fec,ubicacion,hora,estado);

        boolean resp = AdministrarPartido.getInstancia().IngresarPartido(r);
        if (resp){
            Toast.makeText(this.getContext(), "se agrega partido", Toast.LENGTH_SHORT).show();
            Toast.makeText(this.getContext(), "usuario "+usu.getUsuario(), Toast.LENGTH_SHORT).show();
            limpiar();
        }
        else{
            Toast.makeText(this.getContext(), "no se agrega partido", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar() {
        nombreCancha.setText("");
        ubicacion.setText("");
        fecha.setText("");
        hora.setText("");
    }

    private boolean validar(String nombre, String dir, String fec, String hor) {
        if (nombre.trim().isEmpty()){
            nombreCancha.setError("debes completar este campo");
            return false;
        }else if (dir.trim().isEmpty()){
            ubicacion.setError("debes completar este campo");
            return false;
        }else if (fec.trim().isEmpty()){
            fecha.setError("debes completar este campo");
            return false;
        }else if (hor.trim().isEmpty()){
            hora.setError("debes completar este campo");
            return false;
        }
        return true;
    }


}
