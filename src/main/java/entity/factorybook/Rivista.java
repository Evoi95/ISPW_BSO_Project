package entity.factorybook;

import java.time.LocalDate;



public class Rivista implements Raccolta  {
	
	
	
	private String titolo,tipologia,autore,lingua,editore,descrizione;
	private LocalDate dataPubb;
	private int disp;
	private float prezzo;
	private int copieRim;
	private int id;
	
	public String getTitolo() {
		return this.titolo;
	}
	public String getTipologia() {
		return this.tipologia;
	}
	public String getAutore() {
		return this.autore;
	}
	public String getLingua() {
		return this.lingua;
	}
	public String getEditore() {
		return this.editore;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public LocalDate getDataPubb() {
		return this.dataPubb;
	}
	public int getDisp() {
		return this.disp;
	}
	public float getPrezzo() {
		return this.prezzo;
	}
	public int getCopieRim() {
		return this.copieRim;
	}
	public int getId() {
		return this.id;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public void setCopieRim(int copieRim) {
		this.copieRim = copieRim;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public void compra() {
		
		
	}
	@Override
	public void scarica() {
		
		
	}
	@Override
	public void leggi() {
		
		
	}
	public Rivista(String titolo, String tipologia, String autore, String lingua, String editore, String descrizione,
			LocalDate dataPubb2, int disp, float prezzo, int copieRim,int id) {
		//super();
		this.titolo = titolo;
		this.tipologia = tipologia;
		this.autore = autore;
		this.lingua = lingua;
		this.editore = editore;
		this.descrizione = descrizione;
		this.dataPubb = dataPubb2;
		this.disp = disp;
		this.prezzo = prezzo;
		this.copieRim = copieRim;
		this.id = id;
	}
	public Rivista() {
		this.titolo = null;
		this.tipologia = null;
		this.autore = null;
		this.lingua = null;
		this.editore = null;
		this.descrizione = null;
		this.dataPubb = null;
		this.disp = 0;
		this.prezzo =0;
		this.copieRim = 0;
		this.id = -1;
	}
	
	
	
}
