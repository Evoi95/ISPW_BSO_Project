package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerCompravenditaLibri;
import controller.ControllerVisualizzaLibro;
import controller.SingeltonSystemState;
import entity.factorybook.Raccolta;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BoundaryCompravenditaLibri implements Initializable {
	@FXML
	private Pane panel;
	@FXML
	private Label header;
	@FXML
	private TableView<Raccolta> table;
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> editore = new TableColumn<>("Editore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> categoria = new TableColumn<>("Categoria");
	@FXML
	private TableColumn<Raccolta, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Raccolta, SimpleIntegerProperty> idLibro = new TableColumn<>("Id Libro");

	@FXML
	private Button buttonL;
	@FXML
	private TextField entryText;
	@FXML
	private Button buttonV;
	@FXML
	private Button buttonA;
	@FXML
	private Button buttonI;

	private ControllerCompravenditaLibri CCV;
	private ControllerVisualizzaLibro CVL;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	
	@FXML
	private void verifica() throws  IOException, SQLException {
		//CCV.disponibilitaLibro(entryText.getText());// verifico se libro e presente
		// String libro=entryText.getText();
		// return CCV.disponibilitaLibro( libro);
		try
		{
			String i = entryText.getText();
		if( CCV.disponibilitaLibro(i)) {
			CVL.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonV.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("visualizzaBookPage.fxml"));
			stage.setTitle("Benvenuto nella schermata del riepilogo ordine");

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
			
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore Id libro inserito");
			alert.setHeaderText("Errore nei dati inseriti");
			alert.setContentText("Ricontrolla i dati che hai inserito !");
			alert.showAndWait();
		}
		}
		catch (NumberFormatException e)
		{
			e.getMessage();
		}

	}

	@FXML
	private void procedi() throws IOException, SQLException {
		try
		{
			String i = entryText.getText();
		if( CCV.disponibilitaLibro(i)) {
			CVL.setID(i);
			Stage stage;
			Parent root;
			stage = (Stage) buttonA.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("acquista.fxml"));
			stage.setTitle("Benvenuto nella schermata del riepilogo ordine");

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore Id libro non inserito");
			alert.setHeaderText("Errore nei dati inseriti");
			alert.setContentText("Ricontrolla i dati che hai inserito !");
			alert.showAndWait();
		}
		}
		catch (NumberFormatException e)
		{
			e.getMessage();
		}

		
	}

	@FXML
	private void vediListaLibri() throws SQLException {

		table.setItems(CCV.getLibri());

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		CCV = new ControllerCompravenditaLibri();
		CVL = new ControllerVisualizzaLibro();

		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
		categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		idLibro.setCellValueFactory(new PropertyValueFactory<>("id"));


	}

	@FXML
	private void torna() throws IOException {
		
		String tipoU=CCV.retTipoUser();
		if( vis.getIstance().getIsLogged() &&  tipoU.equalsIgnoreCase("A")) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
			 if( vis.getIstance().getIsLogged() && (tipoU.equalsIgnoreCase("W") || tipoU.equalsIgnoreCase("E")) ) {

			{
				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("homePageAfterLoginES.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				}
			}
			else {
				
					Stage stage;
					Parent root;
					stage = (Stage) buttonI.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}

			}
	}


