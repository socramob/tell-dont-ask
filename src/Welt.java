import java.util.ArrayList;
import java.util.List;


public class Welt {

	List<Zelle> aktuellePopulation = new ArrayList<Zelle> ();
	
	public Welt() {
	}

	public void tick() {
		List<Zelle> naechsteGeneration = new ArrayList<Zelle>();
		
		for (final Zelle zelle : aktuellePopulation) {
			
			zelle.ifHatWenigerAlsZweiLebendeNachbarn(aktuellePopulation, new Closure() {
				@Override
				public void execute() {
					// Regel 1 > stirbt
					zelle.stirb();
				}
			});
			zelle.temp(naechsteGeneration);
		}
		
		aktuellePopulation = naechsteGeneration;
	}

	public void addZelle(Zelle zelle) {
		// TODO Auto-generated method stub
		aktuellePopulation.add(zelle);
	}

}
