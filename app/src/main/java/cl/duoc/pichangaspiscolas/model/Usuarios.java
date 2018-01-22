package cl.duoc.pichangaspiscolas.model;



public class Usuarios {

    private String usuario;
    private String pass;

    public Usuarios() {

    }

    public Usuarios(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    public Usuarios(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
