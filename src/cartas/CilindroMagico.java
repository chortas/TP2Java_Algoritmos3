package cartas;

import efectos.EfectoCilindroMagico;

public class CilindroMagico extends Trampa {
    public CilindroMagico() {
        super("Cilindro Magico", new EfectoCilindroMagico());
    }
}
