package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public ArrayList<T> getAll();
	
	public void add(T t);
	
	public void update(T t);
	
	public void delete(T t);
}
