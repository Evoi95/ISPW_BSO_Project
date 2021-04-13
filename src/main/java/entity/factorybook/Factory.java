package entity.factorybook;

import java.time.LocalDate;


public class Factory {
	
	
	public Raccolta createRaccolta(String tipologia,String titolo, int numPag, String codIsbn, String editore, String autore, String lingua,
			String categoria, LocalDate dataPubb, String recensione, int nrCopie, String desc, int disponibilita,
			float prezzo, int copieRim,int id) throws Exception
	{
		
		switch(tipologia)
		{
		// sistemato con le nuove schermate 21/01/2021
		case "libro": return new Libro(titolo,numPag,codIsbn,editore,autore,lingua,categoria,dataPubb,recensione,nrCopie,desc,disponibilita,prezzo,copieRim,id); 
		// no 
		case "giornale" :return new Giornale(titolo,tipologia,lingua,editore,dataPubb,nrCopie,disponibilita,prezzo,id);
		// no
		case "rivista": return new Rivista(titolo,tipologia,autore,lingua,editore,desc,dataPubb,disponibilita,prezzo,copieRim,id);
		
		default : throw new Exception();
		
		}
		

	}	
	
	public Raccolta createLibro(String tipologia,String titolo, int numPag, String codIsbn, String editore, String autore, String lingua,
			String categoria, LocalDate dataPubb, String recensione, int nrCopie, String desc, int disponibilita,
			float prezzo, int copieRim, int id)
	{
	 return new Libro(titolo,numPag,codIsbn,editore,autore,lingua,categoria,dataPubb,recensione,nrCopie,desc,disponibilita,prezzo,copieRim,id); 
	}
	
	
	public Raccolta createRivista(String tipo ,String titolo,String tipologia, String autore, String lingua, String editore, String descrizione,
			LocalDate dataPubb, int disp, float prezzo, int copieRim,int id)
			{
		return new Rivista(titolo,tipo,autore,lingua,editore,descrizione,dataPubb,disp,prezzo,copieRim,id);//autore, dataPubb, dataPubb, dataPubb, dataPubb, dataPubb, prezzo, prezzo, foto, disponibilita);
	}
	
	
	public Raccolta createGiornale(String type,String titolo,String tipologia, String lingua, String editore, LocalDate  date, int copieRimanenti, int disponibilita,
			float prezzo, int id)
	{
		return new Giornale(titolo,tipologia,lingua,editore,date,copieRimanenti,disponibilita,prezzo,id);
			}


	

}
