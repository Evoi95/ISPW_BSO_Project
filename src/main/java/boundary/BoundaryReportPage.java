package boundary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerReportGiornali;
import controller.ControllerReportLibri;
import controller.ControllerReportRiviste;
import controller.ControllerUserPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class BoundaryReportPage implements Initializable {
	
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private Button totaleB;
	@FXML
	private Button libroB;
	@FXML
	private Button raccoltaB;
	@FXML
	private Button giornaliB;
	@FXML
	private Button buttonI;
	@FXML
	private Button rivisteB;
	@FXML
	private TextArea ta;
	
	private ControllerReportLibri cRL;
	private ControllerReportGiornali cRG;
	private ControllerReportRiviste cRR;
	private ControllerUserPage cUP;
	
	
	
	@FXML
	private void totale() throws SQLException
	{
		ta.clear();
		try {
			cRL.generaReportLibri();
		cRG.generaReportGiornali();
		cRR.generaReportRiviste();
		cUP.getUtenti();
		} catch (IOException e) {
		 
			
		} catch (SQLException e) {
		 
			
		}
		
		
		BufferedReader readerL = null;
		try {
			readerL = new BufferedReader(new FileReader("ReportFinale\\riepilogoLibro.txt"));
		String line = readerL.readLine();
        while(line!=null) {
            ta.appendText(line.concat("\n"));
            

            System.out.println(line);
            line = readerL.readLine();
        }
		} catch (FileNotFoundException e) {
		 
			
		} catch (IOException e) {
		 
			
		}
		finally {
			        try {
						readerL.close();
					} catch (IOException e) {
					 
						
					}

		}
        

		BufferedReader readerG = null;
		try {
			readerG = new BufferedReader(new FileReader("ReportFinale\\riepilogoGiornali.txt"));
		String line1 = readerG.readLine();
        while(line1!=null) {
            ta.appendText(line1.concat("\n"));
            

            System.out.println(line1);
            line1 = readerG.readLine();
        }
		} catch (IOException e) {
		 
			
		 
		} 
		finally {
        
        try {
			readerG.close();
		} catch (IOException e) {
		 
			
		}
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("ReportFinale\\riepilogoRiviste.txt"));
		} catch (FileNotFoundException e) {
		 
			
		}
        String line2 = null;
		try {
			line2 = reader.readLine();
		while(line2!=null) {
            ta.appendText(line2.concat("\n"));
            

            System.out.println(line2);
            try {
				line2 = reader.readLine();
			} catch (IOException e) {
			 
				
			}
		}
		}catch(IOException e)
        {
        	e.getCause();
        }
        
        finally {
        try {
			reader.close();
		} catch (IOException e) {
		 
			
		}
        }
        
        BufferedReader reader3 = null;
		try {
			reader3 = new BufferedReader(new FileReader("ReportFinale\\riepilogoUtenti.txt"));
		} catch (FileNotFoundException e) {
		 
			
		}
        String line3 = null;
		try {
			line3 = reader3.readLine();
		while(line3!=null) {
            ta.appendText(line3.concat("\n"));
            

            System.out.println(line3);
            try {
				line3 = reader3.readLine();
			} catch (IOException e) {
			 
				
			}
		}}catch(IOException e)
		{
			e.getMessage();
		}
	finally {
        try {
			reader3.close();
		} catch (IOException e) {
		 
			
		}
	}


		

	}
	@FXML
	private void reportLibri() throws IOException 
	{
		ta.clear();

		  
		try {
			cRL.generaReportLibri();
		} catch (IOException | SQLException e) {
		 
			
		}
		BufferedReader reader = new BufferedReader(new FileReader("ReportFinale\\riepilogoLibro.txt"));
        String line = reader.readLine();
       // ta.appendText("\tEcco il report dei libri\t \n");
        while(line!=null) {
            ta.appendText(line.concat("\n"));
            

            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();
    }
		

	
	@FXML
	private void raccolta() throws IOException, SQLException
	{
		ta.clear();
		cRL.generaReportLibri();
		cRG.generaReportGiornali();
		cRR.generaReportRiviste();
		
		BufferedReader readerL = new BufferedReader(new FileReader("ReportFinale\\riepilogoLibro.txt"));
        String line = readerL.readLine();
        while(line!=null) {
            ta.appendText(line.concat("\n"));
            

            System.out.println(line);
            line = readerL.readLine();
        }
        readerL.close();

		BufferedReader readerG = new BufferedReader(new FileReader("ReportFinale\\riepilogoGiornali.txt"));
        String line1 = readerG.readLine();
        while(line1!=null) {
            ta.appendText(line1.concat("\n"));
            

            System.out.println(line1);
            line1 = readerG.readLine();
        }
        readerG.close();

		BufferedReader reader = new BufferedReader(new FileReader("ReportFinale\\riepilogoRiviste.txt"));
        String line2 = reader.readLine();
        while(line2!=null) {
            ta.appendText(line2.concat("\n"));
            

            System.out.println(line2);
            line2 = reader.readLine();
        }
        reader.close();

		
	}
	@FXML
	private void reportGiornali() throws IOException
	{
		ta.clear();

		try {
			cRG.generaReportGiornali();
		} catch (IOException|SQLException e) {
		 
			
		} 
		BufferedReader reader = new BufferedReader(new FileReader("ReportFinale\\riepilogoGiornali.txt"));
        String line = reader.readLine();
        while(line!=null) {
            ta.appendText(line.concat("\n"));
            

            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();

	}
	@FXML
	private void indietro() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonI.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei libri");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

	}
	@FXML
	private void reportRiviste() throws IOException
	{
		ta.clear();

		try {
			cRR.generaReportRiviste();
		} catch (IOException e) {
		 
			
		} catch (SQLException e) {
		 
			
		}

		        BufferedReader readerR = new BufferedReader(new FileReader("ReportFinale\\riepilogoRiviste.txt"));
        String line2 = readerR.readLine();
        while(line2!=null) {
            ta.appendText(line2.concat("\n"));
            

            System.out.println(line2);
            line2 = readerR.readLine();
        }
        readerR.close();


		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cRL=new ControllerReportLibri();
		cRG=new ControllerReportGiornali();
		cRR=new ControllerReportRiviste();
		cUP=new ControllerUserPage();
	}

}
