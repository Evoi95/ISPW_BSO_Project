package controller;

import database.LibroDao;
import entity.factorybook.Libro;

public class ControllerCancLibro {
	
	private LibroDao lD;
	private Libro l;

	public void cancella(int id) {
		l.setId(id);
		lD.cancella(l);
		
		
	}
	
	public ControllerCancLibro()
	{
		lD=new LibroDao();
		l=new Libro();
	}
	

}
