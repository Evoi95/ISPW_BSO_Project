package entity;

public class Negozio {

	private String nome;
	private String via;
	private Boolean isValid;
	private Boolean isOpen;
		
	
	public Negozio(String nome, String via, Boolean isValid, Boolean isOpen) {
		this.nome = nome;
		this.via = via;
		this.isValid = isValid;
		this.isOpen = isOpen;
	}
	public Negozio() {
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	
	
	

	
}
