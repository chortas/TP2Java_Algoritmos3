package cartas.cartasMonstruo;

import estadosDeCartas.Modo;
import jugador.Punto;

public class Wattcoladragon extends CartaMonstruo {
    private Modo modo;
    private Punto puntosAtaque;
    private Punto puntosDefensa;
    private Nivel nivel;
    //efecto

    public Wattcoladragon() {
        super("Wattcoladragon", 2500, 1000, 6);
        modo = null;
    }
}