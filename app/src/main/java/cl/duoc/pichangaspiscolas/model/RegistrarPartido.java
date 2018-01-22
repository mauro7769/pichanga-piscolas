package cl.duoc.pichangaspiscolas.model;

import java.util.ArrayList;
import java.util.Date;



public class RegistrarPartido {

    private String id;
    private Usuarios usuario;
    private String nombreCancha;
    private Date fechaPartido;
    private String direccion;
    private String hora;
    private String estado;
    private ArrayList<Jugador> jugadores;


    public RegistrarPartido() {
    }

    public RegistrarPartido(String id,Usuarios usuario, String nombreCancha, Date fechaPartido, String direccion, String hora, String estado) {
        this.id=id;
        this.usuario = usuario;
        this.nombreCancha = nombreCancha;
        this.fechaPartido = fechaPartido;
        this.direccion = direccion;
        this.hora = hora;
        this.estado = estado;
        this.jugadores=new ArrayList<>();
        agregar(usuario.getUsuario());
    }

    private void agregar(String usuario) {
        Jugador j = new Jugador(usuario);
        jugadores.add(j);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getNombreCancha() {
        return nombreCancha;
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    public Date getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
