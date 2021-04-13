package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerRicercaPerTipo;
import controller.SingeltonSystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class BoundaryRicercaPerTipo implements Initializable {
	
	@FXML
	private Pane pane;
	@FXML
	private Label labelC;
	@FXML
	private Button buttonL;
	@FXML
	private Button buttonG;
	@FXML
	private Button buttonR;
	@FXML
	private Button buttonB;
	
	private String title = "Benvenuto nella schermata del riepilogo ordine";
	private String pageFxml = "ricercaPage.fxml";
	private String errorTitle = "Errore!" ;
	private String errorHeaderText = "Errore nel caricamento della schermata ";
	private String errorContentText = "Riavvia il programma se l'errore persiste!";
	private ControllerRicercaPerTipo CRPT;
	
	
	
	@FXML
	private void torna() throws IOException
	{
		
		if(SingeltonSystemState.getIstance().getIsLogged()) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonB.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
			else
			{
				Stage stage;
				Parent root;
				stage = (Stage) buttonB.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
	}
	@FXML
	private void cercaL() throws IOException
	{
		SingeltonSystemState.getIstance().setTypeAsBook();

		if(CRPT.setRicercaL())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonL.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource(pageFxml));
			stage.setTitle(title);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(errorTitle);
			alert.setHeaderText(errorHeaderText);
			alert.setContentText(errorContentText);
			alert.showAndWait();
		}
	}
	@FXML
	private void cercaG() throws IOException
	{
		if(CRPT.setRicercaG())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonG.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource(pageFxml));
			stage.setTitle(title);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(errorTitle);
			alert.setHeaderText(errorHeaderText);
			alert.setContentText(errorContentText);
			alert.showAndWait();
		}
	}
	@FXML 
	private void cercaR() throws IOException
	{
		if(CRPT.setRicercaR())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonR.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource(pageFxml));
			stage.setTitle(title);

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(errorTitle);
			alert.setHeaderText(errorHeaderText);
			alert.setContentText(errorContentText);
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		CRPT = new  ControllerRicercaPerTipo();
		//singeltonSystemState vis=singeltonSystemState.getIstance();

	
	}
}
