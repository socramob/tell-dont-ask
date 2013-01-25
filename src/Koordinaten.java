
public class Koordinaten {

	int x,y;
	
	public Koordinaten(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

  public void at(int x, int y, Closure closure) {
    if (x == this.x && y == this.y) {
      closure.execute();
    }
  }

}
