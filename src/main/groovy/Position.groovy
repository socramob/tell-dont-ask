
class Position {

	int x,y
	
	Position(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x
		this.y = y
	}

  void at(int x, int y, Closure closure) {
    if (x == this.x && y == this.y) {
      closure.call()
    }
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this.is(obj))
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Position other = (Position) obj;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }

  void NachbarKoordinaten(Closure each) {
    each.call(new Position(this.x-1, this.y-1))
    each.call(new Position(this.x-1, this.y))
    each.call(new Position(this.x-1, this.y+1))
    each.call(new Position(this.x, this.y+1))
    each.call(new Position(this.x+1, this.y+1))
    each.call(new Position(this.x+1, this.y))
    each.call(new Position(this.x+1, this.y-1))
    each.call(new Position(this.x, this.y-1))
  }

}
