package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import controller.SingeltonSystemState;
import entity.users.singelton.Admin;
import entity.users.singelton.Editore;
import entity.users.singelton.Scrittore;
import entity.users.singelton.TempUser;
import entity.users.singelton.User;

public class UsersDao  {
	
	private static Statement st = null ;
	private static String query ;
	private static ResultSet rs;
	private static PreparedStatement prepQ = null;
    private static Connection  conn;
	private static User U = User.getInstance();
	private static TempUser Ut = TempUser.getInstance();
	private int max;
	private static String r;
	private boolean state=false;

    // use this function on controller after you had check the email
    // add an user on db after registration
    // prende i dati dall'oggetto che gli abbiamo passato 
    public boolean createUser(User U) throws SQLException
    {
    	boolean state=false;
    	LocalDate d=U.getDataDiNascita();
    	System.out.println("\nD vale : "+d);
    	
    	try 
		{
			if (ConnToDb.connection())
			{
				 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query= "INSERT INTO `ispw`.`users`"
			 			+ "(`Nome`,"
			 			+ "`Cognome`,"
			 			+ "`Email`,"
			 			+ "`pwd`,"
			 			+ "`DataDiNascita`)"
			 			+ "VALUES"
			 			+" "
			 			+ "(?,?,?,?,?)";
				prepQ = ConnToDb.conn.prepareStatement(query);	
				prepQ.setString(1,User.getInstance().getNome()); // titolo
				prepQ.setString(2,User.getInstance().getCognome()); //
				prepQ.setString(3,User.getInstance().getEmail());
		 		prepQ.setString(4, User.getInstance().getPassword());
		 		
		 		//System.out.println("\n\n\nData \n"+User.getInstance().getDataDiNascita());
		 		// alternativa NO se rompe tutto se passi un oggetto di tipo data java lui
		 		// vuole un oggetto di tipo data sql 
				prepQ.setDate(5, java.sql.Date.valueOf(d));  
				prepQ.executeUpdate();
				//conn.close();
			 	System.out.println("utente Inserito con successo");
			 	state= true; // true		 			 	
			}
			else {
		    	System.err.print("Errore inserimento utenete");
		    	state= false ;
		    	}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
    	finally {
    		conn.close();
    	}
		//conn.close();				 	
		return state;
    	
    }
    
    //Uso questa funzione quando un admin deve creare un utente 
    //tramite il terzo caso d'uso per la gestione del sito  
    public boolean createUser2(TempUser Ut) throws SQLException
    {
    	
    	//System.out.println("Data in create User2:"+Ut.getDataDiNascita());
    	LocalDate d=Ut.getDataDiNascita();
    	boolean state=false;
    	try 
		{
			if (ConnToDb.connection())
			{
				 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query= "INSERT INTO `ispw`.`users`"
			 			+ "(`idRuolo`,"
			 			+ "`Nome`,"
			 			+ "`Cognome`,"
			 			+ "`Email`,"
			 			+ "`pwd`,"
			 			+ "`descrizione`,"
			 			+ "`DataDiNascita`)"
			 			+ "VALUES (?,?,?,?,?,?,?)";
			 	prepQ = ConnToDb.conn.prepareStatement(query);	
				prepQ.setString(1,Ut.getIdRuolo());
			 	prepQ.setString(2,Ut.getNome()); // titolo
				prepQ.setString(3,Ut.getCognome()); //
				prepQ.setString(4,Ut.getEmail());
		 		prepQ.setString(5, Ut.getPassword());
		 		prepQ.setString(6, Ut.getDescrizione());
		 		// alternativa NO se rompe tutto se passi un oggetto di tipo data java lui
		 		// vuole un oggetto di tipo data sql 
				prepQ.setDate(7, java.sql.Date.valueOf(d)); 
				//prepQ.setString(7,U.getInstance())
				prepQ.executeUpdate();
				//conn.close();
			 	System.out.println("utente Inserito con successo");
			 	state= true; // true		 			 	
			}
			else {
		    	System.err.print("Errore inserimento utenete");
		    	state= false ;
		    	}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
		//conn.close();				 	
		return state;
    	
    }

    //check User email if we found that we return true else we return false
    //Qui viene passato dal controller un oggetto di tipo user
    public static int checkUser(User U) throws SQLException
    {
    	// ritorna int per motivi legati al controller
    	// anche se lo tratto come boolean
    	String email = U.getEmail();
    	
    	System.out.println("\n\n\n\tEmail in check user :"+email);
    	int id;

    	try 
		{
			if (ConnToDb.connection())
			{
				 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="SELECT idUser FROM ispw.users where Email = '"+email+"' ;";
			 	rs = st.executeQuery(query);
			 	if(rs.next())
			 	{
			 		id=rs.getInt(1);
				 	//conn.close();	
			 		
			 		if(id>0)
			 		{
			 			System.out.println("utente già registarto");
			 			return 1;
			 	}
			 	else
			 	{
				 	//conn.close();				 	
			 		return 0; // false
			 		// new account
			 	}

			}}}
		
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
    	finally {
    		conn.close();
    	}
    	return -1 ;
    }
     
    //Questo check
    public static int checkTempUser(TempUser Ut) throws SQLException
    {
    	// ritorna int per motivi legati al controller
    	// anche se lo tratto come boolean
    	String email = TempUser.getInstance().getEmail();
    	try 
		{
			if (ConnToDb.connection())
			{
				Connection conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="SELECT idUser FROM ispw.users where Email = '"+email+"' ;";
			 	rs = st.executeQuery(query);
			 	if(rs.next())
			 	{
				 	conn.close();				 	
			 		System.out.println("utente già registarto");
			 		return 1; // true
			 		// account al ready exists
			 	}
			 	else
			 	{
				 	conn.close();				 	
			 		return 0; // false
			 		// new account
			 	}

			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
    	return -1 ;
    }
     
    public static String getRuolo (User U)
    {
    	//String r = null ;

    	String email = U.getEmail();
    	System.out.println("Email:"+email);
    	try 
		{
			if (ConnToDb.connection())
			{
				 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="SELECT idRuolo FROM ispw.users where Email = '"+email+"' ;";
			 	rs = st.executeQuery(query);
			 	if(rs.next())
			 	{
			 		r =rs.getString(1);
			 		User.getInstance().setIdRuolo(r);
				 	conn.close();				 	
			 		System.out.println("Ruolo utente : "+r);
			 		//return r; // true
			 	}
			 	else
			 	{
				 	//conn.close();				 	
			 		return null; // Errore
			 	}

			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
    	finally {
    		try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
    	}

    	return r;
    	
    }
    
    // this function check if you have changed password successfully 
    public static boolean checkResetpass (User U, String pwd,String email )
    {
    	//String email = U.getEmail();
    	System.out.println("Email : "+email);
    	try 
		{
			if (ConnToDb.connection())
			{
				Connection conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="Update users SET pwd = '"+pwd+"' where Email = '"+email+"'";
			 	st.executeUpdate(query);
			 	conn.close();
			 	return true;
			 	
			 	

			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
    	return false ;
    }
    
    //
    public static Object findUser(TempUser U)
    {
    	String r = TempUser.getInstance().getIdRuolo();
    	if(r.contentEquals("U"))
    	{
    		return  U;
    	}
    	else if(r.contentEquals("A"))
    	{
    		Admin A = new Admin(U);
    		System.out.println(A);
    		return A;
    	}
    	else if(r.contentEquals("E"))
    	{
    		Editore E = new Editore(U);
    		System.out.println(E);

    		return E;
   		
    	}
    	else if(r.contentEquals("W"))
    	{
    		Scrittore W = new Scrittore(U);
    		System.out.println(W);
    		return W;
    	}
	return null;

    }
    
    // delete a user from db  terzo caso d'uso
   
    public static boolean deleteUser(User user)
    {
    	String Email = User.getInstance().getEmail();
    	String ruolo=User.getInstance().getIdRuolo();
    	try 
		{
			if (ConnToDb.connection()  && ruolo.equals("U"))
			{
	    		 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="DELETE FROM users WHERE "+
				"Email = '"+ Email +"'";
			 	st.executeUpdate(query);
			 	return true;
			 	
			}
			else if (ConnToDb.connection()  && ruolo.equals("a"))
				{
		    		 conn = ConnToDb.generalConnection();
					st=conn.createStatement();
					query="USE ispw";
					st.executeQuery(query);
				 	query="DELETE FROM users WHERE "+
					"idUser = '"+User.getInstance().getIdU() +"'";
				 	st.executeUpdate(query);
				 	return true;
				 	
				}
				
				
			
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	finally {
    		try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
    	}
    	return false ;
    }

    public static boolean deleteTempUser(TempUser Ut)
    {
    	String Email = TempUser.getInstance().getEmail();
    	try 
		{
			if (ConnToDb.connection())
			{
	    		Connection conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="DELETE FROM users WHERE "+
				"Email = '"+ Email +"'";
			 	st.executeUpdate(query);
			 	conn.close();
			 	return true;
			 	
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	return false ;
    }

    // Con pickData prendo i dati dall'utente creato per il login
    // per poi restituirlo in un nuovo oggetto di tipo User
    // e poi il controller lo specializza nelle 4 classi 
    public static User pickData(User U)
    {
    	String email = U.getEmail();
    	try 
		{
			if (ConnToDb.connection())
			{
				 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query="SELECT `idRuolo`,"
			 			+ "    `Nome`,"
			 			+ "    `Cognome`,"
			 			+ "    `Email`,"
			 			+ "    `descrizione`,"
			 			+ "    `DataDiNascita` "
			 			+ "		FROM users where Email = '"+email+"' ;";
			 	rs = st.executeQuery(query);
			 	while(rs.next())
			 	{
			 		// setto i vari dati 
			 		U.setIdRuolo(rs.getString(1));
			 		U.setNome(rs.getString(2));
			 		U.setCognome(rs.getString(3));
			 		U.setEmail(rs.getString(4));
			 		U.setDescrizione(rs.getString(5));
			 		U.setDataDiNascita(rs.getDate(6).toLocalDate());
			 				 		
			 	//	sono delle print messe per controllo 
			 	//	System.out.println("U: "+U+"\n Con i campi :\n Cognome "
			 	//			+ U.getCognome() + "\n email " + U .getEmail() + "\n ");
			 		return U ;
			 		 // true
			 		// account already exists
			 	}
			 	
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	finally{
    		try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
    	}
    	// errore
    	return U;
    }
         
    public static User aggiornaNome(User U)
    {
        String email = User.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set Nome=? where Email='"+email+"'";
    		 	

    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,User.getInstance().getNome() );
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }
    
    public static User aggiornaCognome(User U)
    {
        String email = User.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set Cognome=? where Email='"+email+"'";
    		 	

    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,User.getInstance().getCognome() );
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }
    
    public static User aggiornaEmail(User U,String m)
    {
        String email = User.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set Email=? where Email='"+email+"'";
    		 	
    		 	User.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,User.getInstance().getEmail() );
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }
/*
    public static User aggiornaUtente(User U, String emailN)
    {
    
    String email = User.getInstance().getEmail();
   
	try 
	{
		
		if (ConnToDb.connection())
		{
			
			Connection conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			query="USE ispw";
			st.executeQuery(query);
		 	query="UPDATE users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=?"
		 			+ "where Email='"+email+"'";
		 	
			User.getInstance().setEmail(emailN);

		 	
			prepQ = conn.prepareStatement(query);

		 			// rs = st.executeQuery(query);
		 		// setto i vari dati 
		 	prepQ.setString(1,User.getInstance().getIdRuolo());
		 	prepQ.setString(2,User.getInstance().getNome() );
		 	prepQ.setString(3, User.getInstance().getCognome());
		 	prepQ.setString(4, User.getInstance().getEmail());
		 	prepQ.setString(5, User.getInstance().getPassword());
		 	prepQ.setString(6, User.getInstance().getDescrizione());
		 	prepQ.setString(7, User.getInstance().getDataDiNascita().toString());




			prepQ.executeUpdate();

		 		
		 				 		
		 		conn.close();	
		 	//	sono delle print messe per controllo 
		 	//	System.out.println("U: "+U+"\n Con i campi :\n Cognome "
		 	//	 ;
		 		 // true
		 		// account already exists
		 	}
		 
	}
	catch (SQLException e1) {
		e1.printStackTrace();
		}
	// errore
	return null;
}
*/
	public User aggiornaPass(User U) {
		
		String email = User.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set pwd=? where Email='"+email+"'";
    		 	
    		 	//U.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,User.getInstance().getPassword());
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }

	public User aggiornaDesc(User U) {
		String email = User.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set descrizione=? where Email='"+email+"'";
    		 	
    		 	//U.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,User.getInstance().getDescrizione());
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }

	public User aggiornaData(User U) {
		String email = User.getInstance().getEmail();
		LocalDate data=U.getDataDiNascita();

        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set DataDiNascita=? where Email='"+email+"'";
    		 	
    		 	//U.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,data.toString());
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }

	// Per il terzo caso d'uso creo e uso sempre il temp user per appoggiarmi all'utente che modifico  e quindi 
	
    public static TempUser aggiornaTempNome(TempUser U)
    {
        String email = TempUser.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set Nome=? where Email='"+email+"'";
    		 	

    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,TempUser.getInstance().getNome() );
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return Ut;
    }
    
    public static TempUser aggiornaCognome(TempUser U)
    {
        String email = TempUser.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set Cognome=? where Email='"+email+"'";
    		 	

    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,TempUser.getInstance().getCognome() );
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }
    
    public static TempUser aggiornaEmail(TempUser U,String m)
    {
        String email = TempUser.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set Email=? where Email='"+email+"'";
    		 	
    		 	TempUser.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,TempUser.getInstance().getEmail() );
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }
    
    public static TempUser aggiornaTempUtente(TempUser U, String emailN)
    {
    String email = TempUser.getInstance().getEmail();
   
	try 
	{
		
		if (ConnToDb.connection())
		{
			
			Connection conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			query="USE ispw";
			st.executeQuery(query);
		 	query="UPDATE users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=?"
		 			+ "where Email='"+email+"'";
		 	
			TempUser.getInstance().setEmail(emailN);

		 	
			prepQ = conn.prepareStatement(query);

		 			// rs = st.executeQuery(query);
		 		// setto i vari dati 
		 	prepQ.setString(1,TempUser.getInstance().getIdRuolo());
		 	prepQ.setString(2,TempUser.getInstance().getNome() );
		 	prepQ.setString(3, TempUser.getInstance().getCognome());
		 	prepQ.setString(4, TempUser.getInstance().getEmail());
		 	prepQ.setString(5, TempUser.getInstance().getPassword());
		 	prepQ.setString(6, TempUser.getInstance().getDescrizione());
		 	prepQ.setString(7, TempUser.getInstance().getDataDiNascita().toString());




			prepQ.executeUpdate();

		 		
		 				 		
		 		conn.close();	
		 	//	sono delle print messe per controllo 
		 	//	System.out.println("U: "+U+"\n Con i campi :\n Cognome "
		 	//	 ;
		 		 // true
		 		// account already exists
		 	}
		 
	}
	catch (SQLException e1) {
		e1.printStackTrace();
		}
	// errore
	return TempUser.getInstance();
}
   
	public TempUser aggiornaTempPass(TempUser U) {
		
		String email = TempUser.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			Connection conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set pwd=? where Email='"+email+"'";
    		 	
    		 	//U.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,TempUser.getInstance().getPassword());
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return U;
    }

	public TempUser aggiornaTempDesc(TempUser U) {
		String email = TempUser.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			 conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set descrizione=? where Email='"+email+"'";
    		 	
    		 	//U.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,TempUser.getInstance().getDescrizione());
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
        finally {
        	try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
        }
    	// errore
    	return U;
    }

	public TempUser aggiornaTempData(TempUser U) {
		String email = TempUser.getInstance().getEmail();
        
        try 
    	{
    		
    		if (ConnToDb.connection())
    		{
    			
    			 conn = ConnToDb.generalConnection();
    			st=conn.createStatement();
    			query="USE ispw";
    			st.executeQuery(query);
    		 	query="UPDATE users set DataDiNascita=? where Email='"+email+"'";
    		 	
    		 	//U.getInstance().setEmail(m);
    		 	
    			prepQ = conn.prepareStatement(query);

       		 	prepQ.setString(1,TempUser.getInstance().getDataDiNascita().toString());
       			prepQ.executeUpdate();  		 		
    		 				 		
    		 		conn.close();	
    		 	}
    		 
    	}
    	catch (SQLException e1) {
    		e1.printStackTrace();
    		}
    	// errore
    	return null;
    }
	
/*	
	 public static String getRuoloTemp (User U)
	    {

	    	String email =User.getInstance().getEmail();
	    	
	    	System.out.println("Emal dell caz :"+email);
	    	try 
			{
				if (ConnToDb.connection())
				{
					conn = ConnToDb.generalConnection();
					st=conn.createStatement();
					query="USE ispw";
					st.executeQuery(query);
				 	query="SELECT idRuolo FROM ispw.users where Email = '"+email+"' ;";
				 	rs = st.executeQuery(query);
				 	if(rs.next())
				 	{
				 		r =rs.getString(1);
				 		TempUser.getInstance().setIdRuolo(r);
				 		System.out.println("Ruolo utente in dao : "+r);
				 	}
				 	
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
				}
	    	// errore

	    	finally {
	    		try {
					conn.close();
				} catch (SQLException e) {
				 
					
				}
	    	}
	    	return r;
	    	
	    }
*/
	public void getListaUtenti()  {
		
		conn= ConnToDb.generalConnection();
            ResultSet rs;
			try {
				
					rs = conn.createStatement().executeQuery("SELECT * FROM users");
				
            FileWriter w;
            w=new FileWriter("ReportFinale\\riepilogoUtenti.txt");

            BufferedWriter b;
            b=new BufferedWriter (w);

            
           while(rs.next())
            {
        			 
        		TempUser.getInstance().setIdU(rs.getInt(1));
        		TempUser.getInstance().setIdRuolo(rs.getString(2));
       			TempUser.getInstance().setNome(rs.getString(3));
       			TempUser.getInstance().setCognome(rs.getString(4));
       			TempUser.getInstance().setEmail(rs.getString(5));
       			TempUser.getInstance().setDescrizione(rs.getString(7));
       			TempUser.getInstance().setDataDiNascita(rs.getDate(8).toLocalDate());
       			
       			
       			
       			b.write(""+TempUser.getInstance().getIdU()+"\t"+TempUser.getInstance().getIdRuolo()+"\t"+TempUser.getInstance().getNome()+"\t"+TempUser.getInstance().getCognome()+
       					"\t"+TempUser.getInstance().getEmail()+"\t"+TempUser.getInstance().getDescrizione()+"\t"+TempUser.getInstance().getDataDiNascita().toString()+"\n");
       			//b.write("\t");




       			b.flush();
       			b.close();


        			
        		
            }
            }catch(IOException e)
        		{
					e.getMessage();
        		} catch (SQLException e) {
			 
				
			}
            
           finally {

        	   try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}           
           }
		}
            
	
	
	public  TempUser getTempUserSingolo(TempUser uT) throws SQLException
	{
		int id=TempUser.getInstance().getIdU();
		
		System.out.println("Id passato nel dao di tempUser :"+TempUser.getInstance().getIdU());
		System.out.println("Id passato nel dao di singletonBattona :"+SingeltonSystemState.getIstance().getId());
		
		
		
		 conn = ConnToDb.generalConnection();
		st=conn.createStatement();
		query="USE ispw";
		st.executeQuery(query);
		query="SELECT * FROM ispw.users where idUser = "+id+" ;";

	 	rs = st.executeQuery(query);
	 	

		 

            while(rs.next())
            {
            	
            	Ut.setIdRuolo(rs.getString(2));
            	Ut.setNome(rs.getString(3));
            	Ut.setCognome(rs.getString(4));
            	Ut.setEmail(rs.getString(5));
            	Ut.setPassword(rs.getString(6));
            	Ut.setDescrizione(rs.getString(7));
            	Ut.setDataDiNascita(rs.getDate(8).toLocalDate());
            	
            	System.out.println("Nel while del dao :"+rs.getString(2));//u.getInstance().getIdU());

            }
            
		conn.close();
		return uT;
	}
	
	public static User aggiornaUtente(User U)
    {
    
		LocalDate d=U.getDataDiNascita();

	try 
	{
		
		if (ConnToDb.connection())
		{
			
			Connection conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			query="USE ispw";
			st.executeQuery(query);
		 	query="UPDATE users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=?"
		 			+ "where idUser="+User.getInstance().getIdU()+"";
		 	
			//U.getInstance().setEmail(emailN);

		 	
			prepQ = conn.prepareStatement(query);

		 			// rs = st.executeQuery(query);
		 		// setto i vari dati 
		 	prepQ.setString(1,User.getInstance().getIdRuolo());
		 	prepQ.setString(2,User.getInstance().getNome() );
		 	prepQ.setString(3, User.getInstance().getCognome());
		 	prepQ.setString(4, User.getInstance().getEmail());
		 	prepQ.setString(5, User.getInstance().getPassword());
		 	prepQ.setString(6, User.getInstance().getDescrizione());
		 	prepQ.setString(7,d.toString());




			prepQ.executeUpdate();

		 		
		 				 		
		 		conn.close();	
		 	//	sono delle print messe per controllo 
		 	//	System.out.println("U: "+U+"\n Con i campi :\n Cognome "
		 	//	 ;
		 		 // true
		 		// account already exists
		 	}
		 
	}
	catch (SQLException e1) {
		e1.printStackTrace();
		}
	// errore
	return U;
}
/*
	public boolean createTempUser(TempUser U) throws SQLException
    {
    	boolean state=false;
    	try 
		{
			if (ConnToDb.connection())
			{
				 conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query= "INSERT INTO `ispw`.`users`"
			 			+ "(`Nome`,"
			 			+ "`Cognome`,"
			 			+ "`Email`,"
			 			+ "`pwd`,"
			 			+ "`DataDiNascita`)"
			 			+ "VALUES"
			 			+" "
			 			+ "(?,?,?,?,?)";
				prepQ = ConnToDb.conn.prepareStatement(query);	
				prepQ.setString(1,TempUser.getInstance().getNome()); // titolo
				prepQ.setString(2,TempUser.getInstance().getCognome()); //
				prepQ.setString(3,TempUser.getInstance().getEmail());
		 		prepQ.setString(4, TempUser.getInstance().getPassword());
		 		prepQ.setDate(5, java.sql.Date.valueOf(TempUser.getInstance().getDataDiNascita().toString()));  
				prepQ.executeUpdate();
				System.out.println("utente Inserito da admin con successo");
			 	state= true; // true		 			 	
			}
			else {
		    	System.err.print("Errore inserimento utenete");
		    	state= false ;
		    	}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}
    	// errore
		//conn.close();				 	
		return state;
    	
    }
    */

	public int maxIdUSer() throws SQLException
	{
			 conn=ConnToDb.generalConnection();
			 st=conn.createStatement();
			 st.execute("select max(idUser) from ispw.users");
	           rs=st.getResultSet();//executeQuery("");
	          if (rs.next())
	          {
	        	  max=rs.getInt(1);
	          }
		System.out.println("Max in dao "+max);
		return max;
}
	
	public static TempUser aggiornaUtenteTemp(TempUser TU) throws NullPointerException
    {
    
	try 
	{
		
		if (ConnToDb.connection())
		{
			
			 conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			query="USE ispw";
			st.executeQuery(query);
		 	query="UPDATE users set idRuolo=?,Nome=?,Cognome=?,Email=?,pwd=?,descrizione=?,DataDiNascita=?"
		 			+ "where idUser="+TempUser.getInstance().getIdU()+"";
		 	
			//U.getInstance().setEmail(emailN);

		 	
			prepQ = conn.prepareStatement(query);

		 			// rs = st.executeQuery(query);
		 		// setto i vari dati 
		 	prepQ.setString(1,TU.getIdRuolo());
		 	prepQ.setString(2,TU.getNome() );
		 	prepQ.setString(3, TU.getCognome());
		 	prepQ.setString(4, TU.getEmail());
		 	prepQ.setString(5, TU.getPassword());
		 	prepQ.setString(6, TU.getDescrizione());
		 	prepQ.setString(7, TU.getDataDiNascita().toString());




			prepQ.executeUpdate();

		 		
		 				 		
		 		conn.close();	
		 	//	sono delle print messe per controllo 
		 	//	System.out.println("U: "+U+"\n Con i campi :\n Cognome "
		 	//	 ;
		 		 // true
		 		// account already exists
		 	}
		 
	}
	catch (SQLException e1) {
		e1.printStackTrace();
		}
	// errore
	return TU;
}



}
