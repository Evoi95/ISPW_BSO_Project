package controller;

import java.sql.SQLException;

import database.GiornaleDao;
import entity.factorybook.Giornale;
import javafx.collections.ObservableList;

public class ControllerGiornalePage {

	private GiornaleDao gD;
	
	public ObservableList<Giornale> getGiornaliS() throws SQLException {
		return gD.getLibriSingolo();
	}
	
	
	public ControllerGiornalePage() {
		gD=new GiornaleDao();
	}
}
