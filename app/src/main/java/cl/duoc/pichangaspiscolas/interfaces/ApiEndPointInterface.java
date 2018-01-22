package cl.duoc.pichangaspiscolas.interfaces;

import cl.duoc.pichangaspiscolas.model.Jugador;
import cl.duoc.pichangaspiscolas.model.RegistrarPartido;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface ApiEndPointInterface {
    @GET("x0yop")
    Call<Jugador> getJugador();

    @GET()
    Call<RegistrarPartido[]> getDetailsPartido(@Path("id") String id);

}
