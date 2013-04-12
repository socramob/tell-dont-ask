
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
