
package ohtu.kps;

import static ohtu.kivipaperisakset.Paaohjelma.io;

public class Pelitehdas {
	
	public static enum Muoto {helppo, vaikea, moninpeli};
	
	public static AbstractKPS pelimuoto(Muoto pelimuoto) {
		io.print("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
		if (pelimuoto == pelimuoto.helppo) {
			return new KPSTekoaly();
		} else if (pelimuoto == pelimuoto.vaikea) {
			return new KPSParempiTekoaly();
		} else {
			return new KPSPelaajaVsPelaaja();
		}
	}
}
