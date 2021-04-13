package controller;

import database.RivistaDao;
import entity.factorybook.Rivista;

public class ControllerCancellaRivista {
	private Rivista r;
	private RivistaDao rd;

	public void cancella(int id) {
		r.setId(id);
		rd.cancella(r);
		
		
		
	}
	public ControllerCancellaRivista()
	{
		r=new Rivista();
		rd=new RivistaDao();
	}

}
