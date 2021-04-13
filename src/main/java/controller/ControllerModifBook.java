package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.LibroDao;
import entity.factorybook.Libro;
import javafx.collections.ObservableList;

public class ControllerModifBook {
	private LibroDao ld;
	private Libro l;
	
	
	public ObservableList<Libro> getLibriById(int id) throws SQLException {
		l.setId(id);
		return ld.getLibriSingoloById(l);
	}
	
	
	
	public ControllerModifBook()
	{
		ld=new LibroDao();
		l=new Libro();
	}
	
	public void checkData(String titolo, int numPag, String codIsbn, String editore, String autore, String lingua,
			String categoria, LocalDate data, String recensione, int disp,	float prezzo, int copieRim,int idL) throws NullPointerException, SQLException 
	{
	
		l.setTitolo(titolo);
		l.setNumPag(numPag);
		l.setCodIsbn(codIsbn);
		l.setEditore(editore);
		l.setAutore(autore);
		l.setLingua(lingua);
		l.setCategoria(categoria);
		l.setDataPubb(data);
		l.setRecensione(recensione);
		l.setDisponibilita(disp);
		l.setPrezzo(prezzo);
		l.setCopieRim(copieRim);
		l.setId(idL);
		
		ld.aggiornaLibro(l);


		System.out.println("\n\tHo settato tutto nel controller\n");
	}
	
	

}
