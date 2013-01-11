import java.util.List;


public class Zelle {

	boolean zelleLebt = true;
	Koordinaten koordinaten;
	

	public Zelle(Koordinaten koordinaten) {
		this.koordinaten = koordinaten;
	}

	public void stirb() {
		zelleLebt = false;
	}
	
	public void temp(List<Zelle> population) {
		if (zelleLebt) {
			population.add(this);
		}
	}

	boolean istNachbarVon(Zelle zelle) {
		int deltaX = Math.abs(zelle.koordinaten.x - koordinaten.x);
		int deltaY = Math.abs(zelle.koordinaten.y - koordinaten.y);
		return (deltaX <= 1 && deltaY <= 1) && !(deltaX == 0 && deltaY == 0);
	}

	boolean hatWenigerAlsZweiLebendeNachbarn(List<Zelle> population) {
		int anzahlLebenderNachbarn = 0;
		for (Zelle zelle : population) {
			if (zelle != this) {
				if(istNachbarVon(zelle) ) {
					anzahlLebenderNachbarn++;
				}
			}
		}
		boolean hatWenigerAlsZweiLebendeNachbarn = anzahlLebenderNachbarn < 2;
		return hatWenigerAlsZweiLebendeNachbarn;
	}

	void ifHatWenigerAlsZweiLebendeNachbarn(List<Zelle> population, Closure closure) {
		if (hatWenigerAlsZweiLebendeNachbarn(
				population)) {
			closure.execute();
		}
	}
}
