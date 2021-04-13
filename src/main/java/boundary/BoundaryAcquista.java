package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ControllerAcquista;
import controller.SingeltonSystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BoundaryAcquista implements Initializable {
	@FXML
	private SplitPane split;
	@FXML
	private AnchorPane ap1;
	@FXML
	private AnchorPane ap2;
	@FXML
	private Label header;
	@FXML
	private Label labelN;
	@FXML
	private Label labelCosto;
	@FXML
	private Label labelQ;
	@FXML
	private Label labelT;
	@FXML
	private Label  nome;
	@FXML
	private Label  dispLabel;
	@FXML
	private Label costo;
	@FXML
	private TextField quantita;
	@FXML
	private CheckBox ritiroN;

	@FXML
	private Label totale;
	@FXML
	private Label labelPag;
	@FXML
	private RadioButton buttonCC;
	@FXML
	private RadioButton buttonCash;
	@FXML
	private Button calcola;
	@FXML
	private Button link;

	private ControllerAcquista cA;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	
	
	@FXML

	private void pagaCC() throws IOException {
		if(ritiroN.isSelected()) {
			vis.setPickup(true);
		}
		else if (!ritiroN.isSelected())
		{
			vis.setPickup(false);
		}
		Stage stage;
		Parent root;
		stage = (Stage) buttonCC.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("pagamentoCC.fxml"));
		stage.setTitle("Benvenuto nella schermata dell'acquisto con carta credito");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();


	}

	@FXML
	private void pagaCash() throws IOException {

		Stage stage;
		Parent root;
		stage = (Stage) buttonCash.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("pagamentoContrassegno.fxml"));
		stage.setTitle("Benvenuto nella schermata dell'acquisto in contanti");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	private void initialize() {
		costo.setText("");
		quantita.setText("");
		totale.setText("");
	}

	@FXML
	private void importo() throws IOException, SQLException {
		if (!nome.getText().equals("")) {
			buttonCC.setDisable(false);
			buttonCash.setDisable(false);

			String scelta = cA.getType();
			// qui mettere un controllo dal db oer il tipo di prodotto scelto usando l'istanza visualizza
			if (scelta.equals("libro")) {
				float x = cA.totale(nome.getText(), Integer.parseInt(quantita.getText()));
				costo.setText("" + x);
				float tot;
				tot = x * (Float.parseFloat(quantita.getText()));
				totale.setText("" + tot);
				cA.inserisciAmmontareL(tot);
			} else if (scelta.equals("giornale")) {
				labelN.setText("Leggere nome giornale");
				float y = cA.totaleG(nome.getText(), Integer.parseInt(quantita.getText()));
				costo.setText("" + y);

				float tot1;
				tot1 = y * (Float.parseFloat(quantita.getText()));
				totale.setText("" + tot1);
				cA.inserisciAmmontareG(tot1);



			} else if (scelta.equals("rivista")) {
				float z = cA.totaleR(nome.getText(), Integer.parseInt(quantita.getText()));
				costo.setText("" + z);
				float tot2;
				tot2 = z * (Float.parseFloat(quantita.getText()));
				totale.setText("" + tot2);
				cA.inserisciAmmontareR(tot2);

				

			} else {
				throw new IOException();
			}

		}

	}

	public BoundaryAcquista() throws SQLException {
		cA = new ControllerAcquista();
	}

	@FXML
	private void indietro() throws IOException {
		if( vis.getIsLogged()) {
		Stage stage;
		Parent root;
		stage = (Stage) link.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		else
		{
			Stage stage;
			Parent root;
			stage = (Stage) link.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		buttonCC.setDisable(true);
		buttonCash.setDisable(true);
		try {
			nome.setText(cA.getNomeById());
			dispLabel.setText(""+cA.getDisp());
			
		} catch (SQLException e) {
		 
			
		} 
	}

}
