package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.UsersDao;

public class ControllerUserPage {
	private UsersDao ud;
	
	public void getUtenti()  {
		 ud.getListaUtenti();
	}
	
	public ControllerUserPage()
	{
		ud=new UsersDao();
	}
	

}
