package ohtu.kps;

import ohtu.komponentit.Tuomari;
import ohtu.komponentit.TkoAly;
import ohtu.IO;
import ohtu.strategy.Pelimoodi;

public abstract class AbstractKPS {

	protected static final IO io = Pelimoodi.io;
	protected Tuomari tuomari;
	protected TkoAly tekoaly;
	protected boolean pvp;

	abstract public void pelaa();

	private void alusta() {
		tuomari = new Tuomari();
		if (tekoaly != null) {
			pvp = false;
		} else {
			pvp = true;
		}
	}

	private String pelaajaOrAi(String ekanSiirto) {
		String tokaSiirto;
		if (pvp) {
			tokaSiirto = siirto("pelaaja2", "");
		} else {
			tokaSiirto = siirto("tekoaly", ekanSiirto);
		}
		return tokaSiirto;
	}

	private void tuomarinKirjaus(String ekanSiirto, String tokanSiirto) {
		tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
		io.print("" + tuomari);
		io.print("");
	}

	protected void peliSykli() {
		alusta();
		String ekanSiirto = siirto("pelaaja", "");
		String tokanSiirto = pelaajaOrAi(ekanSiirto);
		while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
			tuomarinKirjaus(ekanSiirto, tokanSiirto);
			ekanSiirto = siirto("pelaaja", "");
			tokanSiirto = pelaajaOrAi(ekanSiirto);
		}
		lopeta();
	}

	protected void lopeta() {
		io.print("");
		io.print("Kiitos!");
		io.print("" + tuomari);
	}

	public String siirto(String hahmo, String ekanSiirto) {
		if (hahmo.equals("tekoaly")) {
			String tokaSiirto;
			tokaSiirto = tekoaly.annaSiirto();
			io.print("Tietokone valitsi: " + tokaSiirto);
			tekoaly.asetaSiirto(ekanSiirto);
			return tokaSiirto;
		} else if (hahmo.equals("pelaaja")) {
			io.print("Ensimmäisen pelaajan siirto: ");
		} else if (hahmo.equals("pelaaja2")) {
			io.print("Toisen pelaajan siirto: ");
		}
		return io.readLine("");
	}

	public boolean onkoOkSiirto(String siirto) {
		return "k".equals(siirto)
			|| "p".equals(siirto)
			|| "s".equals(siirto);
	}
}
