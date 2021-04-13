package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.GiornaleDao;
import entity.factorybook.Giornale;
import entity.factorybook.Libro;
import javafx.collections.ObservableList;

public class ControllerModifGiornale {
	private GiornaleDao gd;
	private Giornale g;
	
	public ObservableList<Giornale> getGiornaliById(int id) throws SQLException {
		g.setId(id);
		return gd.getGiornaliSingoloById(g);
	}
	
		
		public boolean checkData(String titolo, String tipo, String ed, String l, LocalDate d, int dispo, float prezzo,
				int copie) throws SQLException {
			g.setTitolo(titolo);
			g.setTipologia(tipo);
			g.setEditore(ed);
			g.setLingua(l);
			g.setDataPubb(d);
			g.setDisponibilita(dispo);
			g.setPrezzo(prezzo);
			g.setCopieRimanenti(copie);
			gd.aggiornaGiornale(g);
			
			return false;
		}
		
		public ControllerModifGiornale()
		{
			gd=new GiornaleDao();
			g=new Giornale();
		}
	


}
