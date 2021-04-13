package controller;

import java.time.LocalDate;

import database.GiornaleDao;
import entity.factorybook.Giornale;

public class ControllerAddGiornalePage {
	private Giornale g;
	private GiornaleDao gD;
	private boolean status = false;
	
	
	public boolean checkData(String titolo, String tipologia, String editore, String lingua, LocalDate data, int disp, float prezzo, int copieRim)
	{
		
		
		if(data.equals(null) )
		{
			return status;

		}
		else
		{
		g.setTitolo(titolo);
		g.setTipologia(tipologia);
		g.setLingua(lingua);
		g.setEditore(editore);
		g.setDataPubb(data);
		g.setCopieRimanenti(copieRim);
		g.setDisponibilita(disp);
		g.setPrezzo(prezzo);
		gD.creaGiornale(g);
		
		status = true;
		
		return status;
		}
	}
	

	
	
	
	public ControllerAddGiornalePage()
	{
		g=new Giornale();
		gD=new GiornaleDao();
	}

}
