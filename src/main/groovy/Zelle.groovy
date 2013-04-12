public class Zelle {

	boolean lebtZelleInNaechsterGeneration = true;
	final Position koordinaten;
	

	public Zelle(Position koordinaten) {
		this.koordinaten = koordinaten;
	}

	public void stirb() {
		lebtZelleInNaechsterGeneration = false;
	}
	
	public void temp(List<Zelle> population) {
		if (lebtZelleInNaechsterGeneration) {
			population.add(this);
		}
	}

	private boolean istNachbarVon(Zelle zelle) {
		int deltaX = Math.abs(zelle.koordinaten.x - koordinaten.x);
		int deltaY = Math.abs(zelle.koordinaten.y - koordinaten.y);
		return (deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0);
	}

	private int anzahlLebenderNachbarn(List<Zelle> population) {
    int anzahlLebenderNachbarn = 0;
		for (Zelle zelle : population) {
			if (zelle != this) {
				if(istNachbarVon(zelle) ) {
					anzahlLebenderNachbarn++;
				}
			}
		}
    return anzahlLebenderNachbarn;
  }

	void ifHatWenigerAlsZweiLebendeNachbarn(List<Zelle> population, Closure closure) {
		if (anzahlLebenderNachbarn(population) < 2) {
			closure.execute();
		}
	}

  public void ifHatMehrAlsDreiLebendeNachbarn(List<Zelle> population,
      Closure closure) {
    if (anzahlLebenderNachbarn(population) > 3) {
      closure.execute();
    }
  }

  public void locatedAt(int x, int y, Closure closure) {
    koordinaten.at(x, y, closure);
  }

  public void NachbarKoordinaten(Closure each) {
    this.koordinaten.NachbarKoordinaten(each);
  }

  public void ifHatDreiLebendeNachbarn(List<Zelle> aktuelleLebendePopulation,
      Closure closure) {
    if(anzahlLebenderNachbarn(aktuelleLebendePopulation) == 3) {
      closure.execute();
    }
  }
  
  @Override
  public String toString() {
    return "@("+koordinaten.x+""+koordinaten.y+")";
  }
}
