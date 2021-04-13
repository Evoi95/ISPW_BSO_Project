package boundary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import controller.ControllerCancUser;
import controller.ControllerModifUserPage;
import controller.ControllerUserPage;
import controller.SingeltonSystemState;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import entity.users.singelton.TempUser;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class BoundaryUserPage implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private Button buttonA;
	@FXML
	private Button buttonM;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonC;
	@FXML
	private Button Indietro;
	@FXML
	private Button buttonP;
	@FXML
	private TextArea elencoUtenti;
	@FXML
	private Label idL;
	@FXML
	private TextField utenteTF;
	
	private ControllerUserPage cUP;
	private ControllerCancUser cCU;
	private ControllerModifUserPage cMPU;
	
	private SingeltonSystemState vis=SingeltonSystemState.getIstance();
	@FXML
	private void aggiungi() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("addUserPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		
	}
	@FXML
	private void modifica() throws IOException, SQLException
	{
		vis.getIstance().setId(Integer.parseInt(utenteTF.getText()));
		int max = 0;
		max=cMPU.prendiIdMax();
		
		System.out.println("Utenti massimi "+max);

		
		if(Integer.parseInt(utenteTF.getText())<1)// && Integer.parseInt(utenteTF.getText())>max)
		{
	
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("ID Utente non trovato in quanto minore di 1");
			alert.setContentText("Re immetterlo");
			alert.showAndWait();
			
			

		}
		else if (Integer.parseInt(utenteTF.getText())>max)// && Integer.parseInt(utenteTF.getText())>max)
		{
	
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("ID Utente non trovato in quanto maggiore del numero utenti");
			alert.setContentText("Re immetterlo");
			alert.showAndWait();
			
			

		}

		else {
			
		
		System.out.println("Id in BoundaryUserPage : "+vis.getIstance().getId());
		Stage stage;
		Parent root;
		stage = (Stage) buttonM.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("modUserPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}

	}
	@FXML
	private void cancella()
	{
		cCU.cancellaUtente(Integer.parseInt(utenteTF.getText()));
	}
	@FXML
	private void torna() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) Indietro.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	@FXML
	private void prendi() throws SQLException{
		cUP.getUtenti();
		elencoUtenti.clear();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("ReportFinale\\riepilogoUtenti.txt"));
		String line = reader.readLine();
        while(line!=null) {
            elencoUtenti.appendText(line.concat("\n"));
            

            System.out.println(line);
            line = reader.readLine();
        }
		}
        catch(IOException e)
        {
        	e.getCause();
        }
        finally {
        try {
			reader.close();
		} catch (IOException e) {
		 
			
		}
        }

		    	    
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cUP=new ControllerUserPage();
		cCU=new ControllerCancUser();
		cMPU=new ControllerModifUserPage();
	}
	
	
	
	
	
	
	
	
	
	}
