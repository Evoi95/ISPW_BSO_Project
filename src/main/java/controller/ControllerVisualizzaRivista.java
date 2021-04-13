package controller;

import java.sql.SQLException;

import database.RivistaDao;
import entity.factorybook.Rivista;

public class ControllerVisualizzaRivista {
	
	private RivistaDao rD;
	private Rivista R;
	private int tempIdMag;
	private static SingeltonSystemState vis = SingeltonSystemState.getIstance() ;
	
	public ControllerVisualizzaRivista()
	{
		rD = new RivistaDao();
	}
	public void setID(String i)
	{		
		tempIdMag = Integer.parseInt(i) ;
		vis.setId(tempIdMag);
	}
	public int getID()
	{
		System.out.println(vis.getId());
		return vis.getId();
	}
	public Rivista getData(int i) throws SQLException
	{
		// imposto che è un libro nel controller
		vis.setTypeAsMagazine();
		return  rD.getRivista(R,i);
		//return L;
	}
}
