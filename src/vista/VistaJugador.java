package vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import modelo.Fase.FasePreparacion;
import modelo.cartas.Carta;
import modelo.cartas.cartasCampo.CartaCampo;
import modelo.cartas.cartasMonstruo.CartaMonstruo;
import modelo.cartas.invocacion.InvocacionCartaMonstruoGenerica;
import modelo.cartas.invocacion.InvocacionDefault;
import modelo.excepciones.InvocacionExcepcion;
import modelo.excepciones.NoHayTrampasExcepcion;
import modelo.excepciones.ZonaMonstruoLlenaException;
import modelo.excepciones.ZonaTrampaMagicaLlenaException;
import modelo.jugador.Jugador;
import modelo.tablero.Tablero;
import vista.botones.BotonCarta;
import vista.botones.BotonCartaMano;
import vista.vistaZonas.VistaCampo;
import vista.vistaZonas.VistaMano;
import vista.vistaZonas.VistaTrampaMagica;
import vista.vistaZonas.VistaZonaMonstruo;

import java.util.ArrayList;
import java.util.List;

public class VistaJugador extends VBox {
    private final Tablero tablero;
    private final VistaZonaMonstruo vistaZonaMonstruo;
    private final VistaMano vistaMano;
    private final VistaCampo vistaCampo;
    private Jugador jugador;
    private ContenedorBase contenedorBase;
    private List <Node> elementos;
    private VistaTrampaMagica vistaTrampaMagica;

    public VistaJugador(ContenedorBase contenedorBase, Jugador jugador,
                        Tablero tablero) {

        this.tablero = tablero;
        this.jugador = jugador;
        this.contenedorBase = contenedorBase;
        this.elementos = new ArrayList <>();
        this.vistaCampo = new VistaCampo(tablero.mostrarZonaCampo(jugador), contenedorBase);
        this.vistaZonaMonstruo = new VistaZonaMonstruo(tablero.mostrarZonaMonstruo(jugador), contenedorBase);
        this.vistaTrampaMagica = new VistaTrampaMagica(tablero.mostrarZonaTrampaMagica(jugador), contenedorBase);
        this.vistaMano = new VistaMano(jugador.mostrarMano(), contenedorBase, this);
    }


    public void activar(boolean abajo, FasePreparacion fasePreparacion, Controlador controlador) {
        int x = 0;
        int y = 8;
        int filaMonstruo = 1;
        int filaTrampa = 0;
        int filaCampo = 1;
        int columnaCampo = 8;
        if (abajo) {
            x = 3;
            y = 2;
            filaMonstruo = 2;
            filaTrampa = 3;
            filaCampo = 2;
            columnaCampo = 2;
            vistaMano.mostrar(fasePreparacion, controlador);
        }
        this.agregarTexto(x, y);
        vistaZonaMonstruo.activar(filaMonstruo);
        vistaTrampaMagica.activar(filaTrampa);
        vistaCampo.activar(filaCampo, columnaCampo);
    }

    private void agregarTexto(int x, int y) {

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(25));

        Label datosJugador = new Label(jugador.obtenerNombre() + "\n Puntos: \n" + String.valueOf(jugador.obtenerPuntos().obtenerNumero()));
        datosJugador.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        datosJugador.setTextAlignment(TextAlignment.CENTER);
        datosJugador.setTextFill(Color.WHITE);

        elementos.add(datosJugador);

        contenedorBase.ubicarObjeto(datosJugador, x, y);
    }

    public void resetNombre() {
        for (Node elemento : elementos) {
            if (elemento instanceof Label) {
                contenedorBase.getChildren().remove(elemento);
            }
        }
        vistaMano.esconder();
    }

    public void colocarCartaMonstruoEnAtaque(CartaMonstruo carta, BotonCartaMano boton, FasePreparacion fase) {
        try {
            List <CartaMonstruo> sacrificios = vistaZonaMonstruo.generarSacrificios(carta.obtenerSacrificios());
            if (sacrificios.size() > 0) {
                contenedorBase.escribirEnConsola("Para tirar esta carta fue necesario " +
                        "sacrificar " + sacrificios.size() + " monstruo/os, elegido/os por tener los menores puntos de ataque");
            }
            InvocacionCartaMonstruoGenerica invocacionCartaMonstruoGenerica = new InvocacionCartaMonstruoGenerica(carta, sacrificios, fase);
            int indice = tablero.colocarZonaMonstruo(invocacionCartaMonstruoGenerica, jugador);
            carta.colocarEnModoDeAtaque();
            List<CartaMonstruo> monstruosActuales = tablero.mostrarZonaMonstruo(jugador).obtenerMonstruos();
            vistaZonaMonstruo.actualizarMonstruos(monstruosActuales);

            int columna = indice + 3;
            vistaZonaMonstruo.colocarCartaModoAtaque(carta, columna);
            contenedorBase.getChildren().remove(boton);
        } catch (ZonaMonstruoLlenaException excepcion) {
            contenedorBase.escribirEnConsola(excepcion.obtenerMotivo());
        } catch (InvocacionExcepcion invocacionExcepcion) {
            contenedorBase.escribirEnConsola(invocacionExcepcion.obtenerMotivo());
        }
    }

    public void colocarCartaMonstruoEnDefensa(CartaMonstruo carta, BotonCartaMano boton, FasePreparacion fase) {
        try {
            List <CartaMonstruo> sacrificios = vistaZonaMonstruo.generarSacrificios(carta.obtenerSacrificios());
            if (sacrificios.size() > 0) {
                contenedorBase.escribirEnConsola("Para tirar esta carta fue necesario " +
                        "sacrificar los" + sacrificios.size() + " monstruos de menor puntos de ataque");
            }
            InvocacionCartaMonstruoGenerica invocacionCartaMonstruoGenerica = new InvocacionCartaMonstruoGenerica(carta, sacrificios, fase);
            int indice = tablero.colocarZonaMonstruo(invocacionCartaMonstruoGenerica, jugador);
            carta.colocarEnModoDeDefensa();
            List<CartaMonstruo> monstruosActuales = tablero.mostrarZonaMonstruo(jugador).obtenerMonstruos();
            vistaZonaMonstruo.actualizarMonstruos(monstruosActuales);

            int columna = indice + 3;
            vistaZonaMonstruo.colocarCartaModoDefensa(carta, columna);
            contenedorBase.getChildren().remove(boton);
        } catch (ZonaMonstruoLlenaException excepcion) {
            contenedorBase.escribirEnConsola(excepcion.obtenerMotivo());
        } catch (InvocacionExcepcion invocacionExcepcion) {
            contenedorBase.escribirEnConsola(invocacionExcepcion.obtenerMotivo());
        }
    }

    public VistaMano getVistaMano() {
        return vistaMano;
    }

    public void setOpcionAtacar(ContextMenuAtacante contextMenu) {
        vistaZonaMonstruo.setOpcionAtacar(contextMenu);
        vistaCampo.desactivarCartas();
        vistaTrampaMagica.desactivarCartas();
    }

    public BotonCarta obtenerBoton(CartaMonstruo carta) {
        return vistaZonaMonstruo.obtenerBoton(carta);
    }

    public List <CheckBoxCarta> generarOpcionesAtaque() {
        return vistaZonaMonstruo.generarOpcionesAtaque();
    }

    public void eliminarElemento(BotonCarta botonCarta) {
        elementos.remove(botonCarta);
        vistaZonaMonstruo.eliminarBoton(botonCarta);
        vistaTrampaMagica.eliminarBoton(botonCarta);
    }

    public void colocarCartaTrampaMagica(Carta carta, BotonCartaMano boton) {

        try {
            InvocacionDefault invocacionDefault = new InvocacionDefault(carta);
            int indice = 0;
            indice = tablero.colocarZonaTrampaMagica(invocacionDefault, jugador);
            int columna = indice + 3;
            contenedorBase.getChildren().remove(boton);
            vistaTrampaMagica.colocarCarta(carta, columna);
        } catch (ZonaTrampaMagicaLlenaException excepcion) {
            contenedorBase.escribirEnConsola(excepcion.obtenerMotivo());
        }
    }

    public void colocarCartaCampo(CartaCampo carta, BotonCartaMano boton) {
        InvocacionDefault invocacionDefault = new InvocacionDefault(carta);
        tablero.colocarZonaCampo(invocacionDefault, jugador);
        vistaCampo.colocarCarta(carta);
        contenedorBase.getChildren().remove(boton);
    }

    public void esconderMano() {
        vistaMano.esconder();
    }

    public BotonCarta voltearPrimeraTrampa(Controlador controlador) throws NoHayTrampasExcepcion {

        return vistaTrampaMagica.voltearPrimeraTrampa(controlador);
    }

    public void activarCartasMagicas(Controlador controlador) {
        vistaTrampaMagica.activarCartasMagicas(this, controlador);
    }


    public void actualizarMonstruos(List<CartaMonstruo> monstruos) {
        vistaZonaMonstruo.actualizarMonstruos(monstruos);
    }

    public void actualizarMano(Controlador controlador) {
        vistaMano.mostrar(new FasePreparacion(), controlador);
    }

    public void actualizarDatosCartas() {
        vistaZonaMonstruo.actualizarDatosCartas();
    }
}
