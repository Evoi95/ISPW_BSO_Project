package controller;

import java.time.LocalDate;

import database.LibroDao;
import entity.factorybook.Libro;

public class ControllerAddBookPage {
	private LibroDao ld;
	private Libro l;
	private boolean status = false;
	
	//funzione di aggiunta dei libri
	//e verifica dei dati inseriti 
	
	public boolean checkData(String titolo, int numPag, String codIsbn, String editore, String autore, String lingua,
			String categoria, LocalDate data, String recensione,String desc, int disp,	float prezzo, int copieRim)
	{
		if(codIsbn.length()>10 && data.equals(null) )
		{
			return status;

		}
		else
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
		l.setDesc(desc);
		l.setDisponibilita(disp);
		l.setPrezzo(prezzo);
		l.setCopieRim(copieRim);
		
		ld.creaLibrio(l);
		status = true;
		
		return status;
		}
	}
	// qui chiamo la funzione del dao
	
	public ControllerAddBookPage()
	{
		ld=new LibroDao();
		l=new Libro();
	}

}
