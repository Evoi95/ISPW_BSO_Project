package boundary;

import java.io.IOException;

import controller.SingeltonSystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BoundaryHomePage {
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
	private Button buttonC;

	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	// private ControllerHomePageLibri cHPL;

	@FXML
	private void getListaGiornali() throws IOException {
		// stampa schermata giornali -> tabella;
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
		// stampa schermata riviste -> tabella;
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
	private void login() throws IOException {
		// specificare controller ogico
		Stage stage;
		Parent root;
		stage = (Stage) buttonL.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
		stage.setTitle("Benvenuto nella schermata del login");

		// Parent root = FXMLLoader.load(getClass().getResource("compravendita.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

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

}
