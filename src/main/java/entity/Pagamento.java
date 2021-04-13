package entity;

public class Pagamento {
	private int id;
	private String metodo;
	private int esito; //0 ok 1 fallito
	private String nomeUtente;
	private float ammontare;
	private String tipo;
	public String getTipo() {
		return tipo;
	}
	public int getIdOg() {
		return idOg;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setIdOg(int idOg) {
		this.idOg = idOg;
	}
	private int idOg;
	public int getId() {
		return this.id;
	}
	public String getMetodo() {
		return this.metodo;
	}
	public int getEsito() {
		return this.esito;
	}
	public String getNomeUtente() {
		return this.nomeUtente;
	}
	public float getAmmontare() {
		return this.ammontare;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public void setEsito(int esito) {
		this.esito = esito;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public void setAmmontare(float ammontare) {
		this.ammontare = ammontare;
	}
	public Pagamento(int id, String metodo, int esito, String nomeUtente, float ammontare,String tipologia,int idOg) {
		//super();
		this.id = id;
		this.metodo = metodo;
		this.esito = esito;
		this.nomeUtente = nomeUtente;
		this.ammontare = ammontare;
		this.tipo=tipologia;
		this.idOg=idOg;
	}
	/*
	public Pagamento(float totale)
	{
		this.id=0;
		this.metodo=null;
		this.esito=-1;
		this.nomeUtente=null;
		this.ammontare=totale;
		
	}*/
	
}
