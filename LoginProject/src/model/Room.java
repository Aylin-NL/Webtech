package model;

public class Room {
	private int surfaceArea;
	private int price;
	private String location;
	
	public Room(int sa, int p, String l) {
		surfaceArea = sa;
		price = p;
		location = l;
	}

	public int getSurfaceArea() {
		return surfaceArea;
	}

	public int getPrice() {
		return price;
	}

	public String getLocation() {
		return location;
	}
	
	
	
	
}
