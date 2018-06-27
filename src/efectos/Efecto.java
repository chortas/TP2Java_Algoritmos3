package efectos;

import cartas.Carta;
import excepciones.VictoriaException;
import jugador.Jugador;
import tablero.InterrumpirAtaqueException;
import tablero.LadoDelCampo;

public interface Efecto {
    void activarEfecto() throws VictoriaException;
    void activarEfectoDeVolteoAnteAtaque(Jugador jugadorPoseedor, Jugador jugadorEnemigo, LadoDelCampo ladoEnemigo) throws InterrumpirAtaqueException;

}
