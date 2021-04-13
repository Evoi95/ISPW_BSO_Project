package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Negozio;

public class NegozioDao {
	
	private Negozio N; 
	private String nome;
	private String via; //primary key
	private Boolean isValid;
	private Boolean isOpen;
	private ObservableList<Negozio> listOfNegozi;
	private static Statement st = null ;
	private static String query ;
	private static ResultSet rs;
	private static PreparedStatement prepQ = null;
    private Connection  conn;
    
	public ObservableList<Negozio> getNegozi() throws SQLException
	{
		conn= ConnToDb.generalConnection();
	
		listOfNegozi=FXCollections.observableArrayList();
		 
        rs=conn.createStatement().executeQuery("SELECT "
        		+ "    `negozio`.`nome`,"
        		+ "    `negozio`.`via`,"
        		+ "    `negozio`.`isValid`,"
        		+ "    `negozio`.`isOpen`"
        		+ "FROM `ispw`.`negozio`");
        
        while(rs.next())
        {
        	try 
        	{
        		N = new Negozio(rs.getString(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4));
				listOfNegozi.add(N);
    		}
        	catch (Exception e) 
        	{
			 
				
			}
        }
		return listOfNegozi;
	}
	
//	public Integer setOpen(Negozio N, boolean i)
//	{
//		// vanno messe  le query
//		conn= ConnToDb.generalConnection();
//		
//		if (i == true)
//		{
//			N.setIsOpen(i);
//			return 1;
//		}
//		else if (i == false)
//		{
//			N.setIsOpen(i);
//			return 0;
//		}
//		return -1;
//		
//	}
//	
//	public Integer setRitiro(Negozio N, boolean i )
//	{
//		if (i == true)
//		{
//			N.setIsValid(i);
//			return 1;
//		}
//		else if (i == false)
//		{
//			N.setIsValid(i);
//			return 0;
//		}
//		return -1;
//	}
	
	// controllo che il negozio sia aperto
	public boolean checkOpen(Negozio  N)
	{
		conn= ConnToDb.generalConnection();
		return false;
	}
	
	//controllo se il negozio fa PickUP
	public boolean checkRitiro(Negozio N)
	{
		return false;
	}

}
