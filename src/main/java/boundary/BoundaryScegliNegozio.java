package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.ControllerScegliNegozio;
import controller.SingeltonSystemState;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entity.Negozio;

public class BoundaryScegliNegozio implements Initializable {
	
	@FXML
	private Label labelL;
	@FXML
	private Pane pane;
	@FXML
	private RadioButton radio1;
	@FXML
	private RadioButton radio2;
	@FXML
	private RadioButton radio3;
	@FXML
	private RadioButton radio4;
	@FXML
	private Button buttonV;
	
	private ControllerScegliNegozio CSN;
	private ObservableList<Negozio> listOfNegozi;
	private String alertTitle = "Ordine ricevuto!";
	private String alertHeaderTexr = "Il negozio che hai selezionato ha ricevuto il tuo ordine. \n Presentati dopo 3 giorni lavorativi per ritirare il tuo acquisto";
	private String alertContentText = "Ricordati di presentarti con le credenziali con le quali accedi al sito e ti verrà consegnato il tuo ordine!\n";
	private Boolean r1IsSelected = false;
	private Boolean r2IsSelected = false;
	private Boolean r3IsSelected = false;
	private Boolean r4IsSelected = false;
	private Boolean statusA = false ;
	private Boolean statusB = false ;
	public BoundaryScegliNegozio()
	{
		CSN = new ControllerScegliNegozio();
	}
	
	@FXML
	private void verifica() throws SQLException, IOException 
	{
		listOfNegozi=CSN.getNegozi();
		r1IsSelected = radio1.isSelected();
		r2IsSelected = radio2.isSelected();
		r3IsSelected = radio3.isSelected();
		r4IsSelected = radio4.isSelected();

		if(r1IsSelected)
		{
			statusA = listOfNegozi.get(0).getIsOpen();
			statusB =  listOfNegozi.get(0).getIsValid();
			
		
			if( statusA && statusB)
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle(alertTitle);
				alert.setHeaderText(alertHeaderTexr);
				alert.setContentText(alertContentText);
				Optional<ButtonType> result = alert.showAndWait();
				
		        if ((result.isPresent()) && (result.get() == ButtonType.OK))
		        	
		        {
		            if(SingeltonSystemState.getIstance().getIsLogged())	
					{
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePageAfterLogin.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            }
		            else {
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            	}
				
			
		        			
		        }
		        else
				{
				
					Alert alertE = new Alert(AlertType.WARNING);
					alertE.setTitle("Negozio chiuso o non disponibile per il ritiro");
					alertE.setHeaderText("Il negozio seleziopnato non è al momento pronto per questo tipo di operazioni");
					alertE.setContentText("torna indietro e seleziona un'altro negozio fra quelli che ti vengono mostrati ! ");
				
				}
			}
			
			
		} // qui si chiude check button1
		else if(r2IsSelected)
		{
			if(listOfNegozi.get(1).getIsOpen() && listOfNegozi.get(1).getIsValid())
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle(alertTitle);
				alert.setHeaderText(alertHeaderTexr);
				alert.setContentText(alertContentText);
				Optional<ButtonType> result = alert.showAndWait();
				
		        if ((result.isPresent()) && (result.get() == ButtonType.OK))
		        	
		        {
		            if(SingeltonSystemState.getIstance().getIsLogged())	
					{
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePageAfterLogin.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            }
		            else {
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            	}
				
				
		            //Open another window on clicking the OK button

		        			
		        }
		        else
				{
				
					Alert alertE = new Alert(AlertType.WARNING);
					alertE.setTitle("Negozio chiuso o non disponibile per il ritiro");
					alertE.setHeaderText("Il negozio seleziopnato non è al momento pronto per questo tipo di operazioni");
					alertE.setContentText("torna indietro e seleziona un'altro negozio fra quelli che ti vengono mostrati ! ");
				
				}
			}
			
			
		} // qui si chiude check button2
		
		if(r3IsSelected)
		{
			if(listOfNegozi.get(2).getIsOpen() && listOfNegozi.get(2).getIsValid())
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle(alertTitle);
				alert.setHeaderText(alertHeaderTexr);
				alert.setContentText(alertContentText);
				Optional<ButtonType> result = alert.showAndWait();
				
		        if ((result.isPresent()) && (result.get() == ButtonType.OK))
		        	
		        {
		            System.out.println("ALL OK..!");
		            if(SingeltonSystemState.getIstance().getIsLogged())	
					{
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePageAfterLogin.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            }
		            else {
		            	System.out.println("Sto in else");
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            	}
				
				
		            //Open another window on clicking the OK button

		        			
		        }
		        else
				{
				
					Alert alertE = new Alert(AlertType.WARNING);
					alertE.setTitle("Negozio chiuso o non disponibile per il ritiro");
					alertE.setHeaderText("Il negozio seleziopnato non è al momento pronto per questo tipo di operazioni");
					alertE.setContentText("torna indietro e seleziona un'altro negozio fra quelli che ti vengono mostrati ! ");
				
				}
			}
			
			
		} // qui si chiude check button1
		
		else if(r4IsSelected)
		{
			if(listOfNegozi.get(3).getIsOpen() && listOfNegozi.get(3).getIsValid())
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle(alertTitle);
				alert.setHeaderText(alertHeaderTexr);
				alert.setContentText(alertContentText);
				Optional<ButtonType> result = alert.showAndWait();
				
		        if ((result.isPresent()) && (result.get() == ButtonType.OK))
		        	
		        {
		            System.out.println("ALL OK..!");
		            if(SingeltonSystemState.getIstance().getIsLogged())	
					{
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePageAfterLogin.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            }
		            else {
		            	System.out.println("Sto in else");
		            	Stage stage;
		                Parent root;
		                stage = (Stage) buttonV.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
		                root = loader.load();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		            	}
				
				
		            //Open another window on clicking the OK button

		        			
		        }
		        else
				{
				
					Alert alertE = new Alert(AlertType.WARNING);
					alertE.setTitle("Negozio chiuso o non disponibile per il ritiro");
					alertE.setHeaderText("Il negozio seleziopnato non è al momento pronto per questo tipo di operazioni");
					alertE.setContentText("torna indietro e seleziona un'altro negozio fra quelli che ti vengono mostrati ! ");
				
				}
			}
			
			
		} // qui si chiude check button1

						
	}
	
				
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			radio1.setText(CSN.getNegozi().get(0).getNome());
			radio2.setText(CSN.getNegozi().get(1).getNome());
			radio3.setText(CSN.getNegozi().get(2).getNome());
			radio4.setText(CSN.getNegozi().get(3).getNome());
		} catch (SQLException e) {
		 
			
		}
	}
}
