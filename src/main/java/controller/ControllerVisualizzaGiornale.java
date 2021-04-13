package controller;

import java.sql.SQLException;

import database.GiornaleDao;
import entity.factorybook.Giornale;



public class ControllerVisualizzaGiornale {
	
	private GiornaleDao gD;
	private Giornale g;
	private int tempIdGior;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;
	
	public ControllerVisualizzaGiornale()
	{
		gD = new GiornaleDao();
	}
	
	public void setID(String i)
	{		
		tempIdGior = Integer.parseInt(i) ;
		vis.getIstance().setId(tempIdGior);
	}
	public int getID()
	{
		System.out.println(vis.getIstance().getId());
		return vis.getIstance().getId();
	}
	public Giornale getData(int i) throws SQLException
	{
		// imposto che è un giornale nel controller
		vis.getIstance().setTypeAsDaily();
		return  gD.getGiornale(g,i);
		//return L;
	}

}
