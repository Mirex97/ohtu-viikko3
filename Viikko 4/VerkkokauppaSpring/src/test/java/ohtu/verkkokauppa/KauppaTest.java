package ohtu.verkkokauppa;

import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class KauppaTest {

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
	Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(1)).thenReturn(10);
	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

	Kauppa k = new Kauppa(varasto, pankki, viite);

	k.aloitaAsiointi();
	k.lisaaKoriin(1);
	k.tilimaksu("pekka", "12345");

	verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
	
    }
    
    @Test
    public void eriOstoksienPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
	Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(1)).thenReturn(10);
	when(varasto.saldo(2)).thenReturn(10);
	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feikkimaito(vegan)", 999));

	Kauppa k = new Kauppa(varasto, pankki, viite);

	k.aloitaAsiointi();
	k.lisaaKoriin(1);
	k.lisaaKoriin(2);
	k.tilimaksu("pekka", "12345");

	verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(1004));
	
    }
    
    @Test
    public void samojenOstoksienPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
	Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(2)).thenReturn(10);
	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feikkimaito(vegan)", 999));

	Kauppa k = new Kauppa(varasto, pankki, viite);

	k.aloitaAsiointi();
	k.lisaaKoriin(2);
	k.lisaaKoriin(2);
	k.tilimaksu("pekka", "12345");

	verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(1998));
	
    }
    
    @Test
    public void loppuneenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
	Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(1)).thenReturn(10);
	when(varasto.saldo(2)).thenReturn(0);
	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feikkimaito(vegan)", 999));

	Kauppa k = new Kauppa(varasto, pankki, viite);

	k.aloitaAsiointi();
	k.lisaaKoriin(1);
	k.lisaaKoriin(2);
	k.tilimaksu("pekka", "12345");

	verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
	
    }
    
    
    @Test
    public void aloitaAsiointiNollaa() {
	Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(1)).thenReturn(10);
	when(varasto.saldo(2)).thenReturn(10);
	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feikkimaito(vegan)", 999));

	Kauppa k = new Kauppa(varasto, pankki, viite);

	k.aloitaAsiointi();
	k.lisaaKoriin(1);
	k.lisaaKoriin(2);
	k.tilimaksu("pekka", "12345");
	k.aloitaAsiointi();
	k.lisaaKoriin(1);
	k.tilimaksu("pekka", "12345");

	verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
	
	
    }
    
    @Test
    public void pyydetaanUusiViiteJokaiseenMaksuun() {
        Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(1)).thenReturn(10);
	when(varasto.saldo(2)).thenReturn(10);
	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feikkimaito(vegan)", 999));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
	
        verify(viite, times(1)).uusi();
	
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
	
        verify(viite, times(2)).uusi();
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
	
        verify(viite, times(3)).uusi();
    }
    
    @Test
    public void poistaTuotePoistaa() {
	Pankki pankki = mock(Pankki.class);

	Viitegeneraattori viite = mock(Viitegeneraattori.class);
	when(viite.uusi()).thenReturn(42);

	Varasto varasto = mock(Varasto.class);
	when(varasto.saldo(1)).thenReturn(10);
	when(varasto.saldo(2)).thenReturn(10);
	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "feikkimaito(vegan)", 999));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
	k.poistaKorista(1);
	Tuote t = varasto.haeTuote(1);
        verify(varasto, times(1)).palautaVarastoon(t);
    }
    

}
