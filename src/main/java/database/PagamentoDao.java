package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Pagamento;
import entity.users.singelton.User;

public class PagamentoDao {
	private static String qInsert ;
	private static PreparedStatement prepQ = null;
	private Connection connPag;
    

	public void inserisciPagamento(Pagamento p) throws SQLException {
		
		String m=p.getMetodo();
		int esito=p.getEsito();
		String nomeU=p.getNomeUtente();
		float amm=p.getAmmontare();
		String email=User.getInstance().getEmail();
		String tipologia=p.getTipo();
		int idProdotto=p.getId();
		
		try {
					connPag=ConnToDb.generalConnection();

		
		 qInsert="INSERT INTO pagamento (metodo,esito,nomeUtente,spesaTotale,eMail,tipoAcquisto,id_prod) values (?,?,?,?,?,?,?)";
		prepQ = connPag.prepareStatement(qInsert);
		//prepQ.setInt(1,p.getEsito()); // numero pagine int
		prepQ.setString(1,m); // 
		prepQ.setInt(2,esito);
		prepQ.setString(3, nomeU);
		prepQ.setFloat(4,amm);
		prepQ.setString(5, email);
		prepQ.setString(6,tipologia);
		prepQ.setInt(7, idProdotto);
		prepQ.executeUpdate();
		
		connPag.close();
		}catch(SQLException s)
		{
			s.getStackTrace();
		}
		}
	public void daiPrivilegi() 
	{
		 connPag=null;
		 prepQ=null;
	//	Double d=(double) disp;

		 try {
			  connPag = ConnToDb.generalConnection();
			  prepQ = connPag.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			         prepQ.executeUpdate();

	            
	         }catch(SQLException e)
	         {
	        	// esito=false;
	        	e.getMessage();

	         }	
		 finally{
			 try {
				connPag.close();
			} catch (SQLException e) {
			 
				
			}
		 }
		
		 System.out.println("LibroDao. privilegi");

	}
	
public void aggiornaPagamentoCash(Pagamento p) throws SQLException {
		
		String m=p.getMetodo();
		int esito=p.getEsito();
		String nomeU=p.getNomeUtente();
		float amm=p.getAmmontare();
		String email=User.getInstance().getEmail();
		
		try {
					connPag=ConnToDb.generalConnection();

		
		 qInsert="update pagamento set metodo=?,esito=?,nomeUtente=?,spesaTotale=?,eMail=?  where 1+last_insert_id(id_op) order by id_op desc limit 1";
		prepQ = connPag.prepareStatement(qInsert);
		//prepQ.setInt(1,p.getEsito()); // numero pagine int
		prepQ.setString(1,m); // 
		prepQ.setInt(2,esito);
		prepQ.setString(3, nomeU);
		prepQ.setFloat(4,amm);
		prepQ.setString(5, email);
		prepQ.executeUpdate();
		
		connPag.close();
		}catch(SQLException s)
		{
			s.getStackTrace();
		}
		}

public void aggiornaPagamentoCC(Pagamento p) throws SQLException {
	
	String m=p.getMetodo();
	int esito=p.getEsito();
	String nomeU=p.getNomeUtente();
	float amm=p.getAmmontare();
	String email=User.getInstance().getEmail();
	
	try {
				connPag=ConnToDb.generalConnection();

	
	 qInsert="update pagamento set metodo=?,esito=?,nomeUtente=?,spesaTotale=?,eMail=?  where 1+last_insert_id(id_op) order by id_op desc limit 1";
	prepQ = connPag.prepareStatement(qInsert);
	//prepQ.setInt(1,p.getEsito()); // numero pagine int
	prepQ.setString(1,m); // 
	prepQ.setInt(2,esito);
	prepQ.setString(3, nomeU);
	prepQ.setFloat(4,amm);
	prepQ.setString(5, email);
	prepQ.executeUpdate();
	
	connPag.close();
	}catch(SQLException s)
	{
		s.getStackTrace();
	}
	}
public ObservableList<Pagamento> getPagamenti()  {
	
	
		
		Connection conn= ConnToDb.generalConnection();

		ObservableList<Pagamento> catalogo=FXCollections.observableArrayList();
		 
		//ConnToDb.connection();
        ResultSet rs;
		try {
			rs = conn.createStatement().executeQuery("SELECT id_op,metodo,esito,nomeUtente,spesaTotale,tipoAcquisto,id_prod from pagamento where eMail='"+User.getInstance().getEmail()+"'");
		
        while(rs.next())
        {
           // System.out.println("res :"+rs);

    		try {
    			catalogo.add(new Pagamento (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6),rs.getInt(7)));
    		} catch (Exception e) {
			 
				
			}

        }
		} catch (SQLException e1) {
		 
			e1.printStackTrace();
		}
		finally {
        try {
			conn.close();
		} catch (SQLException e) {
		 
			
		}
		}
	
	//catalogo.add(new Libro("pippo","pluto","it","fantasy","8004163529","paperino","avventura",100,11,11,5252020,18,null,true));
	
	System.out.println(catalogo);
	return catalogo;
		}

}





