package controller;

import java.time.LocalDate;

import database.RivistaDao;
import entity.factorybook.Rivista;

public class ControllerAddRivistaPage {
	private RivistaDao rd;
	private Rivista r;
	private boolean status = false; 
	
	public boolean checkData(String t, String tipologia, String a, String l, String ed, String desc, LocalDate data,
			int dispo, float prezzo, int copie) {
		
		if(data.equals(null) )
		{
			return status;

		}
		r.setTitolo(t);
		r.setTipologia(tipologia);
		r.setAutore(a);
		r.setLingua(l);
		r.setEditore(ed);
		r.setDescrizione(desc);
		r.setDataPubb(data);
		r.setDisp(dispo);
		r.setPrezzo(prezzo);
		r.setCopieRim(copie);

		rd.creaRivista(r);
		
		status = true ;
		
		return status;
	}
	
	public ControllerAddRivistaPage()
	{
		rd=new RivistaDao();
		r=new Rivista();
	}

}
