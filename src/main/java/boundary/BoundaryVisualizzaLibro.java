package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerVisualizzaLibro;
import controller.SingeltonSystemState;
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

public class BoundaryVisualizzaLibro implements Initializable {
	
	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane ;
	@FXML
	private Label LabelTitolo;
	@FXML 
	private Label LabelNumeroPagine;
	@FXML
	private Label LabelCodiceISBN;
	@FXML
	private Label LabelEditore;
	@FXML
	private Label LabelAutore;
	@FXML
	private Label LabelLingua;
	@FXML
	private Label LabelCategoria ;
	@FXML
	private Label LabelDate;
	@FXML
	private Label LabelRecensione;
	@FXML 
	private Label LabelDescrizione;
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
	private Label numeroPagineL;
	@FXML
	private Label codeIsbnL;
	@FXML
	private Label editoreL;
	@FXML
	private Label autoreL;
	@FXML
	private Label linguaL;
	@FXML
	private Label categoriaL;
	@FXML
	private Label dataL;
	@FXML
	private Label recensioneL;
	@FXML
	private Label descrizioneL;
	@FXML
	private Label disponibbilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanenteL;
	
	private ControllerVisualizzaLibro CVB;
	private int i;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	
	public BoundaryVisualizzaLibro()
	{
		CVB = new ControllerVisualizzaLibro();
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
		root = FXMLLoader.load(getClass().getResource("compravenditaLibri.fxml"));

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
		i = CVB.getID();
		
		// problema assegnazioni variabili nel dao o controller 
		try {
			LabelTitolo.setText(CVB.getData(i).getTitolo());
			LabelNumeroPagine.setText(""+CVB.getData(i).getNumPag());
			LabelCodiceISBN.setText(CVB.getData(i).getCodIsbn());
			LabelEditore.setText(CVB.getData(i).getEditore());
			LabelAutore.setText(CVB.getData(i).getAutore());
			LabelLingua.setText(CVB.getData(i).getLingua());
			LabelCategoria.setText(CVB.getData(i).getCategoria());
			LabelDate.setText(""+CVB.getData(i).getDataPubb());
			LabelRecensione.setText(CVB.getData(i).getRecensione());
			LabelDescrizione.setText(CVB.getData(i).getDesc());
			LabelDisp.setText(""+CVB.getData(i).getDisponibilita());
			LabelPrezzo.setText(CVB.getData(i).getPrezzo()+"");
			LabelCopieRimanenti.setText(CVB.getData(i).getCopieRim()+"");
		} catch (SQLException e) {
			
		}
		

	}
	

}
