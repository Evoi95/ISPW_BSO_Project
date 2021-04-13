package boundary;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.ControllerAddGiornalePage;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class BoundaryAddGiornalePage implements Initializable  {
	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane;
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
	private Label disponibilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanentiL;
	@FXML
	private TextField titoloT;
	@FXML
	private TextField tipologiaT;
	@FXML
	private TextField editoreT;
	@FXML
	private TextField linguaT;
	@FXML
	private DatePicker dataP;
	@FXML
	private CheckBox disponibilitaC;
	@FXML
	private TextField prezzoT;
	@FXML
	private TextField copieRimanentiT;
	@FXML
	private Button buttonC;
	@FXML
	private Button buttonA;
	
	private ControllerAddGiornalePage cAGP;
	@FXML
	private void conferma()
	{
		String t=titoloT.getText();
		String tipo=tipologiaT.getText();
		String ed=editoreT.getText();
		String l=linguaT.getText();

		LocalDate d=dataP.getValue();
		boolean disp=disponibilitaC.isPressed();
		
		int dispo;
		
		if(disp==true)
		{
			dispo=1;
			//disponibile
		}
		else {
			dispo=0;
		}
		float prezzo=Float.parseFloat(prezzoT.getText());
		int copie=Integer.parseInt(copieRimanentiT.getText());
		
		boolean esito=cAGP.checkData(t,tipo,ed,l,d,dispo,prezzo,copie);
		
		System.out.println("Esito : "+esito);
		
		

		
	}
	@FXML
	private void annulla() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("giornalePage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	
	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				cAGP=new ControllerAddGiornalePage();

	}
	
	
	








}
