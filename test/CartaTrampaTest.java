import cartas.Carta;
import cartas.CreadoraDeCartas;
import cartas.cartasTrampa.CilindroMagico;
import cartas.invocacion.InvocacionDefault;
import excepciones.VictoriaException;
import jugador.Jugador;
import org.junit.Test;
import tablero.Tablero;

import static junit.framework.TestCase.assertTrue;

public class CartaTrampaTest {
    @Test
    public void ColocarCartaTrampaBocaAbajoEnCampoParaJugador1() throws VictoriaException {
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Tablero tablero = new Tablero(jugador1, jugador2);
        CreadoraDeCartas manager = new CreadoraDeCartas();
        CilindroMagico unaCarta = new CilindroMagico();
        InvocacionDefault unaInvocacion = new InvocacionDefault(unaCarta);
        unaCarta.colocarBocaAbajo();
        assertTrue(tablero.colocarZonaTrampaMagica(unaInvocacion, jugador1));
    }


}
