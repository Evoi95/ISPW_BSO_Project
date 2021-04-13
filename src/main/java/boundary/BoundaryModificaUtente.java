package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerModificaUtente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BoundaryModificaUtente implements Initializable {
	
	private ControllerModificaUtente cMU;
	@FXML
	private Pane pane;
	@FXML
	private GridPane grid;
	@FXML
	private Label header;
	@FXML
	private Label credV;
	@FXML
	private Label credN;
	@FXML
	private Label nomeL;
	@FXML
	private Label vecchioNL;
	@FXML
	private TextField nuovoNL;
	@FXML
	private Label cognomeL;
	@FXML
	private Label vecchioCL;
	@FXML
	private TextField nuovoCL;
	@FXML
	private Label emailL;
	@FXML
	private Label vecchiaEmailL;
	@FXML
	private TextField nuovaEmailL;
	@FXML
	private Label pwdL;
	@FXML
	private PasswordField vecchiaPwd;
	@FXML
	private PasswordField nuovaPwd;
	@FXML
	private Label descL;
	@FXML
	private Label vecchiaDescL;
	@FXML
	private TextField nuovaDescL;
	@FXML
	private Label dataL;
	@FXML
	private Label vecchiaDNL;
	@FXML
	private DatePicker nuovaDNL;
	@FXML
	private Button buttonV;
	@FXML
	private Button aggiornaB;
	@FXML
	private Button annullaB;
	
	
	@FXML
	private void visualizza()
	{
		//cMU.prendi();
		vecchioNL.setText(cMU.prendi().getNome());
		vecchioCL.setText(cMU.prendi().getCognome());
		vecchiaEmailL.setText(cMU.prendi().getEmail());
		vecchiaPwd.setText(cMU.prendi().getPassword());
		vecchiaDescL.setText(cMU.prendi().getDescrizione());
		vecchiaDNL.setText(cMU.prendi().getDataDiNascita().toString());
		

	}
	@FXML
	private void aggiorna()
	{
		if(cMU.aggiorna(nuovoNL.getText(),nuovoCL.getText(),
				nuovaEmailL.getText(),nuovaPwd.getText(),
				nuovaDescL.getText(),nuovaDNL.getValue(),vecchiaEmailL.getText()))
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Conferma modifiche");
			alert.setHeaderText("Modifiche inserite!");
			alert.setContentText("La modifica dei dati del profilo e' avvenuta con successo");
			alert.showAndWait();
		
		}
		else
		{			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Errore nei dati inseriti");
			alert.setContentText("Ricontrolla i dati che hai inserito !");
			alert.showAndWait();}
		
		// scermata  di conferma aggiornamento dati  
		
		
	}
	@FXML
	private void annulla() throws IOException
	{
		//scermata precedente
		Stage stage;
		Parent root;
		stage = (Stage) annullaB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("visualizzaProfilo.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo ordine");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				cMU=new ControllerModificaUtente();

	}
	
	
	
	
	
	
	
}
