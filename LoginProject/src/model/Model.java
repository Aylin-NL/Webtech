package model;

import java.util.ArrayList;

public class Model {
	private static Model model;
	private ArrayList<User> users;
	private ArrayList<Room> rooms;
	
	//singleton 
	private Model() {
		users = new ArrayList<User>();
		rooms = new ArrayList<Room>();
	}
	
	public static Model getInstance() {
		if (model == null) {
			model = new Model();
		}
		return model;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	
	
	

}
