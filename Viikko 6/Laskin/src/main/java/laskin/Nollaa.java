
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends SuperKomento {

	public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
	}
	
	@Override
	public void suorita() {
		edellinen = sovellus.tulos();
		sovellus.nollaa();
		syotekentta.setText("");
		tuloskentta.setText(""+sovellus.tulos());
		undo.disableProperty().set(false);
		tarkista();
	}

	
}
