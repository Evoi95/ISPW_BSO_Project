package controller;

import java.sql.SQLException;

import database.LibroDao;
import entity.factorybook.Libro;

public class ControllerVisualizzaLibro {
	
	private LibroDao ld;
	private Libro b;
	private int tempIdLib;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;
	
	public ControllerVisualizzaLibro()
	{
		ld = new LibroDao();
	}
	public void setID(String i)
	{		
		tempIdLib = Integer.parseInt(i) ;
		vis.setId(tempIdLib);
	}
	public int getID()
	{
		System.out.println(vis.getId());
		return vis.getId();
	}
	public Libro getData(int i) throws SQLException
	{
		// imposto che è un libro nel controller
		vis.setTypeAsBook();
		return  ld.getLibro(b,i);
		//return L;
	}
}