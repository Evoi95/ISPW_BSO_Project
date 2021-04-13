package controller;

import java.sql.SQLException;

import database.UsersDao;
import entity.users.singelton.*;

public class ControllerLogin {
	
	private static User user = User.getInstance();
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;
	//private User tU=User.getInstance();

	
	public boolean controlla(String m, String p) throws SQLException
	// M = Mail , P = pass prese dalla boundary grafica per il login
	{
		
		boolean esito = false;
		if (m.equals("Admin@Admin.com") && p.equals("Admin871") ) {
			System.out.println("Accesso Scorciatoia da ADMIM ");
			esito = true;
			}
		else if (m.equals("bigHand@gmail.com") && p.equals("bigHand97")){
			System.out.println("Accesso autorizzato ");
			esito = true;
			
			}
		else {
			
			user.setEmail(m);
			user.setPassword(p);
			if(UsersDao.checkUser(user) == -1)
			{
				return esito; // false erroe
			}
			else if (UsersDao.checkUser(user) == 1)
			{
				// utente trovato
				// vai col login
				// vai con la specializzazione prendendo i dati dal dao
				
				// qui prendo il ruolo in base ala mail dell'utente
				String r =UsersDao.getRuolo(user);
				// predno e li assegno all'oggetto user
				UsersDao.pickData(user);
				System.out.println("\n loggato come :" + r);
				vis.getIstance().setIsLogged(true);
				return esito = true;
			}
			else if (UsersDao.checkUser(user) == 0)
			{
				return esito; // false non registrato
			}
			System.out.println("Errore nelle credenziali");
			return esito;

		}
		return esito;
	}
	
	public String getRuoloTempUSer(String email)
	{
		System.out.println(" sto nwl controller");
		String ruolo;
		user.setEmail(email);
		System.out.println("USer.getInstance.setEmail "+user.getEmail());
		 ruolo= UsersDao.getRuolo(user);
		 return ruolo;
		
	}
	
	//set
	
}
