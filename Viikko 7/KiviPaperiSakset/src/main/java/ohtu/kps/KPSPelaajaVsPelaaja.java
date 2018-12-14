package ohtu.kps;

public class KPSPelaajaVsPelaaja extends AbstractKPS {

	@Override
	public void pelaa() {
		tekoaly = null;
		peliSykli();
	}
}
