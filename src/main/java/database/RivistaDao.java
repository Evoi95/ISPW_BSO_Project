package database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.factorybook.Factory;
import entity.factorybook.Giornale;
import entity.factorybook.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import entity.factorybook.Raccolta;
import entity.factorybook.Rivista;

public class RivistaDao {
	private Factory f;
	private String name;
	private static Statement st = null ;
	private static String query ;
	private static String qTrigger ;
	private static PreparedStatement prepQ = null; 
	private static Connection conn ;
	private static int q;
	private static ResultSet rs;
	
	
	
	public void getDesc(Rivista r)
	{
		 try {
	            //String url = "jdbc:msql://200.210.220.1:1114/Demo";
	             conn = ConnToDb.generalConnection();
	             st = conn.createStatement();
	            ResultSet rs;
	 
	            rs = st.executeQuery("select * from rivista where titolo ='"+r.getTitolo()+"'");
	            while ( rs.next() ) {
	                String titolo = rs.getString("titolo");
	                String tipologia=rs.getString("tipologia");
	                String autore=rs.getString("autore");
	                String lingua=rs.getString("lingua");	   
	                String editore=rs.getString("editore");
	                String desc=rs.getString("Descrizione");

	                Date data=rs.getDate("dataPubblicazione");
	               
	                int disp=rs.getInt("disp");
	                float prezzo=rs.getFloat("prezzo");
	                int copieR=rs.getInt("copieRimanenti");
	               // InputStream img=rs.getBinaryStream("img");


	                
	                
	    	        
	            }
	        } catch (Exception e) {
	            System.err.println("Got an exception! ");
	            System.err.println(e.getMessage());
	        }
		 finally {
			 try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		 }
	    }
	
	public float getCosto(Rivista r) throws SQLException
	{
		float prezzo=(float) 0.0;
		  conn = ConnToDb.generalConnection();
         st = conn.createStatement();
         //ResultSet rs;

         rs = st.executeQuery("select * from rivista  where titolo='"+r.getTitolo()+"'");
         while ( rs.next() ) {
              prezzo=rs.getFloat("prezzo");

         }
		return prezzo;
		
	}
	
	public void aggiornaDisponibilita(Rivista r) throws SQLException
	{
		int d=r.getCopieRim();

		 try {
			  conn = ConnToDb.generalConnection();
		      prepQ = conn.prepareStatement("update rivista set copieRimanenti=copieRimanenti-'"+d+"' where titolo='"+r.getTitolo()+"'");
			  prepQ.executeUpdate();

	            
	         }catch(SQLException e)
	         {
	        	// esito=false;
	        	e.getMessage();

	         }	
		 finally {
			 prepQ.close();
			 conn.close();
			 System.out.println("Ho chiuso tutto");
			 
		 }

		 System.out.println("LibroDao. questy");

		}

	public void daiPrivilegi() throws SQLException
	{
	//	Double d=(double) disp;

		 try {
			  conn = ConnToDb.generalConnection();
			  prepQ = conn.prepareStatement(" SET SQL_SAFE_UPDATES=0");
			         prepQ.executeUpdate();

	            
	         }catch(SQLException e)
	         {
	        	// esito=false;
	        	e.getMessage();

	         }	
		 finally {
			 prepQ.close();
			 conn.close();
			 System.out.println("Ho chiuso tutto");
			 
		 }

		 System.out.println("LibroDao. privilegi");

}
	
	public ObservableList<Raccolta> getRiviste() throws SQLException
	{
		 conn= ConnToDb.generalConnection();

		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
			//ConnToDb.connection();
             rs=conn.createStatement().executeQuery("SELECT * FROM rivista");
           // int i=0;
            while(rs.next())
            {
               // System.out.println("res :"+rs);

        		try {
					catalogo.add(f.createRivista("rivista",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7).toLocalDate(), rs.getInt(8),rs.getFloat(9),rs.getInt(10),rs.getInt(11)));
					
					//titolo,tipo,autore,lingua,editore,descrizione,dataPubb,disp,prezzo,copieRim,foto,id//rs=rs.next();
					//System.out.println("res: "+rs[i]);
        		} catch (Exception e) {
				 
					
				}
        		//System.out.println("rivista nome"+rs.getString(1));

            }
		//catalogo.add(new Libro("pippo","pluto","it","fantasy","8004163529","paperino","avventura",100,11,11,5252020,18,null,true));
	
			
		System.out.println(catalogo);
		return catalogo;
		
	}
	
	public ObservableList<Raccolta> getRivisteByName(String S) throws SQLException
	{
		 conn= ConnToDb.generalConnection();

		
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
			//ConnToDb.connection();
             rs=conn.createStatement().executeQuery("SELECT * FROM ispw.rivista where titolo = '"+S+"' OR autore = '"+S+"'");
           // int i=0;
            while(rs.next())
            {
               // System.out.println("res :"+rs);

        		try {
					catalogo.add(f.createRivista("rivista",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7).toLocalDate(), rs.getInt(8),rs.getFloat(9),rs.getInt(10),rs.getInt(11)));
					
					//titolo,tipo,autore,lingua,editore,descrizione,dataPubb,disp,prezzo,copieRim,foto,id//rs=rs.next();
					//System.out.println("res: "+rs[i]);
        		} catch (Exception e) {
				 
					
				}
        		//System.out.println("rivista nome"+rs.getString(1));

            }
		//catalogo.add(new Libro("pippo","pluto","it","fantasy","8004163529","paperino","avventura",100,11,11,5252020,18,null,true));
	
			
		System.out.println(catalogo);
		return catalogo;
		
	}


	public Rivista getRivista(Rivista R,int id) throws SQLException
	{

		 conn= ConnToDb.generalConnection();
         rs=conn.createStatement().executeQuery("SELECT * FROM rivista where id = "+id+" ");
        while (rs.next())
        {
        	R = (Rivista) f.createRivista("rivista", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7).toLocalDate(),rs.getInt(8),rs.getFloat(9),rs.getInt(10),rs.getInt(11)); 
        }
             return R;
	}

	public RivistaDao()
	{
		f=new Factory();
	}
	
	public int retId(Rivista r) throws SQLException {
		int id = 0;
		String titolo=r.getTitolo();
		 Connection conn = ConnToDb.generalConnection();
		 try {
         Statement stmt = conn.createStatement();
         ResultSet rs;

         rs = stmt.executeQuery("select id from rivista where titolo ='"+titolo+"'");
         while ( rs.next() ) {
              id=rs.getInt("id");

         }
		 }catch(SQLException e)
		 {
			 e.getCause();
		 }finally {
			 conn.close();
		 }
		 //return id;
		return id;

		
		
	}

	public String retTip(Rivista r) throws SQLException {
		
		String categoria=null;
		  conn = ConnToDb.generalConnection();
		 try {
          st = conn.createStatement();

         rs = st.executeQuery("select tipologia from rivista where titolo ='"+r.getTitolo()+"'");
         while ( rs.next() ) {
              categoria=rs.getString("tipologia");

         }
		 }catch(SQLException e)
		 {
			 e.getCause();
		 }finally {
				conn.close();
			}
			
		return categoria;

		
	}
	
	public String getNome(Rivista R) throws SQLException
	{
	 conn= ConnToDb.generalConnection();
         rs=conn.createStatement().executeQuery("SELECT titolo FROM rivista where id = "+R.getId()+" ");
        if (rs.next())
        {
        	name = rs.getString(1);
        	return name;
        }
        else {
        	System.out.println("non ho torvato un cazzo e ritorno null");
            return null;

        }	
   }

	public int getDisp(Rivista R) throws SQLException
	{
		int disp;
        //ResultSet rs;
		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				rs=  stmt.executeQuery(
						"SELECT `rivista`.`disp` FROM `ispw`.`rivista` where `id` = `"+R.getId()+"` ;");
				disp = rs.getInt(1);
				if (disp >= 1)
					return disp;
				else if (disp == 0)
					return 0;
			}
		} catch (SQLException e) {
		 
			
		}
		/*if (l.getDisponibilita()>=1)
		{
			return true;
		}*/
		return -1;
	}
	
	public int getQuantita(Rivista R) throws SQLException
	{
        
		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				rs=  stmt.executeQuery(
						"SELECT `rivista`.`copieRimanenti` FROM `ispw`.`rivista` where `id` = "+R.getId()+" ;");
				if (rs.next()) {
					q = rs.getInt(1);
				}			
			}
		} catch (SQLException e) {
		 
			
		}

		return q;
	}

	public boolean checkDisp(Rivista R,int id) throws SQLException
	{
		int disp;
		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				
				rs=  stmt.executeQuery("SELECT disp FROM ispw.rivista where id = '"+id+"' ;");
				if(rs.next())
					{
					disp = rs.getInt(1);
					if (disp >= 1)
						return true;
					}
			}
		} catch (SQLException e) {
		 
			
		}
		return false;
	}

	public ObservableList<Rivista> getRivistaSingolo() throws SQLException {
		
		conn= ConnToDb.generalConnection();
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();
		 
             rs=conn.createStatement().executeQuery("SELECT * FROM rivista");

            while(rs.next())
            {

        		try {
        			catalogo.add((Rivista) f.createRivista("rivista",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7).toLocalDate(),rs.getInt(8),rs.getFloat(9),rs.getInt(10),rs.getInt(11)));					//rs=rs.next();
        		} catch (Exception e) {
				 
					
				}

            }
		
		System.out.println(catalogo);
		return catalogo;
		
	}

	public Boolean creaRivista(Rivista r) {
		boolean state=false;
    	try 
		{
    		if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
			 	query= "INSERT INTO `ispw`.`rivista`"
			 			+ "(`titolo`,"
			 			+ "`tipologia`,"
			 			+ "`autore`,"
			 			+ "`lingua`,"
			 			+ "`editore`,"
			 			+ "`Descrizione`,"
			 			+ "`dataPubblicazione`,"
			 			+ "`disp`,"
			 			+ "`prezzo`,"
			 			+ "`copieRimanenti`)"
			 			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			 	prepQ = ConnToDb.conn.prepareStatement(query);	
				prepQ.setString(1,r.getTitolo()); 
				prepQ.setString(2,r.getTipologia());
				prepQ.setString(3,r.getAutore());
				prepQ.setString(4,r.getLingua());
				prepQ.setString(5,r.getEditore());
				prepQ.setString(6,r.getDescrizione());
				prepQ.setDate(7, java.sql.Date.valueOf(r.getDataPubb().toString()));  
				prepQ.setInt(8, r.getDisp());
				prepQ.setFloat(9, r.getPrezzo());
				prepQ.setInt(10,r.getCopieRim());


				
				prepQ.executeUpdate();
				//conn.close();
			 	System.out.println("Libro Inserito con successo");
			 	state= true; // true		 			 	
			}
			else {
		    	System.err.print("Errore inserimento utenete");
		    	state= false ;
		    	}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			}

    	//conn.close();				 	
		return state;
		
		
	}

	public void cancella(Rivista r) {
		int row = 0;

		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        //Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				
				PreparedStatement ps=conn.prepareStatement("delete  FROM ispw.rivista where id = "+r.getId()+" ;");
				 row=ps.executeUpdate();
				}
		} catch (SQLException e) {
		 
			
		}
		
		System.out.println("Rivista cancellata : "+row);

		
		
	}

	public ObservableList<Rivista> getRivistaSingoloById(Rivista r) throws SQLException {
		
		conn= ConnToDb.generalConnection();
		ObservableList<Rivista> catalogo=FXCollections.observableArrayList();
		 
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM rivista");

            while(rs.next())
            {

        		try {
        			catalogo.add((Rivista) f.createRivista("rivista",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7).toLocalDate(),rs.getInt(8),rs.getFloat(9),rs.getInt(10),rs.getInt(11)));					//rs=rs.next();
					//rs=rs.next();
        		} catch (Exception e) {
				 
					
				}

            }
		
		System.out.println(catalogo);
		return catalogo;
		
	}

	public void aggiornaRivista(Rivista r) throws SQLException {
		int rowAffected=0;
		
					 conn = ConnToDb.generalConnection();
			st=conn.createStatement();
			query="USE ispw";
			
			//System.out.println("Titolo dopo use ispw:"+l.getTitolo());

			st.executeQuery(query);
		 	String query="UPDATE `ispw`.`rivista`"
		 			+ "SET"
		 			+ "`titolo` = ?,"
		 			+ "`tipologia` =?,"
		 			+ "`autore` = ?,"
		 			+ "`lingua` = ?,"
		 			+ "`editore` = ?,"
		 			+ "`Descrizione` =?,"
		 			+ "`dataPubblicazione` =?,"
		 			+ "`disp` = ?,"
		 			+ "`prezzo` = ?,"
		 			+ "`copieRimanenti` =?"
		 			+ "WHERE `id` = "+r.getId()+";";
		 		
		 	prepQ=conn.prepareStatement(query);
			
			prepQ.setString(1,r.getTitolo());
			prepQ.setString(2,r.getTipologia());
			prepQ.setString(3,r.getAutore());
			prepQ.setString(4,r.getLingua());
			prepQ.setString(5,r.getEditore());
			prepQ.setString(6,r.getDescrizione());
			prepQ.setString(7,r.getDataPubb().toString());
			prepQ.setInt(8,r.getDisp());
			prepQ.setFloat(9,r.getPrezzo());
			prepQ.setInt(10,r.getCopieRim());
		

			rowAffected = prepQ.executeUpdate();
			prepQ.close();
			
            System.out.println(("Row affected "+ rowAffected));

	 }	
	
	public void generaReport() throws SQLException, IOException
	{
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				
				
				rs=conn.createStatement().executeQuery("select titolo,editore,copieRimanenti,prezzo as totale  from rivista;");
				
				 FileWriter w;
		            w=new FileWriter("ReportFinale\\riepilogoRiviste.txt");

		            BufferedWriter b;
		            b=new BufferedWriter (w);
		            while(rs.next())
		            {
		        		try {
		        	

				
								rs.getString(1);
								rs.getString(2);
								rs.getInt(3);
								rs.getFloat(4);
								
										
				
		        		b.write("Titolo :"+rs.getString(1)+"\t"+"Editore :"+rs.getString(2)+"\t"+"Ricavo totale :" +rs.getInt(3)*rs.getFloat(4)+"\n");




		     			b.flush();


		        			} catch (Exception e) {
						 
							
						}
		        		
		            }


		          b.close();
				}
			
	
	}


		
}

		
	

	



