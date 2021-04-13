package entity.factorybook;

import java.time.LocalDate;


public class Giornale implements Raccolta {
	
	
	private String  titolo,tipologia,lingua,editore;
	private LocalDate dataPubb;
	private int copieRimanenti;
	private int disponibilita;
	private float prezzo;
	private int id;
	

	

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public String getEditore() {
		return editore;
	}

	public void setEditore(String editore) {
		this.editore = editore;
	}

	public LocalDate getDataPubb() {
		return dataPubb;
	}

	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}

	public int getCopieRimanenti() {
		return copieRimanenti;
	}

	public void setCopieRimanenti(int copieRimanenti) {
		this.copieRimanenti = copieRimanenti;
	}

	public int getDisponibilita() {
		return this.disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
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

	public Giornale(String titolo, String tipologia, String lingua, String editore, LocalDate dataPubb2, int copieRimanenti,
			int disponibilita, float prezzo, int id ) {
		super();
		this.titolo = titolo;
		this.tipologia = tipologia;
		this.lingua = lingua;
		this.editore = editore;
		this.dataPubb = dataPubb2;
		this.copieRimanenti = copieRimanenti;
		this.disponibilita = disponibilita;
		this.prezzo = prezzo;
		this.id = id;
	}
	
	public Giornale()
	{
		super();
		this.titolo=null;
		this.tipologia=null;
		this.lingua=null;
		this.editore=null;
		this.dataPubb=null;
		this.copieRimanenti=0;
		this.disponibilita=0;
		this.prezzo=0;
		this.id = -1;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
