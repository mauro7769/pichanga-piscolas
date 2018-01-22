package cl.duoc.pichangaspiscolas.controlador;

import java.util.ArrayList;
import java.util.Date;

import cl.duoc.pichangaspiscolas.model.RegistrarPartido;
import cl.duoc.pichangaspiscolas.model.Usuarios;



public class AdministrarPartido {

    private ArrayList<RegistrarPartido> partidos;
    private static AdministrarPartido instancia;

    public AdministrarPartido() {
        partidos = new ArrayList<>();
        Usuarios usu = new Usuarios("mauro");
        RegistrarPartido r = new RegistrarPartido("1",usu,"los palmos",new Date("15/11/2017"),"calle falsa","13:00","Incompleto");
        partidos.add(r);
    }

    public static AdministrarPartido getInstancia() {
        if (instancia==null){
            instancia=new AdministrarPartido();
        }
        return instancia;
    }

    public boolean IngresarPartido(RegistrarPartido nuevo){
        if (!BuscarPartido(nuevo)){
            partidos.add(nuevo);
            return true;
        }
        return false;
    }

    public boolean BuscarPartido(RegistrarPartido nuevo) {
        for (RegistrarPartido item:partidos){
            if (item.getFechaPartido().equals(nuevo.getFechaPartido())
                    && item.getNombreCancha().equals(nuevo.getNombreCancha()) && item.getHora().equals(nuevo.getHora())){
                return true;
            }
        }
        return false;
    }

    public RegistrarPartido Buscar(String id){
        for (RegistrarPartido item:partidos){
            if (item.getId().compareTo(id)==0){
                return item;
            }
        }
        return null;
    }

    public RegistrarPartido Buscar(RegistrarPartido nuevo){
        for (RegistrarPartido item:partidos){
            if (item.equals(nuevo)){
                return item;
            }
        }
        return null;
    }

    public ArrayList<RegistrarPartido> listar (){
        return partidos;
    }
}
