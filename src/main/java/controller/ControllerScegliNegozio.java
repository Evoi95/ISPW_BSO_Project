package controller;

import java.sql.SQLException;
import database.NegozioDao;
import javafx.collections.ObservableList;

import entity.Negozio;

public class ControllerScegliNegozio {
	
	private NegozioDao nD;
	private Negozio N;
	private ObservableList<Negozio> listOfNegozi;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	
	public ControllerScegliNegozio()
	{
		nD = new NegozioDao();
		N = new Negozio();
	}
	
	public ObservableList<Negozio> getNegozi() throws SQLException
	{
		listOfNegozi = nD.getNegozi();
		return listOfNegozi;
	}
	
	public boolean isLogged()
	{
		return vis.getIsLogged();
	}
}