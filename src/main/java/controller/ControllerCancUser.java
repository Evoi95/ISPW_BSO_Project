package controller;

import database.UsersDao;
import entity.users.singelton.TempUser;

public class ControllerCancUser {
	private static TempUser u=TempUser.getInstance();
	

	public void cancellaUtente(int id)
	{
		u.setIdU(id);
		UsersDao.deleteTempUser(u);
	}
	
	public ControllerCancUser()
	{
		
	}
}
