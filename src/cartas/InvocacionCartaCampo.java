package cartas;
import jugador.Punto;
import java.util.List;

public class InvocacionCartaCampo extends Invocacion {

    private CartaCampo carta;
    private List<CartaMonstruo> monstruosAtaque;
    private List<CartaMonstruo> monstruosDefensa;

    public InvocacionCartaCampo(CartaCampo unaCarta, List<CartaMonstruo> monstruosAtaque,
                                   List<CartaMonstruo> monstruosDefensa) {
        super(unaCarta);
        carta = unaCarta;
        this.monstruosAtaque = monstruosAtaque;
        this.monstruosDefensa = monstruosDefensa;
    }

    public CartaCampo invocar() {
        return carta;
    }

}