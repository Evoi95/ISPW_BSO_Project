package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerCancellaRivista;
import controller.ControllerRivistaPage;
import controller.SingeltonSystemState;
import entity.factorybook.Libro;
import entity.factorybook.Rivista;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryRivistaPage implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private TableView<Rivista>table=new TableView<Rivista>();
	@FXML
	private TableColumn<Rivista, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Rivista, SimpleStringProperty> tipologia = new TableColumn<>("Tipologia");
	@FXML
	private TableColumn<Rivista, SimpleStringProperty> editore = new TableColumn<>("Editore");
	@FXML
	private TableColumn<Rivista, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Rivista, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Rivista, SimpleIntegerProperty> id = new TableColumn<>("ID ProdottoTitolo");
	@FXML
	private Button buttonG;
	@FXML
	private Button buttonAdd;
	@FXML
	private Button modB;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonB;
	private ControllerRivistaPage cRP;
	private SingeltonSystemState vis=SingeltonSystemState.getIstance();

	private ControllerCancellaRivista cCR;
	
	
	
	@FXML
	private void prendiDato()
	{
		vis.getIstance().setId(table.getSelectionModel().getSelectedItem().getId());

	}
	@FXML
	private void genera()
	{
		try {
			table.setItems(cRP.getRivistaS());
		} catch (SQLException e) {
		 
			
		}
	}
	@FXML
	private void aggiungi() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonAdd.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("addRivistaPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	@FXML
	private void modifica() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) modB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("modRivistaPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	@FXML
	private void cancella()
	{
		int id;
		id=SingeltonSystemState.getIstance().getId();
		cCR.cancella(id);
		
	}
	@FXML
	private void indietro() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cRP=new ControllerRivistaPage();
		cCR=new ControllerCancellaRivista();
		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		tipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
		autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));

		
	}

}
