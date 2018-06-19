import cartas.*;
import jugador.Jugador;
import jugador.Punto;
import org.junit.Test;
import tablero.Cementerio;
import tablero.Tablero;
import tablero.ZonaMonstruo;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class DefensaMonstruoTest {
    @Test
    public void cartaEnModoDefensaEsAtacadaYGana(){
        Jugador jugadorDefensor = new Jugador();
        Jugador jugadorAtacante = new Jugador();
        Tablero tablero = new Tablero(jugadorDefensor, jugadorAtacante);
        HuevoMonstruoso cartaDefensora = new HuevoMonstruoso();
        DefaultInvocacion cartaInvocadaDefensora = new DefaultInvocacion(cartaDefensora); // No requiere sacrificios
        cartaInvocadaDefensora.invocar();
        AlasDeLaLlamaPerversa cartaAtacante = new AlasDeLaLlamaPerversa();
        DefaultInvocacion cartaInvocadaAtacante = new DefaultInvocacion(cartaAtacante); // No requiere sacrificios
        cartaInvocadaAtacante.invocar();
        cartaDefensora.colocarEnModoDeDefensa();
        cartaAtacante.colocarEnModoDeAtaque();
        tablero.colocarZonaMonstruo(cartaInvocadaDefensora,jugadorDefensor);
        tablero.colocarZonaMonstruo(cartaInvocadaAtacante,jugadorAtacante);
        tablero.atacarDosMonstruos(cartaAtacante,jugadorAtacante,cartaDefensora,jugadorDefensor);
        Cementerio cementerioDefensor = tablero.mostrarCementerio(jugadorDefensor);
        Cementerio cementerioAtacante = tablero.mostrarCementerio(jugadorAtacante);
        assertFalse(cementerioDefensor.existe(cartaDefensora) && cementerioAtacante.existe(cartaAtacante));
        ZonaMonstruo zonaMonstruoDefensor = tablero.mostrarZonaMonstruo(jugadorDefensor);
        ZonaMonstruo zonaMonstruoAtacante = tablero.mostrarZonaMonstruo(jugadorAtacante);
        assertTrue(zonaMonstruoDefensor.existe(cartaDefensora) && zonaMonstruoAtacante.existe(cartaAtacante));
        assertTrue(jugadorDefensor.obtenerPuntos().equals(new Punto(8000)) &&
                jugadorAtacante.obtenerPuntos().equals(new Punto(8000)));
    }

    @Test
    public void cartaEnModoDefensaEsAtacadaYPierdeConPuntosDeSobra(){
        Jugador jugadorDefensor = new Jugador();
        Jugador jugadorAtacante = new Jugador();
        Tablero tablero = new Tablero(jugadorDefensor, jugadorAtacante);
        HuevoMonstruoso cartaDefensora = new HuevoMonstruoso();
        DefaultInvocacion cartaInvocadaDefensora = new DefaultInvocacion(cartaDefensora); // No requiere sacrificios
        cartaInvocadaDefensora.invocar();
        AlcanzadorDeGarra cartaAtacante = new AlcanzadorDeGarra();
        DefaultInvocacion cartaInvocadaAtacante = new DefaultInvocacion(cartaAtacante); // No requiere sacrificios
        cartaInvocadaAtacante.invocar();
        cartaDefensora.colocarEnModoDeDefensa();
        cartaAtacante.colocarEnModoDeAtaque();
        tablero.colocarZonaMonstruo(cartaInvocadaDefensora,jugadorDefensor);
        tablero.colocarZonaMonstruo(cartaInvocadaAtacante,jugadorAtacante);
        tablero.atacarDosMonstruos(cartaAtacante,jugadorAtacante,cartaDefensora,jugadorDefensor);
        Cementerio cementerio = tablero.mostrarCementerio(jugadorDefensor);
        assertTrue(cementerio.existe(cartaDefensora));
        ZonaMonstruo zonaMonstruo = tablero.mostrarZonaMonstruo(jugadorDefensor);
        assertFalse(zonaMonstruo.existe(cartaDefensora));
        assertTrue(jugadorDefensor.obtenerPuntos().equals(new Punto(8000)) &&
                jugadorAtacante.obtenerPuntos().equals(new Punto(8000)));
    }

    @Test
    public void cartaEnModoDefensaEsAtacadaYPierdeConPuntosIguales(){
        Jugador jugadorDefensor = new Jugador();
        Jugador jugadorAtacante = new Jugador();
        Tablero tablero = new Tablero(jugadorDefensor, jugadorAtacante);
        HuevoMonstruoso cartaAtacante = new HuevoMonstruoso();
        DefaultInvocacion cartaInvocadaAtacante = new DefaultInvocacion(cartaAtacante); // No requiere sacrificios
        cartaInvocadaAtacante.invocar();
        AlasDeLaLlamaPerversa cartaDefensora = new AlasDeLaLlamaPerversa();
        DefaultInvocacion cartaInvocadaDefensora = new DefaultInvocacion(cartaDefensora); // No requiere sacrificios
        cartaInvocadaDefensora.invocar();
        cartaDefensora.colocarEnModoDeDefensa();
        cartaAtacante.colocarEnModoDeAtaque();
        tablero.colocarZonaMonstruo(cartaInvocadaAtacante,jugadorAtacante);
        tablero.colocarZonaMonstruo(cartaInvocadaDefensora,jugadorDefensor);
        tablero.atacarDosMonstruos(cartaAtacante,jugadorAtacante,cartaDefensora,jugadorDefensor);
        Cementerio cementerioAtacante = tablero.mostrarCementerio(jugadorAtacante);
        assertFalse(cementerioAtacante.existe(cartaAtacante));
        ZonaMonstruo zonaMonstruoAtacante = tablero.mostrarZonaMonstruo(jugadorAtacante);
        assertTrue(zonaMonstruoAtacante.existe(cartaAtacante));
        Cementerio cementerioDefensor = tablero.mostrarCementerio(jugadorDefensor);
        assertTrue(cementerioDefensor.existe(cartaDefensora));
        ZonaMonstruo zonaMonstruoDefensor = tablero.mostrarZonaMonstruo(jugadorDefensor);
        assertFalse(zonaMonstruoDefensor.existe(cartaDefensora));
        assertTrue(jugadorDefensor.obtenerPuntos().equals(new Punto(8000)) &&
                jugadorAtacante.obtenerPuntos().equals(new Punto(8000)));
    }
}
