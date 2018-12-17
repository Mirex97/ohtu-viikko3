package ohtu.strategy;

import ohtu.IO;
import ohtu.kps.AbstractKPS;
import ohtu.kps.Pelitehdas;

public abstract class Pelimoodi {
	public static IO io;
	private static AbstractKPS peli;

	public static void suorita(IO ioPass) {
		io = ioPass;
		boolean kaynnista = true;
		while (kaynnista) {
			printHelp();
			String vastaus = io.readLine("");
			kaynnista = Pelimoodi.luo(vastaus);
		}

	}

	private static boolean luo(String vastaus) {
		if (vastaus.endsWith("a")) {
			peli = Pelitehdas.pelimuoto(Pelitehdas.Muoto.moninpeli);
			peli.pelaa();
			return true;
		} else if (vastaus.endsWith("b")) {
			peli = Pelitehdas.pelimuoto(Pelitehdas.Muoto.helppo);
			peli.pelaa();
			return true;
		} else if (vastaus.endsWith("c")) {
			peli = Pelitehdas.pelimuoto(Pelitehdas.Muoto.vaikea);
			peli.pelaa();
			return true;
		}
		return false;
	}

	public static void printHelp() {
		io.print("\nValitse pelataanko"
			+ "\n (a) ihmistä vastaan "
			+ "\n (b) tekoälyä vastaan"
			+ "\n (c) parannettua tekoälyä vastaan"
			+ "\nmuilla valinnoilla lopetataan");
	}

}
