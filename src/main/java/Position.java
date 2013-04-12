
public class Position {

	int x,y;
	
	public Position(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

  public void at(int x, int y, Closure closure) {
    if (x == this.x && y == this.y) {
      closure.execute();
    }
  }

  public void NachbarKoordinaten(Closure each) {
    each.execute(new Position(this.x-1, this.y-1));
    each.execute(new Position(this.x-1, this.y));
    each.execute(new Position(this.x-1, this.y+1));
    each.execute(new Position(this.x, this.y+1));
    each.execute(new Position(this.x+1, this.y+1));
    each.execute(new Position(this.x+1, this.y));
    each.execute(new Position(this.x+1, this.y-1));
    each.execute(new Position(this.x, this.y-1));
  }

}
