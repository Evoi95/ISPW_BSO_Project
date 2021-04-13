package controller;

import java.sql.SQLException;

import database.*;
import entity.factorybook.*;
import javafx.collections.ObservableList;

public class ControllerRicercaPage {
	
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	public ControllerRicercaPage()
	{
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD =new RivistaDao();
		//singeltonSystemState.getIstance();
		SingeltonSystemState.getIstance().setIsSearch(true);
		System.out.println("vis nel costruttore"+SingeltonSystemState.getIstance());
		
	}
	
	public ObservableList<Raccolta> cercaPerTipo (String S) throws SQLException
	{
		//3 if per i tipi 
		System.out.println("Tipo e confronto :"+SingeltonSystemState.getIstance().getType().equals("libro"));
		if(SingeltonSystemState.getIstance().getType().equals("libro"))
		{
			//serach in libro dao
			return lD.getLibriByName(S);
		}
		else if(SingeltonSystemState.getIstance().getType().equals("giornale"))
		{
			//search in giornale dao
			return gD.getGiornaliByName(S);
		}
		else if(SingeltonSystemState.getIstance().getType().equals("rivista"))
		{
			//search in rivista dao
			return rD.getRivisteByName(S);
		}
		
		return null;
		
	}
	
	public String returnType()
	{
		return SingeltonSystemState.getIstance().getType();
	}
	
	
}
