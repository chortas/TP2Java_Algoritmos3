package modelo.tablero;

import modelo.cartas.Carta;
import modelo.cartas.invocacion.Invocacion;
import modelo.jugador.Punto;

import java.util.ArrayList;
import java.util.List;

public abstract class ZonaMonstruo implements Zona {
    private static final int CANT_CASILLEROS = 5;
    private List<Casillero> casilleros;

    public ZonaMonstruo() {
        casilleros = new ArrayList<>();
        for (int i = 0; i < CANT_CASILLEROS; i++) casilleros.add(new Casillero());
    }

    public List<modelo.cartas.cartasMonstruo.CartaMonstruo> obtenerMonstruos() {
        List<modelo.cartas.cartasMonstruo.CartaMonstruo> resultado = new ArrayList<>();
        for (int i = 0; i < CANT_CASILLEROS; i++) {
            Casillero casillero = casilleros.get(i);
            if (! casillero.estaVacio()) resultado.add((modelo.cartas.cartasMonstruo.CartaMonstruo) casillero.mostrarCarta());
        }
        return resultado;
    }

    public int colocarCarta(Invocacion invocacion) {
        Carta carta = invocacion.invocar();
        for (int i = 0; i<CANT_CASILLEROS ; i++) {
            Casillero casillero = casilleros.get(i);
            if (casillero.estaVacio()) {
                casillero.colocarCarta(carta);
                carta.colocarBocaAbajo();
                return i;
            }
        }
        return -1;
    }

    public void eliminarCarta(Carta carta) {
        for (int i = 0; i < 5; i += 1) {
            if (casilleros.get(i).comparar(carta)) {
                casilleros.get(i).borrarCarta();
            }
        }
    }

    public boolean existe(modelo.cartas.cartasMonstruo.CartaMonstruo unaCarta) {
        for (int i = 0; i < 5; i += 1) {
            Casillero casillero = casilleros.get(i);
            if (casillero.comparar(unaCarta)) {
                return true;
            }
        }
        return false;
    }

    public void borrarMonstruos(Cementerio unCementerio){
        for (int i = 0; i<5; i+=1) {
            if(!casilleros.get(i).estaVacio()){
                Carta unaCarta = casilleros.get(i).mostrarCarta();
                unCementerio.colocarCarta(unaCarta);
                casilleros.get(i).borrarCarta();
            }
        }

    }

    public boolean estaVacia() {
        for (int i = 0; i<5; i+=1){
            if(!casilleros.get(i).estaVacio()){
                return false;
            }
        }
        return true;
    }

    public List<Casillero> obtenerCasilleros(){
        return casilleros;
    }

    public modelo.cartas.cartasMonstruo.CartaMonstruo obtenerMonstruoDebil(){
        List<modelo.cartas.cartasMonstruo.CartaMonstruo> monstruos = this.obtenerMonstruos();

        modelo.cartas.cartasMonstruo.CartaMonstruo cartaDebil = monstruos.get(0);
        for (int i = 1; i < monstruos.size(); i+=1){
            modelo.cartas.cartasMonstruo.CartaMonstruo carta = (modelo.cartas.cartasMonstruo.CartaMonstruo) casilleros.get(i).mostrarCarta();
            cartaDebil = carta.obtenerAtaqueMinimo(cartaDebil);
        }
        return cartaDebil;
    }

    public void aumentarDefensa(Punto puntosAdicionalesDefensa) {
        List<modelo.cartas.cartasMonstruo.CartaMonstruo> monstruos = obtenerMonstruos();
        for (modelo.cartas.cartasMonstruo.CartaMonstruo monstruo : monstruos) {
            monstruo.aumentarDefensa(puntosAdicionalesDefensa);
        }
    }

    public void aumentarAtaque(Punto puntosAdicionalesAtaque) {
        List<modelo.cartas.cartasMonstruo.CartaMonstruo> monstruos = obtenerMonstruos();
        for (modelo.cartas.cartasMonstruo.CartaMonstruo monstruo : monstruos) {
            monstruo.aumentarAtaque(puntosAdicionalesAtaque);
        }
    }
}