package controller;

import database.UsersDao;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import entity.users.singelton.User;

public class ControllerVisualizzaProfilo {
	private UsersDao ud;
	private boolean status=false;

	public User getCredenziali() {
		
		return UsersDao.pickData(User.getInstance());
		
	}
	
	public ControllerVisualizzaProfilo()
	{
		ud=new UsersDao();
	}

	public boolean cancellaUtente() {
		if(UsersDao.deleteUser(User.getInstance())==true )
		{
			User.getInstance().setNull();
			status=true;
			//System.out.println("USer @"+u.getInstance());
		}
		return status;

		}
}
		
		
		
		
	


