package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.RivistaDao;
import entity.factorybook.Libro;
import entity.factorybook.Rivista;
import javafx.collections.ObservableList;

public class ControllerModifRivistaPage {
	
	
	private Rivista r;
	private RivistaDao rd;
	
	
	public ObservableList<Rivista> getRivistaById(int id) throws SQLException {
		r.setId(id);
		return rd.getRivistaSingoloById(r);
	}
	
	public ControllerModifRivistaPage()
	{ 
		r=new Rivista();
		rd=new RivistaDao();
	}

	public void checkData(String t, String tipologia, String autore, String l, String e, String desc, LocalDate d,
			int dispo, float prezzo, int copie, int id) throws SQLException {
		
		r.setTitolo(t);
		r.setTipologia(tipologia);
		r.setAutore(autore);
		r.setLingua(l);
		r.setEditore(e);
		r.setDescrizione(desc);
		r.setDataPubb(d);
		r.setDisp(dispo);
		r.setPrezzo(prezzo);
		r.setCopieRim(copie);
		r.setId(id);
		
		rd.aggiornaRivista(r);
		
		
	}
	

}
