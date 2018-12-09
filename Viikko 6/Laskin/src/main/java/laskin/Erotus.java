
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends SuperKomento{
	

	public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
	}

	@Override
	public void suorita() {
		if (syotekentta.getText().isEmpty()) {
			return;
		}
		edellinen = sovellus.tulos();
		sovellus.miinus(Integer.parseInt(syotekentta.getText()));
		tuloskentta.setText(""+sovellus.tulos());
		undo.disableProperty().set(false);
		tarkista();
	}
	
}
