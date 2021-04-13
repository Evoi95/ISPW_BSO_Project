package entity.users.singelton;


import java.time.LocalDate;

public class User {
	

	private String idRuolo,nome,cognome,email,password,descrizione;
	private LocalDate dataDiNascita;
	private int idU;
	// array di codici isbn presi dai libri 
	
	private static User instance =new User(); ;
	
	private  User()
	{
		
	}
 	/*
	
private User( String nome, String cognome, String email, String password,LocalDate dataDiNascita) {
		//user for registration
		this.nome = nome;		
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.dataDiNascita = dataDiNascita;
	}
	
	// use to abstrac factory AND void user for reset pass 
	private User() 
	{
		
		this.idRuolo = "U"; 
		this.nome = null;
		this.cognome = null;
		this.email = null;
		this.password = null;
		this.dataDiNascita =  null;
	}
	
	private User(String idRuolo, String nome, String cognome, String email, String password, String descrizione,
			LocalDate dataDiNascita, ArrayList<String> listaPreferiti) {
		// user for third use case
		this.idRuolo = idRuolo;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.descrizione = descrizione;
		this.dataDiNascita = dataDiNascita;
		this.listaPreferiti = listaPreferiti;
	}
	
	private  User (String m , String p) {
		// usato per il secondo caso d'urso
		this.email= m;
		this.password  = p;
	}
	*/
	
	// end of construtor
	// start of getters and setters
	
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



	// end of setters and getters
	
	// start method
	
	public static User getInstance()
	{
		return instance;
	}
	public User login( String a) {
		
		// rivedere il pickdata per fare l'assegnaziopne dei dati qui nella classe
		// presi dal dao 
		a="Sono loggato come User generico";
		System.out.println(a);
		return null;
		
	}
		
	public void setNull() {
		
		this.idRuolo = null; 
		this.nome = null;
		this.cognome = null;
		this.email = null;
		this.password = null;
		this.dataDiNascita =  null;
		this.idU=0;
		
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}


	
}