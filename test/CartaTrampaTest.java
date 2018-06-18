import cartas.Carta;
import cartas.CreadoraDeCartas;
import jugador.Jugador;
import org.junit.Test;
import tablero.Tablero;

import static junit.framework.TestCase.assertTrue;

public class CartaTrampaTest {
    @Test
    public void ColocarCartaTrampaBocaAbajoEnCampoParaJugador1() {
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Tablero tablero = new Tablero(jugador1, jugador2);
        CreadoraDeCartas manager = new CreadoraDeCartas();
        Carta unaCarta = manager.crearCarta("Cilindro Magico");
        unaCarta.colocarBocaAbajo();
        assertTrue(tablero.colocarZonaTrampaMagica(unaCarta, jugador1));
    }


}