package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import database.UsersDao;
import entity.users.singelton.TempUser;

public class ControllerAddUserPage {
	private UsersDao ud;
	private static TempUser u=TempUser.getInstance();
	
	public ControllerAddUserPage()
	{
		ud=new UsersDao();
	}

	public void insUtenteAsAdmin(String nome, String cognome, String email, String pwd, String desc, LocalDate data, String ruolo) throws SQLException
	{
		//u.setNull();
		u.setNome(nome);
		u.setCognome(cognome);
		u.setEmail(email);
		u.setPassword(pwd);
		u.setDescrizione(desc);
		u.setDataDiNascita(data);
		u.setIdRuolo(ruolo);
		ud.createUser2(u);
	}
}
