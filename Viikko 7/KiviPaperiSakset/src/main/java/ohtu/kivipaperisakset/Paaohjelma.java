package ohtu.kivipaperisakset;

import ohtu.*;
import ohtu.strategy.Pelimoodi;

public class Paaohjelma {

	public static final IO io = new ConsoleIO();

	public static void main(String[] args) {
		Pelimoodi.suorita();
	}
}
