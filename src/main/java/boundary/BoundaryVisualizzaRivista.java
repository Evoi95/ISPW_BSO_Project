package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerVisualizzaRivista;
import controller.SingeltonSystemState;
import database.RivistaDao;
import entity.factorybook.Rivista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryVisualizzaRivista implements Initializable{

	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane ;
	@FXML
	private Label LabelTitolo;
	@FXML
	private Label LabelTipologia;
	@FXML
	private Label LabelEditore;
	@FXML
	private Label LabelLingua;
	@FXML
	private Label LabelDate;
	@FXML
	private Label LabelDisp;
	@FXML
	private Label LabelPrezzo;
	@FXML
	private Label LabelCopieRimanenti;
	@FXML
	private Button buttonBack;
	@FXML
	private Button buttonA;
	@FXML
	private Label titoloL;
	@FXML
	private Label tipologiaL;
	@FXML
	private Label editoreL;
	@FXML
	private Label linguaL;
	@FXML
	private Label dataL;
	@FXML
	private Label disponibbilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanentiL;
	
	private ControllerVisualizzaRivista CVR;
	private int i;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	public BoundaryVisualizzaRivista()
	{
		CVR = new ControllerVisualizzaRivista();
	}
	
	@FXML
	private void Acquista() throws IOException
	{
		
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("acquista.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo ordine");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void annulla() throws IOException
	{
		if (!vis.getIstance().getIsSearch()) {
		Stage stage;
		Parent root;
		stage = (Stage) buttonBack.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("compravenditaRivista.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		else
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonBack.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ricercaPage.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		i = CVR.getID();
		
		try {
			LabelTitolo.setText(CVR.getData(i).getTitolo());
			LabelEditore.setText(CVR.getData(i).getEditore());
			LabelLingua.setText(CVR.getData(i).getLingua());
			LabelDate.setText(""+CVR.getData(i).getDataPubb());
			LabelDisp.setText(""+CVR.getData(i).getDisp());
			LabelPrezzo.setText(CVR.getData(i).getPrezzo()+"");
			LabelCopieRimanenti.setText(CVR.getData(i).getCopieRim()+"");
		} catch (SQLException e) {
			
		}
		

	}
	
}
