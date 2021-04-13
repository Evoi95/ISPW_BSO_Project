package controller;

import java.sql.SQLException;

import database.UsersDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import entity.users.singelton.User;

public class ControllerPassword {
	private User U = User.getInstance();
	private UsersDao Ud;
	private boolean status;
	public ControllerPassword()
	{
		Ud = new UsersDao();
		
	}

	public boolean aggiornaPass(String email,String vecchiaP,String nuovaP) throws SQLException
	{
		
		if(nuovaP.length()>=8 || !email.equals(""))
		{
			U.setEmail(email);
			U.setPassword(nuovaP);
			if(UsersDao.checkUser(U) == 1)
			{
				UsersDao.checkResetpass(U, nuovaP,email);
				status=true;
			}
			else if  (UsersDao.checkUser(U) == 0 || UsersDao.checkUser(U) == -1 )
			{
				status=false;
			}
			
		}
		else {
			status=false;
		}
		return status;
	}
}
