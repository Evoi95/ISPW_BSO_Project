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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import entity.factorybook.Giornale;
import entity.factorybook.Libro;
import entity.factorybook.Raccolta;
import entity.factorybook.Rivista;

public class GiornaleDao {
	private Factory f;
	private String name;
	private static Statement st = null ;
	private static String query ;
	private static String qTrigger ;
	private static PreparedStatement prepQ = null; 
	private static Connection conn ;
	private static int q ; 
	private static ResultSet rs;
	private String categoria;//=g.getTipologia();

	
 	public void getDesc(entity.factorybook.Giornale g) 
	{	           
		Connection conn = ConnToDb.generalConnection();

		 try {
	            //String url = "jdbc:msql://200.210.220.1:1114/Demo";
	            Statement stmt = conn.createStatement();
	            ResultSet rs;
	 
	            rs = stmt.executeQuery("select * from giornale where titolo ='"+g.getTitolo()+"'");
	            while ( rs.next() ) {
	                String titolo = rs.getString("titolo");
	                String tipologia=rs.getString("tipologia");
	                String lingua=rs.getString("lingua");	       
	                String editore=rs.getString("editore");
	                Date data=rs.getDate("dataPubblicazione");	
	                int copieR=rs.getInt("copiRim");	
	                int disp=rs.getInt("disp");

	                float prezzo=rs.getFloat("prezzo");
	               // InputStream img=rs.getBinaryStream("img");
	                //int id=rs.getInt("id");



	                
	                Alert alert = new Alert(AlertType.INFORMATION);
	    	        alert.setTitle("  Riepilogo  ");
	    	        alert.setHeaderText("Ecco il riepigolo del libro");
	    	        alert.setContentText("  Titolo : "+titolo+"\n"+"tipologia : "+tipologia+"\n"+"lingua : "+lingua+"\n"+"editore : "+editore+ "\n"+
	    	        "data pubblicazione : "+data+"\n"+"numero copie rimanenti :"+copieR+"\n"+"disponibilita :"+disp+"\n"+"prezzo :"+prezzo
	    	        		+"\n"+"foto copertina : "+"\n");
	    	        alert.showAndWait();
	    	       
	    	        
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
	
	public float getCosto(Giornale g) 
	{		 Connection conn = ConnToDb.generalConnection();	
	float prezzo=(float) 0.0;


		try {
         Statement stmt = conn.createStatement();
         ResultSet rs;

         rs = stmt.executeQuery("select * from giornale where titolo  ='"+g.getTitolo()+"'");
         while ( rs.next() ) {
              prezzo=rs.getFloat("prezzo");

         }
		}catch(SQLException e)
		{
			e.getCause();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}
		return prezzo;
		
	}
	
	public void aggiornaDisponibilita(Giornale g) 
	{
		Connection conn=null;
		PreparedStatement stmt=null;
		int d=g.getDisponibilita();
		//)g.getCopieRimanenti();

		 try {
			  conn = ConnToDb.generalConnection();
		      stmt = conn.prepareStatement("update giornale set copiRim=copiRim-'"+d+"' where titolo='"+g.getTitolo()+"'");
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

		 System.out.println("LibroDao. questy");

		}

	public void daiPrivilegi() 
	{
		 conn=null;
		 prepQ=null;
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
			// stmt.close();
			 try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
			 System.out.println("Ho chiuso tutto");
			 
		 }

		 System.out.println("LibroDao. privilegi");

}
	
	public ObservableList<Raccolta> getGiornali()  {
		
		
		Connection conn= ConnToDb.generalConnection();

		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
		//ConnToDb.connection();
        ResultSet rs;
		try {
			rs = conn.createStatement().executeQuery("SELECT * FROM ispw.giornale");
		
        while(rs.next())
        {
           // System.out.println("res :"+rs);

    		try {
				catalogo.add(f.createGiornale("giornale",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getInt(6),rs.getInt(7),rs.getFloat(8),rs.getInt(9)));
				//rs=rs.next();
    		} catch (Exception e) {
			 
				
			}

        }
		} catch (SQLException e1) {
		 
			e1.printStackTrace();
		}
		finally {
    	//System.out.println(catalogo);

        try {
			conn.close();
		} catch (SQLException e) {
		 
			
		}
		}
	
	
	return catalogo;
		}
	
		
	public Giornale getGiornale(Giornale G,int id) 
	{

		Connection c= ConnToDb.generalConnection();
        ResultSet rs;
		try {
			rs = c.createStatement().executeQuery("SELECT * FROM giornale where id = "+id+" ");
		if (rs.next())
        {
        	G = (Giornale) f.createGiornale("giornale", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(), rs.getInt(6),rs.getInt(7),rs.getFloat(8),rs.getInt(9)) ;
        	//(Rivista) f.createRivista("rivista", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7).toLocalDate(),rs.getInt(8),rs.getFloat(9),rs.getInt(10),rs.getInt(11)); 
        }
        else {
        	System.out.println("non ho torvato un cazzo e ritorno null");

        }
		} catch (SQLException e) {
		 
			
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}
		return G;
        
	}
	
	public GiornaleDao()
	{
		f=new Factory();
	}
		
	public int retId(Giornale g) throws SQLException {
			int id = 0;
			String titolo=g.getTitolo();
			 Connection conn = ConnToDb.generalConnection();
			 try {
	         Statement stmt = conn.createStatement();
	         ResultSet rs;

	         rs = stmt.executeQuery("select id from giornale where titolo ='"+titolo+"'");
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

	public String retTip(Giornale g)  {
			
			String titolo=g.getTitolo();
			  conn = ConnToDb.generalConnection();
			 try {
	          st = conn.createStatement();
	        // ResultSet rs;

	         rs = st.executeQuery("select tipologia from ispw.giornale where titolo ='"+titolo+"'");
	         while ( rs.next() ) {
	              categoria=rs.getString("tipologia");

	         }
			 }catch(SQLException e)
			 {
				 e.getCause();
			 }finally {
					try {
						conn.close();
					} catch (SQLException e) {
					 
						
					}
				}
				
			return categoria;

			
		}
			
	public String getNome(Giornale G) 
	{
	
		conn= ConnToDb.generalConnection();
        ResultSet rs;
		try {
			rs = conn.createStatement().executeQuery("SELECT titolo FROM giornale where id = "+G.getId()+" ");
		if (rs.next())
        {
        	name = rs.getString(1);
        }
        else {
        	System.out.println("non ho torvato un cazzo e ritorno null");
            name=null;

        }	
		} catch (SQLException e) {
		 
			
		}
        finally {
        	try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
        }
		return name;
   }

	public int getDisp(Giornale G) 
	{
		int disp;
        ResultSet rs;
		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				rs=  stmt.executeQuery(
						"SELECT `giornale`.`disp` FROM `ispw`.`giornale` where `id` = `"+G.getId()+"` ;");
				disp = rs.getInt(1);
				if (disp >= 1)
					return disp;
				else if (disp == 0)
					return 0;
			}
		} catch (SQLException e) {
		 
			
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}
		return -1;
	}

	public int getQuantita(Giornale G) 
	{
        ResultSet rs;
		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				rs=  stmt.executeQuery(
						"SELECT `giornale`.`copiRim` FROM `ispw`.`giornale` where `id` = "+G.getId()+" ;");
				if (rs.next()) {
					q = rs.getInt(1);
				}			
			}
		} catch (SQLException e) {
		 
			
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}

		return q;
	}

	public boolean checkDisp(Giornale g,int id) 
	{
		int disp;
        ResultSet rs;
		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				
				rs=  stmt.executeQuery("SELECT disp FROM giornale where id = '"+id+"' ;");
				if(rs.next())
					{
					disp = rs.getInt(1);
					if (disp >= 1)
						return true;
					}
			}
		} catch (SQLException e) {
		 
			
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}
		return false;
	}

	public ObservableList<Giornale> getLibriSingolo()  {
		
		Connection c= ConnToDb.generalConnection();
		ObservableList<Giornale> catalogo=FXCollections.observableArrayList();
		 
            ResultSet rs;
			try {
				rs = c.createStatement().executeQuery("SELECT * FROM giornale");
			
            while(rs.next())
            {

        		try {
        			catalogo.add((Giornale)f.createGiornale("giornale",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getInt(6),rs.getInt(7),rs.getFloat(8),rs.getInt(9)));
					//catalogo.add((Giornale) f.createGiornale("giornale",rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8).toLocalDate(),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getInt(12),rs.getFloat(13),rs.getInt(14),rs.getInt(15)));
					//rs=rs.next();
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

		//System.err.println(catalogo);
            return catalogo;
		
	

	}


	public boolean creaGiornale(Giornale g) {
		{


			boolean state=false;
	    	try 
			{
	    		if (ConnToDb.connection())
				{
					conn = ConnToDb.generalConnection();
					query= "INSERT INTO `ispw`.`giornale`"
				 			+ "(`titolo`,"
				 			+ "`tipologia`,"
				 			+ "`lingua`,"
				 			+ "`editore`,"
				 			+ "`dataPubblicazione`,"
				 			+ "`copiRim`,"
				 			+ "`disp`,"
				 			+ "`prezzo`)"
				 			+ "VALUES"
				 			+ "(?,?,?,?,?,?,?,?);"
				 			+ "";
					prepQ = ConnToDb.conn.prepareStatement(query);	
					prepQ.setString(1,g.getTitolo()); 
					prepQ.setString(2,g.getTipologia());
					prepQ.setString(3,g.getLingua());
					prepQ.setString(4,g.getEditore());
					prepQ.setDate(5, java.sql.Date.valueOf(g.getDataPubb().toString())); 
					prepQ.setInt(6,g.getCopieRimanenti());
					prepQ.setInt(7, g.getDisponibilita());
					prepQ.setFloat(8, g.getPrezzo());
					
					prepQ.executeUpdate();
					//conn.close();
				 	System.out.println("Giornale Inserito con successo");
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
		}

	public void cancella(Giornale g) {
		int row = 0;

		try {
			if (ConnToDb.connection())
			{
				conn = ConnToDb.generalConnection();
				st=conn.createStatement();
		        //Statement stmt = conn.createStatement();
				query="USE ispw";
				st.executeQuery(query);
				
				PreparedStatement ps=conn.prepareStatement("delete  FROM ispw.giornale where id = "+g.getId()+" ;");
				 row=ps.executeUpdate();
				}
		} catch (SQLException e) {
		 
			
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
			 
				
			}
		}
		
		System.out.println("Giornale cancellato : "+row);
	}

	public ObservableList<Giornale> getGiornaliSingoloById(Giornale g)  {
		Connection c= ConnToDb.generalConnection();
		ObservableList<Giornale> catalogo=FXCollections.observableArrayList();
		 
            ResultSet rs;
			try {
				rs = c.createStatement().executeQuery("SELECT * FROM giornale where id="+g.getId()+"");
			
            while(rs.next())
            {

        		try {
        			catalogo.add((Giornale)f.createGiornale("giornale",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getInt(6),rs.getInt(7),rs.getFloat(8),rs.getInt(9)));
					//rs=rs.next();
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

		
		System.out.println(catalogo);
		return catalogo;
		
	}

	public ObservableList<Raccolta> getGiornaliByName(String s) throws SQLException {
		
		Connection c= ConnToDb.generalConnection();
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		 
            ResultSet rs=c.createStatement().executeQuery("SELECT * FROM giornale where titolo = '"+s+"' OR editore = '"+s+"'");

            while(rs.next())
            {

        		try {
        			catalogo.add(f.createGiornale("giornale", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(),rs.getInt(6),rs.getInt(7),rs.getFloat(8),rs.getInt(9)));
					//catalogo.add(f.createLibro("libro",rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8).toLocalDate(),rs.getString(9),rs.getInt(10),rs.getString(11),rs.getInt(12),rs.getFloat(13),rs.getInt(14),rs.getInt(15)));
					//rs=rs.next();
        		} catch (Exception e) {
				 
					
				}

            }
		c.close();
		System.out.println(catalogo);
		return catalogo;
		

	}

	public void aggiornaGiornale(Giornale g)  {
		
		int rowAffected=0;
		
		
		
		//System.out.println("IdLibro prima del try nel dao:"+l.getId());

		
	 	 

			 conn = ConnToDb.generalConnection();
			try {
				st=conn.createStatement();
			query="USE ispw";
			

			st.executeQuery(query);
		 	String query=" UPDATE `ispw`.`giornale`"
		 			+ "SET"
		 			+ "`titolo` =?,"
		 			+ "`tipologia` = ?,"
		 			+ "`lingua` = ?,"
		 			+ "`editore` = ?,"
		 			+ "`dataPubblicazione` = ?,"
		 			+ "`copiRim` = ?,"
		 			+ "`disp` = ?,"
		 			+ "`prezzo` = ?"
		 			+ "WHERE `id` = "+g.getId()+"";
			prepQ=conn.prepareStatement(query);
			
			prepQ.setString(1,g.getTitolo());
			prepQ.setString(2,g.getTipologia());
			prepQ.setString(3,g.getLingua());
			prepQ.setString(4, g.getEditore());
			prepQ.setString(5,g.getDataPubb().toString());
			prepQ.setInt(6,g.getCopieRimanenti());
			prepQ.setInt(7,g.getDisponibilita());
			prepQ.setFloat(8,g.getPrezzo());
			//prepQ.setInt(14,l.getCopieRim());
		//	prepQ.setInt(15,l.getId());


			rowAffected = prepQ.executeUpdate();
			prepQ.close();
			
            System.out.println(("Row affected "+ rowAffected));
            
			} catch (SQLException e) {
			 
				
			}
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
				 
					
				}
			}

	 }	

	public void generaReport() 
	{
			try {
				if (ConnToDb.connection())
				{
					conn = ConnToDb.generalConnection();
					st=conn.createStatement();
					query="USE ispw";
					st.executeQuery(query);
					
					
					rs=conn.createStatement().executeQuery("select titolo,editore,copiRim,prezzo as totale  from giornale;");
					
					 FileWriter w;
				        w=new FileWriter("ReportFinale\\riepilogoGiornali.txt");

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
			} catch (SQLException | IOException e) {
			 
				
			}
			finally {
				try {
					conn.close();
				} catch (SQLException e) {
				 
					
				}
			}
			
	
		
	}

		
}


		
	




