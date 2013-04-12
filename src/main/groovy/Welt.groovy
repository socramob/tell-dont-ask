public class Welt {

  List<Zelle> aktuelleLebendePopulation = new ArrayList<Zelle>();

  public Welt() {
  }

  public void tick() {
    final List<Zelle> naechsteGeneration = new ArrayList<Zelle>();

    for (final Zelle zelle : aktuelleLebendePopulation) {

      zelle.ifHatWenigerAlsZweiLebendeNachbarn(aktuelleLebendePopulation,
          { Object... arguments ->
              // Regel 1 > stirbt
              zelle.stirb();
          });
      zelle.ifHatMehrAlsDreiLebendeNachbarn(aktuelleLebendePopulation,
          { Object... arguments ->
              zelle.stirb();
          });
      zelle.temp(naechsteGeneration);

      zelle.NachbarKoordinaten({ Object... arguments1 ->
          // 8-)
          final Position position = (Position) arguments1[0];
          ifTotAt(position, { Object... arguments2 ->
              // Zelle an koordinaten ist TOT
              final Zelle heisenCell = new Zelle(position);
              heisenCell.ifHatDreiLebendeNachbarn(aktuelleLebendePopulation, { Object... arguments3 ->
                 heisenCell.temp(naechsteGeneration);
              });
          });
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
      zelle.locatedAt(x, y, { Object... arguments ->
          lebendeZelleAtXY.setTrue();
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
        closure.call();
    }
  }

}
