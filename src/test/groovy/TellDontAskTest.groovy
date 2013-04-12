import static org.junit.Assert.*

import java.util.concurrent.atomic.AtomicInteger

import org.junit.Test

class TellDontAskTest {

  @Test
  void eine_lebende_Zelle_mit_weniger_als_zwei_Nachbarn_stirbt() {
    Welt welt = new Welt()

    SpyZelle speisell = new SpyZelle(new Position(0, 0))
    welt.addZelle(speisell)

    welt.tick()

    speisell.assertDied()
  }

  @Test
  void eine_lebende_Zelle_mit_zwei_Nachbarn_lebt_weiter() {

    Welt welt = new Welt()

    SpyZelle spyCell1 = new SpyZelle(new Position(0, 0))
    SpyZelle spyCell2 = new SpyZelle(new Position(0, 1))
    SpyZelle spyCell3 = new SpyZelle(new Position(1, 0))

    welt.addZelle(spyCell1)
    welt.addZelle(spyCell2)
    welt.addZelle(spyCell3)

    welt.tick()

    spyCell1.assertStayedAlive()
  }
  
  @Test
  void eine_lebende_Zelle_mit_mehr_als_drei_Nachbarn_stirbt() {
    
    Welt welt = new Welt()
    
    SpyZelle lebendeZelle = new SpyZelle(new Position(0, 0))
    
    Zelle cell1 = new Zelle(new Position(0, -1))
    Zelle cell2 = new Zelle(new Position(0, 1))
    Zelle cell3 = new Zelle(new Position(1, 0))
    Zelle cell4 = new Zelle(new Position(1, 1))
    
    welt.addZelle(lebendeZelle)
    welt.addZelle(cell1)
    welt.addZelle(cell2)
    welt.addZelle(cell3)
    welt.addZelle(cell4)
    
    welt.tick()
    
    lebendeZelle.assertDied()
  }
  
  //@Ignore("Zu großer Schritt. Need subcycles!!!11elf")
  @Test
  void eine_tote_Zelle_mit_genau_drei_lebenden_Nachbarn_wird_zum_Leben_erweckt() {
     
    Welt welt = new Welt()
    
    Zelle cell1 = new Zelle(new Position(0, -1))
    Zelle cell2 = new Zelle(new Position(0, 0))
    Zelle cell3 = new Zelle(new Position(0, 1))
    
    welt.addZelle(cell1)
    welt.addZelle(cell2)
    welt.addZelle(cell3)
    
    welt.tick()
    
    welt.ifTotAt(1, 0, {
        fail("Zelle sollte leben")
    })
  }
  
  @Test
  void eine_tote_Zelle_sollte_tot_sein() {\
	  Welt welt = new Welt()
	  boolean called = false
	  welt.ifTotAt(0, 0) {
		  called = true
	  }
	  assertTrue(called)
  }

  @Test
  void eine_tote_Zelle_neben_einer_lebendigen_Zelle_sollte_tot_sein() {
	  Welt welt = new Welt()
	  welt.addZelle(new Zelle(new Position(0, 0)))

	  welt.ifTotAt(0, 0) {
		  fail("Sollte lebendig sein")
	  }

	  boolean called = false
	  welt.ifTotAt(0, 1) {
		  called = true
	  }
	  assertTrue(called)
  }

  @Test
  void eine_zelle_sollte_acht_nachbarn_haben() {
    Zelle cell = new Zelle(new Position(0, 0))
    int count = 0
    cell.NachbarKoordinaten {
        count++
    }
    assertEquals(8, count)
  }

  @Test
  public void itShouldSupportWith() {
	  withVar("hallo") {
		  assertEquals("hallo", it)
	  }
  }

  @Test
  public void itShouldSupportLambdaWith() {
	  def block = { str ->
		  sleep(5000)
		  "hallo, $str"
	  }.memoize()

	  assertEquals "hallo, world", block.call("world").toString()
	  println "One"
	  assertEquals "hallo, world", block.call("world").toString()
	  println "Two"
  }

  def withVar(variable, Closure block) {
	  block.call(variable)
  }

  // @Test
  // void sollte_zwei_Nachbarn_zaehlen() {
  // Zelle zelle = new Zelle(new Position(0, 0))
  //
  // List<Zelle> alleZellen = new ArrayList<Zelle>()
  // alleZellen.add(new Zelle(new Position(0, 1)))
  // alleZellen.add(new Zelle(new Position(1, 0)))
  //
  // assertEquals(2, Welt.anzahlLebendeNachbarn(zelle, alleZellen))
  // }
  //
  // @Test
  // void sollte_keine_Nachbarn_zaehlen() {
  // Zelle zelle = new Zelle(new Position(0, 0))
  //
  // List<Zelle> alleZellen = new ArrayList<Zelle>()
  //
  // assertEquals(0, Welt.anzahlLebendeNachbarn(zelle, alleZellen))
  // }

  static class SpyZelle extends Zelle {

    SpyZelle(Position koordinaten) {
      super(koordinaten)
    }

    void assertStayedAlive() {
      assertTrue("Zelle sollte am Leben geblieben sein", lebtZelleInNaechsterGeneration)
    }

    void assertDied() {
      assertFalse("Zelle sollte tot sein!", lebtZelleInNaechsterGeneration)
    }

  }

}
