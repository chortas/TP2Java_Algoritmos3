package vista.vistaZonas;

import modelo.cartas.Carta;
import modelo.tablero.ZonaTrampaMagica;
import vista.ContenedorBase;
import vista.botones.BotonCartaBocaAbajo;
import vista.botones.BotonCartaZonaTrampaMagica;
import vista.handler.VoltearHandler;



public class VistaTrampaMagica extends VistaZonas {
    protected ZonaTrampaMagica zonaTrampaMagica;

    public VistaTrampaMagica(ZonaTrampaMagica zonaTrampaMagica, ContenedorBase contenedorBase) {
        super(contenedorBase);
        this.zonaTrampaMagica = zonaTrampaMagica;
    }

    public void colocarCarta(Carta carta, int columna) {

        BotonCartaZonaTrampaMagica botonCartaBocaArriba = new BotonCartaZonaTrampaMagica(carta, fila, columna);
        BotonCartaBocaAbajo botonCartaBocaAbajo = new BotonCartaBocaAbajo(fila, columna);
        VoltearHandler handle = new VoltearHandler(botonCartaBocaArriba, botonCartaBocaAbajo, this);
        botonCartaBocaAbajo.setOnAction(handle);
        botonCartaBocaAbajo.setRotate(90);
        elementos.add(botonCartaBocaAbajo);
        contenedorBase.ubicarObjeto(botonCartaBocaAbajo, fila, columna);
    }
}