package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerVisualizzaProfilo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryVisualizzaProfilo implements Initializable  {
	@FXML
	private Pane pane;
	@FXML
	private GridPane grid;
	@FXML
	private Label header;
	@FXML
	private Label nomeL;
	@FXML
	private Label cognomeL;
	@FXML
	private Label emailL;
	@FXML
	private Label dataNL;
	@FXML
	private Label labelInsN;
	@FXML
	private Label labelInsC;
	@FXML
	private Label labelInsE;
	@FXML
	private Label labelInsD;
	@FXML
	private Button buttonCred;
	@FXML
	private Button indietroB;
	@FXML
	private Button modificaB;
	@FXML
	private Button listaPB;
	@FXML
	private Button cronologiaB;
	@FXML
	private Button cancellaB;
	
	private ControllerVisualizzaProfilo cVP;
	
	@FXML
	private void credenziali() {
		
		//cVP.getCredenziali();
		
		
		labelInsN.setText(cVP.getCredenziali().getNome());
		labelInsC.setText(cVP.getCredenziali().getCognome());
		labelInsE.setText(cVP.getCredenziali().getEmail());
		labelInsD.setText(cVP.getCredenziali().getDataDiNascita().toString());
		
		/*if(cVP.getCredenziali()==null)
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Errore cancellazione profilo");// line 2
			alert.setHeaderText("Errore cancellazione");// line 3
			alert.setContentText("!--Utente non cancellato--!");// line 4
			alert.showAndWait(); // line 5

		}
		*/

	}
	@FXML
	private void indietro() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) indietroB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei giornali");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

		
	}
	@FXML
	private void modifica() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) modificaB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("modificaUtente.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei giornali");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();	}
	@FXML
	private void cronologia() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) indietroB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("visualizzaOrdine.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei giornali");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();


		
		
		
	}
	
	public BoundaryVisualizzaProfilo()
	{
		cVP=new ControllerVisualizzaProfilo();
	}
	
	@FXML
	private void eliminaProfilo() throws IOException
	{
		
		
			if (cVP.cancellaUtente()==true)
			{
				labelInsN.setText("");
				labelInsC.setText("");
				labelInsE.setText("");
				labelInsD.setText("");
				
				Stage stage;
				Parent root;
				stage = (Stage) cancellaB.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
				stage.setTitle("Registazione andata a buon fine");

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

				
			}
			else {
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("Errore cancellazione profilo");// line 2
				alert.setHeaderText("Errore cancellazione");// line 3
				alert.setContentText("!--Utente non cancellato--!");// line 4
				alert.showAndWait(); // line 5


			}
	}
			
		
			
			
						
			/*
			try {
				if(labelInsN.getText()==null && labelInsC.getText()==null && labelInsE.getText()==null && labelInsD.getText()==null)
				{
					Alert alert=new Alert(AlertType.ERROR);
					alert.setTitle("Errore cancellazione profilo");// line 2
					alert.setHeaderText("Errore cancellazione");// line 3
					alert.setContentText("!--Utente non cancellato--!");// line 4
					alert.showAndWait(); // line 5

				}
			}catch(NullPointerException e)
			{
				
			}*/


			
				
			
			
			
			
		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		labelInsN.setText("");
		labelInsC.setText("");
		labelInsE.setText("");
		labelInsD.setText("");
	}
	
	
	
	
	

}
