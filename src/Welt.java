import java.util.ArrayList;
import java.util.List;


public class Welt {

	List<Zelle> aktuelleLebendePopulation = new ArrayList<Zelle> ();
	
	public Welt() {
	}

	public void tick() {
		List<Zelle> naechsteGeneration = new ArrayList<Zelle>();
		
		for (final Zelle zelle : aktuelleLebendePopulation) {
			
			zelle.ifHatWenigerAlsZweiLebendeNachbarn(aktuelleLebendePopulation, new Closure() {
				@Override
				public void execute() {
					// Regel 1 > stirbt
					zelle.stirb();
				}
			});
      zelle.ifHatMehrAlsDreiLebendeNachbarn(aktuelleLebendePopulation, new Closure() {
        @Override
        public void execute() {
          zelle.stirb();
        }
      });
			zelle.temp(naechsteGeneration);
		}
		
		aktuelleLebendePopulation = naechsteGeneration;
	}

	public void addZelle(Zelle zelle) {
		aktuelleLebendePopulation.add(zelle);
	}

  public void ifTotAt(int x, int y, Closure closure) {
    final BooleanHolder lebendeZelleAtXY = new BooleanHolder();
    for (final Zelle zelle : aktuelleLebendePopulation) {
      zelle.at(x, y, new Closure() {
        @Override
        public void execute() {
          lebendeZelleAtXY.setTrue();
        }
      });
    }
    lebendeZelleAtXY.ifFalse(closure);
  }

  public static class BooleanHolder {
    boolean value = false;

    public void setTrue() {
      value = true;
    }

    public void ifFalse(Closure closure) {
      if (value == false)
        closure.execute();
    }
  }

}
