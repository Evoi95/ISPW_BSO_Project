package database;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDefaultDB 
{
	private static boolean status ;
	private static Statement st = null ;
	private static String query ;
	private static String qTrigger ;
	private static PreparedStatement prepQ = null;


	//private static CallableStatement cStmt;

	
	public static void createDefaultDB () throws SQLException, ClassNotFoundException, FileNotFoundException
	{
		try 
		{
			status = ConnToDb.InitailConnection() && !ConnToDb.connection() ; 
			// status = 1 se c'e' workbench ma non il db 
			// status = 0 se c'e' tutto
			// Se non trovo il db chiamo questa funzione che lo crea
			if(status == true) 
			{
				//
				st = ConnToDb.conn.createStatement();
				query="CREATE DATABASE IF NOT EXISTS ispw ";
				st.execute(query);
				query = "USE ispw ";
				st.execute(query);
				System.out.println("Connesso a mysql workbench, ma non ho torvato il database 'ispw'\n"
						+ "Database creato \n"
						+ " Chiamo la Stored Procedure, per creare le tabelle");
				
				query=	"CREATE TABLE if not exists USERS (	idUser INT primary key not null auto_increment, idRuolo VARCHAR(1) NOT NULL DEFAULT 'U, Nome VARCHAR(40), Cognome VARCHAR(40),Email VARCHAR(50) UNIQUE , pwd VARCHAR(16),descrizione text, DataDiNascita date);";
				st.executeUpdate(query);
				
				query=	"Create table  if not exists AMMINISTRATORE"
						+ "	( idAdmin int primary key not null auto_increment,"
						+ "	  idUser int,"
						+ "   FOREIGN KEY (idUser) REFERENCES USERS(idUser) );";
				st.executeUpdate(query);			
					
				query=	"Create table  if not exists EDITORE "
						+ "	( idEditor int primary key not null auto_increment,"
						+ "	  idUser int, casaEditrice VARCHAR (200), "
						+ "   FOREIGN KEY (idUser) REFERENCES USERS(idUser) "
						+ "      ); ";
				st.executeUpdate(query);				
				
				query=	"Create table  if not exists SCRITTORI"
						+ "	( idScrittore int primary key not null auto_increment,"
						+ "	  idUser int, editoreAssociato int, "
						+ "	  FOREIGN KEY (editoreAssociato) REFERENCES EDITORE(idEditor) ,"
						+ "   FOREIGN KEY (idUser) REFERENCES USERS(idUser) );";
				st.executeUpdate(query);
				
				
				query=	"Create table  if not exists LIBRO "
						+ "	( titolo VARCHAR(200), numeroPagine int, "
						+ " Cod_isbn varchar(10)  not null unique ,"
						+ "	editore varchar(200) ,"
						+ " autore varchar(200), lingua varchar(10),"
						+ " categoria Varchar(60), dataPubblicazione date,"
						+ " recensione text, copieVendute int, breveDescrizione text,"
						+ " disp int, prezzo float,"
						+ " copieRimanenti int,"
						+ "id_prod int primary key auto_increment);";
				st.executeUpdate(query);
				
				query=	"create table if not exists pagamento("
						+ "id_op int primary key auto_increment,"
						+ "metodo varchar(10),esito int ,"
						+ "nomeUtente varchar(10),spesaTotale float,"
						+ "eMail varchar(100 ),"
						+ "tipoAcquisto varchar(20),"
						+ "id_prod int )";
						
						st.executeUpdate(query);
				
				query=	"Create table if not exists RIVISTA "
						+ "	( titolo VARCHAR(200),tipologia Varchar(60),"
						+ "	autore varchar(200), lingua varchar(10),"
						+ "	 editore varchar(200) ,"
						+ "	Descrizione text, dataPubblicazione date,"
						+ " disp int,"
						+ "	prezzo float,"
						+ "	copieRimanenti int,"
						+ "id int primary key not null auto_increment);";
				st.executeUpdate(query);
				
				query=	"Create table if not exists GIORNALE "
						+ "	( titolo VARCHAR(200),tipologia Varchar(60),"
						+ "	lingua varchar(10),"
						+ "	editore varchar(200) ,"
						+ "	dataPubblicazione date,"
						+ " copiRim int, "
						+ "	disp int,"
						+ "	prezzo float,"
						+ " id int primary key not null auto_increment);";
				st.executeUpdate(query);
				
				query=	"Create table if not exists cartacredito "
						+ "	( nomeP VARCHAR(10),cognomeP  Varchar(20),"
						+ "	codiceCarta varchar(20),"
						+ "	scad date ,"
						+ "	codicePin varchar(5) ,"
						+ " ammontare float );";
				st.executeUpdate(query);
				
				query=	"Create table if not exists FATTURA "
						+ "	( nome varchar(10),cognome varchar(10),"
						+ "	via varchar(50),"
						+ "	comunicazoni text,"
						+ " id int auto_increment not null  primary key,"
						+ " ammontare float);";
				st.executeUpdate(query);
			
				query= "Create table if not exists NEGOZIO"
						+ "( idNegozio int not null auto_increment primary key, "
						+ "nome VARCHAR(100) , via VARCHAR(100),"
						+ " isValid boolean, isOpen BOOLEAN"
						+ ");";
				st.executeUpdate(query);

				System.out.println("Tabelle create!");
				if (PopulateDefaultDb.populateDefaultDb()) {
					System.out.println("Tabella populata con valori di default");
					if (true)
					{
					//if (createTrigger()) {
						ConnToDb.conn.close();
						System.out.println("Trigger creati e connesione chiusa col db");
					}
					else
					{
						System.err.println("Ops..! qualcosa è andato storto nella creazione dei trigger !");

					}
				}
				else
				{
					System.err.println("Ops..! qualcosa è andato storto nel populare il database!");
				}
			}
			
			// Se trovo tutto  chiudo la connesione e vado avanti con l'esecuzione del programma
			else if (status == false)
			{
				System.out.println("Trovato database e connesso senza problemi! Buone madonne!");
				ConnToDb.conn.close();		
			}
			// Se qualcosa non va chiudo la connessione e vado nel cacth
			else 
			{
				System.err.println("Ops..! qualcosa è andato storto nella connesione al database!");
				ConnToDb.conn.close();		

			}
		}
		catch(SQLException e1) 
		{
			e1.printStackTrace();
			System.err.println("ERRORE DI SQL ");
		}
		
		
	}
	
	public static boolean createTrigger() throws SQLException 
	{
		try 
		{		st = ConnToDb.conn.createStatement();
				query = "USE ispw ";
				st.execute(query);
				//0 regolare 1 irregolare
				qTrigger= "delimiter //"
						+ "create trigger pagaFattura after insert on fattura "
						+ "for each row "
						+ "begin  "
						+ "insert into  pagamento values(0,'fattura',0,new.nome,new.ammontare); "
						+ "end; //";
				
				prepQ = ConnToDb.conn.prepareStatement(qTrigger);	

				
				System.out.println("Trigger pagamento triggerato");
				
				qTrigger= "delimiter //"
						+ "create trigger pagaCartaCredito after insert on cartacredito "
						+ "for each row "
						+ "begin "
						+ "insert into  pagamento values(0,'cartac',0,new.nomeP,new.ammontare);"
						+ "end; //";
				prepQ = ConnToDb.conn.prepareStatement(qTrigger);	
				System.out.println("Trigger cartaDiCredito triggerato");
				// TO DO : Mancano altri trigger degli utenti etc...
				return true;
			
		}
		catch(SQLException e1) 
		{
			e1.printStackTrace();
			System.err.println("ERRORE DI SQL ");
		}
		
		return false;
	}
}
