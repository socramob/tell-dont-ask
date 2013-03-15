import java.util.ArrayList;
import java.util.List;

public class Welt {

  List<Zelle> aktuelleLebendePopulation = new ArrayList<Zelle>();

  public Welt() {
  }

  public void tick() {
    final List<Zelle> naechsteGeneration = new ArrayList<Zelle>();

    for (final Zelle zelle : aktuelleLebendePopulation) {

      zelle.ifHatWenigerAlsZweiLebendeNachbarn(aktuelleLebendePopulation,
          new Closure() {
            @Override
            public void execute(Object... args) {
              // Regel 1 > stirbt
              zelle.stirb();
            }
          });
      zelle.ifHatMehrAlsDreiLebendeNachbarn(aktuelleLebendePopulation,
          new Closure() {
            @Override
            public void execute(Object... args) {
              zelle.stirb();
            }
          });
      zelle.temp(naechsteGeneration);

      zelle.NachbarKoordinaten(new Closure() {

        @Override
        public void execute(Object... args) {
          // 8-)
          final Position position = (Position) args[0];
          ifTotAt(position, new Closure() {

            @Override
            public void execute(Object... args) {
              // Zelle an koordinaten ist TOT
              final Zelle heisenCell = new Zelle(position);
              heisenCell.ifHatDreiLebendeNachbarn(aktuelleLebendePopulation, new Closure() {
                
                @Override
                public void execute(Object... args) {
                 heisenCell.temp(naechsteGeneration);
                }
              });

            }
          });
        }
      });
      // für jeden Nachbar,
      // wenn dieser tot ist,
      // dann stelle Anzahl der lebenden Nachbarn in der
      // aktuellen lebenden Population des toten Nachbarn fest
      // wenn Anzahl == 3
      // dann erwecke den Nachbarn von den Toten (Zombies)
    }

    aktuelleLebendePopulation = naechsteGeneration;
  }

  protected void ifTotAt(Position koordinaten, Closure closure) {
    ifTotAt(koordinaten.x, koordinaten.y, closure);
  }

  public void addZelle(Zelle zelle) {
    aktuelleLebendePopulation.add(zelle);
  }

  public void ifTotAt(int x, int y, Closure closure) {
    final BooleanHolder lebendeZelleAtXY = new BooleanHolder();
    for (final Zelle zelle : aktuelleLebendePopulation) {
      zelle.locatedAt(x, y, new Closure() {
        @Override
        public void execute(Object... args) {
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
