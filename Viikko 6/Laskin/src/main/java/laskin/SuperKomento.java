package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SuperKomento implements Komento {

	protected TextField tuloskentta;
	protected TextField syotekentta;
	protected Button nollaa;
	protected Button undo;
	protected Sovelluslogiikka sovellus;
	protected int edellinen;

	public SuperKomento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.nollaa = nollaa;
		this.undo = undo;
		this.sovellus = sovellus;
	}
	
	protected void tarkista() {
		if (tuloskentta.getText().equals("0")) {
			nollaa.disableProperty().set(true);
		} else {
			nollaa.disableProperty().set(false);
		}
	}

	@Override
	public void suorita() {
		
	}

	@Override
	public void peru() {
		sovellus.setTulos(edellinen);
		undo.disableProperty().set(true);
		tuloskentta.setText(""+sovellus.tulos());
		tarkista();
	}

}
