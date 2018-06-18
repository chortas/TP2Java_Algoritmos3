package cartas;
import efectos.AgujeroNegro;

public class CreadoraDeCartas {
	public static final Carta crearCarta(String nombre) {
		switch (nombre) {
			case "Huevo Monstruoso":
				return new HuevoMonstruoso();
			case "AlasDeLaLlamaPerversa":
				return new AlasDeLaLlamaPerversa();
			case "Cilindro Magico":
				return new CilindroMagico();
			case "Agujero Negro":
				return new AgujeroNegro();

		default:
			break;
		}
		return null;
	}
}