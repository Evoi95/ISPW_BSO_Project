package controller;

import database.GiornaleDao;
import entity.factorybook.Giornale;

public class ControllerCancellaGiornale {
	private Giornale g;
	private GiornaleDao gD;
	
	public void cancella(int id) {
		g.setId(id);
		gD.cancella(g);
		
		
	}
	
	
	
	public ControllerCancellaGiornale()
	{
		g=new Giornale();
		gD=new GiornaleDao();
	}

}
