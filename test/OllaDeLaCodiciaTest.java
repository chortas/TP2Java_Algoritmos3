import cartas.invocacion.InvocacionDefault;
import cartas.cartasMonstruo.OllaDeLaCodicia;
import jugador.Jugador;
import org.junit.Test;
import tablero.Tablero;

import static junit.framework.TestCase.assertTrue;


public class OllaDeLaCodiciaTest {


    @Test
    public void ollaDeLaCodiciaTest() {
        assertTrue(true);

        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Tablero tablero = new Tablero(jugador1, jugador2);
        OllaDeLaCodicia ollaDeLaCodicia = new OllaDeLaCodicia(tablero, jugador1);
        InvocacionDefault invocacionOllaDeLaCodicia = new InvocacionDefault(ollaDeLaCodicia);
        tablero.colocarZonaTrampaMagica(invocacionOllaDeLaCodicia, jugador1);

        assertTrue((jugador1.cantidadDeCartasEnMazo() == 33));


    }
}
