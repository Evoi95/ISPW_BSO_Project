package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerRicercaPage;
import controller.ControllerVisualizzaGiornale;
import controller.ControllerVisualizzaLibro;
import controller.ControllerVisualizzaRivista;
import entity.factorybook.Raccolta;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class BoundaryRicercaPage  implements Initializable{
	
	@FXML
	private Pane pane;
	@FXML
	private Label labelT;
	@FXML
	private TextField cercaT;
	@FXML
	private TextField idT;
	@FXML
	private TableView <Raccolta> table;
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> id = new TableColumn<>("ID");
	@FXML
	private Button buttonC; 
	@FXML
	private Button buttonV; 
	@FXML
	private Button buttonB;
	
	private String i;
	private String title = "Benvenuto nella schermata del riepilogo ordine";
	private ControllerRicercaPage CRP;
	private ControllerVisualizzaLibro CVL;
	private ControllerVisualizzaGiornale CVG;
	private ControllerVisualizzaRivista CVR;
	
	public BoundaryRicercaPage()
	{
		CRP = new ControllerRicercaPage();
		CVL = new ControllerVisualizzaLibro();
		CVG	= new ControllerVisualizzaGiornale();
		CVR	= new ControllerVisualizzaRivista();
	}
	@FXML
	private void cerca() throws SQLException
	{
		// e populo la tabella
		//col controller faccio la ricerca basandomi sul singerlton battona+
		table.setItems( CRP.cercaPerTipo(cercaT.getText()));
	}

	// mostro i dati e le relative schermate
	@FXML
	private void apri() throws IOException
	{
		i = idT.getText();
		//col controller Apro basandomi sul singerlton battona
		if(CRP.returnType().equals("libro"))
		{
			CVL.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonV.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("visualizzaBookPage.fxml"));
			stage.setTitle(title);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if(CRP.returnType().equals("giornale"))
		{
			CVG.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonV.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("visualizzaDailyPage.fxml"));
			stage.setTitle(title);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if(CRP.returnType().equals("rivista"))
		{
			CVR.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonV.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("visualizzaMagazinePage.fxml"));
			stage.setTitle(title);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore Id inserito");
			alert.setHeaderText("Errore nei dati inseriti");
			alert.setContentText("Ricontrolla i dati che hai inserito !");
			alert.showAndWait();
		}
	}

	@FXML
	private void torna() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) buttonB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ricercaPerTipo.fxml"));
		stage.setTitle(title);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		autore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));

	}

	
}
