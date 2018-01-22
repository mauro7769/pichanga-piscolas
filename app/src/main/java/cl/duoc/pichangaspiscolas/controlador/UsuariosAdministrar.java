package cl.duoc.pichangaspiscolas.controlador;

import java.util.ArrayList;

import cl.duoc.pichangaspiscolas.model.Usuarios;



public class UsuariosAdministrar {

    private static UsuariosAdministrar instance;
    ArrayList<Usuarios>datos;

    protected UsuariosAdministrar()
    {
        datos = new ArrayList<>();
        Usuarios a = new Usuarios("Rene","11111");
        Usuarios b = new Usuarios("Marcelo","22222");
        Usuarios c = new Usuarios("Mauri","33333");
        Usuarios d = new Usuarios("Harting","44444");
        Usuarios f = new Usuarios("ÑaÑi","55555");
        Usuarios g = new Usuarios("Pepito","66666");
        Usuarios h = new Usuarios("Rypherior","77777");
        Usuarios j = new Usuarios("Jorge","88888");
        Usuarios k = new Usuarios("toto","99999");


        datos.add(a);
        datos.add(b);
        datos.add(c);
        datos.add(d);
        datos.add(f);
        datos.add(g);
        datos.add(h);
        datos.add(j);
        datos.add(k);

    }

    public static UsuariosAdministrar getInstance(){
        if(instance == null) {
            instance = new UsuariosAdministrar();

        }
        return instance;
    }




    public ArrayList<Usuarios> listarUsuario()
    {
        return datos;
    }
}
