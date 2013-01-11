import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TellDontAskTest {

	@Test
	public void eine_lebende_Zelle_mit_weniger_als_zwei_Nachbarn_stirbt() {
		Welt welt = new Welt();
		
        SpyZelle spyCell = new SpyZelle(new Koordinaten(0, 0));
        welt.addZelle(spyCell);

        welt.tick();

        spyCell.assertDied();
	}
	
	
	@Test
	public void eine_lebende_Zelle_mit_zwei_Nachbarn_lebt_weiter() {
	
		Welt welt = new Welt();
		
        SpyZelle spyCell1 = new SpyZelle(new Koordinaten(0, 0));
        SpyZelle spyCell2 = new SpyZelle(new Koordinaten(0, 1));
        SpyZelle spyCell3 = new SpyZelle(new Koordinaten(1, 0));
        
        welt.addZelle(spyCell1);
        welt.addZelle(spyCell2);
        welt.addZelle(spyCell3);

        welt.tick();

        spyCell1.assertStayedAlive();
	}
	
//	@Test
//	public void sollte_zwei_Nachbarn_zaehlen() {
//		Zelle zelle = new Zelle(new Koordinaten(0, 0));
//		
//		List<Zelle> alleZellen = new ArrayList<Zelle>();
//		alleZellen.add(new Zelle(new Koordinaten(0, 1)));
//		alleZellen.add(new Zelle(new Koordinaten(1, 0)));
//
//		assertEquals(2, Welt.anzahlLebendeNachbarn(zelle, alleZellen));
//	}
//	
//	@Test
//	public void sollte_keine_Nachbarn_zaehlen() {
//		Zelle zelle = new Zelle(new Koordinaten(0, 0));
//		
//		List<Zelle> alleZellen = new ArrayList<Zelle>();
//
//		assertEquals(0, Welt.anzahlLebendeNachbarn(zelle, alleZellen));
//	}
	
	static class SpyZelle extends Zelle {

		public SpyZelle(Koordinaten koordinaten) {
			super (koordinaten);
		}

		public void assertStayedAlive() {
			assertTrue("Zelle sollte am Leben geblieben sein", zelleLebt);
		}

		public void assertDied() {
			assertFalse("Zelle sollte tot sein!", zelleLebt);
		}
		
	}

}
