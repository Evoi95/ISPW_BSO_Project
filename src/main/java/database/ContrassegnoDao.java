package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Fattura;

public class ContrassegnoDao {
	private Connection conn;
	private static PreparedStatement stmt;
	
	//private boolean esito=false;
	
	public void inserisciFattura(Fattura f) 
	{
		 conn=null;
		 stmt=null;
		
		String par1=f.getNome();
 		String par2=f.getCognome();
 		String par3=f.getVia();
 		String par4=f.getCom();
 		float par5=f.getAmmontare();
 		
 		System.out.println(par1 + par2 + par3 + par4 + par5);
       
		 try {

			 conn = ConnToDb.generalConnection();
         
             stmt = conn.prepareStatement("insert into fattura   values (?,?,?,?,?,?);");
             stmt.setString(1,par1);
             stmt.setString(2, par2);
             stmt.setString(3,par3);
             stmt.setString(4,par4 );
             stmt.setInt(5, 0);
             stmt.setFloat(6, par5);
             stmt.executeUpdate();
             

     		
             
            
         }catch(SQLException e)
         {
        	e.getMessage();

         }
		 		 
		 System.out.println("effettuo inserimento pagaentoDao");
				//return esito;
         
         
        	 
	}  
	public void daiPrivilegi() 
	{
		conn=null;
		 stmt=null;
	//	Double d=(double) disp;

		 try {
			  conn = ConnToDb.generalConnection();
			  stmt = conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			         stmt.executeUpdate();

	            
	         }catch(SQLException e)
	         {
	        	// esito=false;
	        	e.getMessage();

	         }	
		 finally {
			// stmt.close();
			 try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
			 System.out.println("Ho chiuso tutto");
			 
		 }

		 System.out.println("PagamentoDao. privilegi");

		}
	
	public float prendiSpesa() 
	{
		float spesa=0;
		Connection conn = null;
		try {
			 conn=ConnToDb.generalConnection();
	          ResultSet rs=conn.createStatement().executeQuery("select spesaTotale from pagamento  where 1+last_insert_id(id_op) order by id_op desc limit 1");
	          while (rs.next())
	          {
	        	  spesa=rs.getFloat("spesaTotale");
	          }
		}catch(SQLException e)
		{
			e.getCause();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}
		
		System.out.println("\n\n Spesa in cDao :"+spesa);
		return spesa;
	}
	
	
	
	
	
	
}
         


