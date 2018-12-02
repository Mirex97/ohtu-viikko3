package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

	public final static int KAPASITEETTI = 5, // aloitustalukon koko
		OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
	// näin paljon isompi kuin vanha
	private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
	private int[] lukuJono;      // Joukon luvut säilytetään taulukon alkupäässä. 
	private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

	public IntJoukko() {
		this(KAPASITEETTI, OLETUSKASVATUS);
	}

	public IntJoukko(int kapasiteetti) {
		this(kapasiteetti, OLETUSKASVATUS);

	}

	public IntJoukko(int kapasiteetti, int kasvatuskoko) {
		if (kapasiteetti < 0 || kasvatuskoko < 0) {
			return;
		}
		lukuJono = new int[kapasiteetti];
		Arrays.fill(lukuJono, 0);
		this.kasvatuskoko = kasvatuskoko;
	}

	public boolean lisaa(int luku) {
		if (!kuuluu(luku)) {
			lukuJono[alkioidenLkm] = luku;
			alkioidenLkm++;
			tarkistaTila();
			return true;
		}
		return false;
	}

	private void tarkistaTila() {
		if (alkioidenLkm % lukuJono.length == 0) {
			int[] taulukkoOld = lukuJono;
			lukuJono = new int[alkioidenLkm + kasvatuskoko];
			kopioiTaulukko(taulukkoOld, lukuJono);
		}
	}

	private void kopioiTaulukko(int[] vanha, int[] uusi) {
		for (int i = 0; i < vanha.length; i++) {
			uusi[i] = vanha[i];
		}

	}

	public boolean kuuluu(int luku) {
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == lukuJono[i]) {
				return true;
			}
		}
		return false;

	}

	public boolean poista(int luku) {
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == lukuJono[i]) {
				siirraPoistettava(i);
				alkioidenLkm--;
				return true;
			}
		}
		return false;
	}

	public void siirraPoistettava(int i) {
		for (int j = i; j < alkioidenLkm - 1; j++) {
			int apu = lukuJono[j];
			lukuJono[j] = lukuJono[j + 1];
			lukuJono[j + 1] = apu;
		}
	}

	public int getAlkioidenLukumaara() {
		return alkioidenLkm;
	}

	@Override
	public String toString() {
		if (alkioidenLkm == 0) {
			return "{}";
		} else if (alkioidenLkm == 1) {
			return "{" + lukuJono[0] + "}";
		} else {
			String tuotos = "{";
			for (int i = 0; i < alkioidenLkm - 1; i++) {
				tuotos += lukuJono[i];
				tuotos += ", ";
			}
			tuotos += lukuJono[alkioidenLkm - 1];
			tuotos += "}";
			return tuotos;
		}
	}

	public int[] toIntArray() {
		int[] taulu = new int[alkioidenLkm];
		for (int i = 0; i < taulu.length; i++) {
			taulu[i] = lukuJono[i];
		}
		return taulu;
	}

	public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
		IntJoukko x = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			x.lisaa(aTaulu[i]);
		}
		for (int i = 0; i < bTaulu.length; i++) {
			x.lisaa(bTaulu[i]);
		}
		return x;
	}

	public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
		IntJoukko y = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			for (int j = 0; j < bTaulu.length; j++) {
				if (aTaulu[i] == bTaulu[j]) {
					y.lisaa(bTaulu[j]);
				}
			}
		}
		return y;

	}

	public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
		IntJoukko z = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			z.lisaa(aTaulu[i]);
		}
		for (int i = 0; i < bTaulu.length; i++) {
			z.poista(i);
		}

		return z;
	}

}
