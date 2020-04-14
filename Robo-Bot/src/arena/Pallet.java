package arena;

public class Pallet {
	private int x;
	private int y;
	private int id;
	
	public Pallet(int x, int y, int id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public Pallet(int id) {
		super();
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Pallet [x=" + x + ", y=" + y + ", id=" + id + "]";
	} 
	
	
}
