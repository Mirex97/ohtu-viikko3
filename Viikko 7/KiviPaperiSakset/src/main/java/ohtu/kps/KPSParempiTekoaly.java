package ohtu.kps;

import ohtu.komponentit.TekoalyParannettu;

public class KPSParempiTekoaly extends AbstractKPS {

	@Override
	public void pelaa() {
		tekoaly = new TekoalyParannettu(20);
		peliSykli();
	}

}
