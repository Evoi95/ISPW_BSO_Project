package entity.users.singelton;

import java.time.LocalDate;

public class Scrittore {

	private String idRuolo,nome,cognome,email,password,descrizione;
	private LocalDate dataDiNascita;
	// array di codici isbn presi dai libri 
	
	public Scrittore () {

		// tocca creare lo scrittore 
	
	}
	
	
	public Scrittore(TempUser u) {
		this.idRuolo = TempUser.getInstance().getIdRuolo();
		this.nome = TempUser.getInstance().getNome();
		this.cognome = TempUser.getInstance().getCognome();
		this.email = TempUser.getInstance().getEmail();
		this.password = TempUser.getInstance().getPassword();
		this.descrizione = TempUser.getInstance().getDescrizione();
		this.dataDiNascita =TempUser.getInstance().getDataDiNascita();
	}


	public String getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(String idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	

	
}
