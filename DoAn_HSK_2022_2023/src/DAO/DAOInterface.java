package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public ArrayList<T> getAll();
	
	public void add();
	
	public void update();
	
	public void delete();
}
