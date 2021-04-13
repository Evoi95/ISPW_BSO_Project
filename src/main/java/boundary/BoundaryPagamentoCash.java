package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerPagamentoCash;
import controller.SingeltonSystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BoundaryPagamentoCash implements Initializable{
	@FXML
	private Pane panel;
	@FXML
	private GridPane grid;
	@FXML
	private Label header;
	@FXML
	private Label labelN;
	@FXML
	private Label labelC;
	@FXML
	private Label labelVP;
	@FXML
	private Label labelCom;
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cognomeTF;
	@FXML
	private TextField viaTF;
	@FXML
	private TextArea eventualiArea;
	@FXML
	private Button buttonI;
	@FXML
	private Button buttonA;

	private ControllerPagamentoCash CPC;

	private String n, c, v, com;
	private static SingeltonSystemState vis = SingeltonSystemState.getIstance();

	@FXML
	private void procediCash() throws IOException {
		try {

			n = nomeTF.getText();
			c = cognomeTF.getText();
			v = viaTF.getText();
			com = eventualiArea.getText();

			if (n.equals("") || c.equals("") || v.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("  Riepilogo pagamento  ");
				alert.setHeaderText("Esito pagamento contanti:");
				alert.setContentText("Non eseguito");
				alert.showAndWait();

				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("pagamentoContrassegno.fxml"));
				stage.setTitle("Benvenuto nella schermata del riepilogo ordine");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				throw new IOException();

			} else {

				CPC.controlla(n, c, v, com,0);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("  Riepilogo pagamento  ");
				alert.setHeaderText("Esito pagamento contanti:");
				alert.setContentText("eseguito");
				alert.showAndWait();
				if(vis.getIstance().getIsPickup()) 
				{
					Stage stage;
					Parent root;
					stage = (Stage) buttonI.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("scegliNegozio.fxml"));
					stage.setTitle("Benvenuto nella schermata per scegliere il negozio");
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();	
				}
				else
				{
				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("download.fxml"));
				stage.setTitle("Benvenuto nella schermata per il download");
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				}
			}
		} catch (Exception e) {
			e.getCause();
		}

	}

	@FXML
	private void annullaCash() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("acquista.fxml"));
		stage.setTitle("benvenuto nella schermata del riepilogo ordine");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				try {
					CPC = new ControllerPagamentoCash();
				} catch (Exception e) {
				 
					
				}

	}

}
