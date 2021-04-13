package controller;

import java.time.LocalDate;

import database.UsersDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import entity.users.singelton.User;

public class ControllerModificaUtente {
	private UsersDao ud;
	private boolean state = false;

	
	
	public ControllerModificaUtente()
	{
		ud=new UsersDao();
	}



	public User prendi() {
		
		return ud.pickData(User.getInstance());
		
		
	}

	public boolean aggiorna(String n, String c, String email, String pass, String desc, LocalDate localDate, String vecchiaMail) {
		
		
		try {
		if( !n.equals("") && !n.equals(null) && !n.equals(User.getInstance().getNome()))
		{
			
			User.getInstance().setNome(n);
			ud.aggiornaNome(User.getInstance());
			state =  true; 

		}
		if (!c.equals("") && !c.equals(null) && !c.equals(User.getInstance().getCognome()))
		{
			User.getInstance().setCognome(c);
			ud.aggiornaCognome(User.getInstance());
			state =  true; 

		}
		if(!email.equals("") && !email.equals(null) && !email.equals(vecchiaMail))
		{
			
			ud.aggiornaEmail(User.getInstance(),email);
			state =  true; 

	
		}
		if(!pass.equals("") && !pass.equals(null) && !pass.equals(User.getInstance().getPassword()))
		{
			User.getInstance().setPassword(pass);
			ud.aggiornaPass(User.getInstance());
			state =  true; 

		}
		if(!desc.equals("") && !desc.equals(null) && !desc.equals(User.getInstance().getDescrizione()))
		{
			User.getInstance().setDescrizione(desc);
			ud.aggiornaDesc(User.getInstance());
			state =  true; 
		}

		if(!localDate.equals("") && !localDate.equals(null) && !localDate.equals(User.getInstance().getDataDiNascita()))
		{
			User.getInstance().setDataDiNascita(localDate);
			ud.aggiornaData(User.getInstance());
			state = true;
			
		}
		//qui controllo se i campi sono vuoti 
		else { 
			return state;
			//throw new NullPointerException();
		}
			
		
		}
		catch(NullPointerException e)
		{
			//
			//System.err.println("Something went wrong.");
			System.out.println("Ho torvato LocalDate come : "+localDate);
			// commento di test per vedere quale dato si rompe
			// test di modifica utente
			//System.out.println(localDate+"\n"+User.getInstance().getDataDiNascita());
					/*User.getInstance().getIdRuolo() +"\n"+ User.getInstance().getNome()+"\n"+User.getInstance().getCognome()+"\n"+
					User.getInstance().getEmail()+"\n"+User.getInstance().getPassword()+"\n"+
					User.getInstance().getDescrizione()+"\n"+User.getInstance().getDataDiNascita());*/
			

		}
		return state;
			
	}
}
