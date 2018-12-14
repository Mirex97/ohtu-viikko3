package ohtu.kps;

import ohtu.komponentit.Tekoaly;

public class KPSTekoaly extends AbstractKPS {

	@Override
	public void pelaa() {
		tekoaly = new Tekoaly();
		peliSykli();

	}
}
