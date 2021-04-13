package boundary;

import java.io.IOException;

import controller.ControllerHomePageAfterLogin;
import controller.SingeltonSystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryHomePageAfterLoginSE {
	@FXML
	private Pane pane;
	@FXML
	private GridPane grid;
	@FXML
	private Label header;
	@FXML
	private Button buttonL;
	@FXML
	private Button buttonG;
	@FXML
	private Button buttonR;
	@FXML
	private ImageView imageL;
	@FXML
	private ImageView imageG;
	@FXML
	private ImageView imageR;
	@FXML
	private ImageView imageU;
	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonLogout;
	@FXML
	private Button buttonC;
	@FXML
	private Button buttonGestione;
	
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	@FXML
	private void getListaGiornali() throws IOException {
		vis.getIstance().setIsSearch(false);
		vis.getIstance().setTypeAsDaily();
		Stage stage;
		Parent root;
		stage = (Stage) buttonL.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("compravenditaGiornali.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei giornali");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
	}

	@FXML
	private void getListaRiviste() throws IOException {
		vis.getIstance().setIsSearch(false);
		vis.getIstance().setTypeAsMagazine();
		Stage stage;
		Parent root;
		stage = (Stage) buttonL.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("compravenditaRivista.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo delle riviste");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
	}

	@FXML
	private void getListaLibri() throws IOException {
		vis.getIstance().setIsSearch(false);
		vis.getIstance().setTypeAsBook();
		Stage stage;
		Parent root;
		stage = (Stage) buttonL.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("compravenditaLibri.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei libri");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

	}

	@FXML
	private void profile() throws IOException {
		// specificare controller logico
		/*
		 */
		Stage stage;
		 
		Parent root;
		stage = (Stage) buttonL.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("visualizzaProfilo.fxml"));
		stage.setTitle("Benvenuto nel tuo profilo qui puoi visualizzare le tue informazioni");
	
		
		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
		
		//System.out.println("Sto nel terzo caso d'urso lode");
	}

	// Usaiamo la Reflection!! no! 
	@FXML
	private void logout() throws IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		
		if (ControllerHomePageAfterLogin.logout())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonLogout.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);

			stage.show();
		}
		else
		{
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Errore Logout");// line 2
			alert.setHeaderText("Errore Logout");// line 3
			alert.setContentText("!--Errore Logout--!");// line 4
			alert.showAndWait(); // line 5

		}
	
	}
	
	@FXML
	private void cerca() throws IOException {
		vis.getIstance().setIsSearch(true);

		Stage stage;
		Parent root;
		stage = (Stage) buttonC.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ricercaPerTipo.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

	}
	
	@FXML
	private void gestioneRaccolta() throws IOException
	{
		
		Stage stage;
		Parent root;
		stage = (Stage) buttonR.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("RaccoltaPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
	}


}
