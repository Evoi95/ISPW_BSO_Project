package boundary;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.ControllerAddRivistaPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class BoundaryAddRivistaPage implements Initializable{
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private GridPane grid;
	@FXML
	private Label labelT;
	@FXML
	private Label labelTipo;
	@FXML
	private Label labelA;
	@FXML
	private Label labelLingua;
	@FXML
	private Label labelE;
	@FXML
	private Label labelDesc;
	@FXML
	private Label labelData;
	@FXML
	private Label labelDisp;
	@FXML
	private Label labelP;
	@FXML
	private Label labelCopie;
	@FXML
	private TextField titoloTF;
	@FXML
	private TextField tipologiaTF;
	@FXML
	private TextField autoreTF;
	@FXML
	private TextField linguaTF;
	@FXML
	private TextField editoreTF;
	@FXML
	private TextArea descTA;
	@FXML
	private DatePicker datePick;
	@FXML
	private CheckBox dispCheck;
	@FXML
	private TextField prezzoTF;
	@FXML
	private TextField copieTF;
	@FXML
	private Button buttonAdd;
	@FXML
	private Button buttonI;
	private ControllerAddRivistaPage cARP;
	
	@FXML
	private void aggiungi()
	{
		int dispo;
		String t=titoloTF.getText();
		String tipologia=tipologiaTF.getText();
		String a=autoreTF.getText();
		String l=linguaTF.getText();
		String ed=editoreTF.getText();
		String desc=descTA.getText();
		LocalDate data=datePick.getValue();

		//String c=(String) categoriaList.getSelectionModel().getSelectedItem();

		boolean disp=dispCheck.isPressed();
		
		
		if(disp==true)
		{
			dispo=1;
			//disponibile
		}
		else {
			dispo=0;
		}
		float prezzo=Float.parseFloat(prezzoTF.getText());
		int copie=Integer.parseInt(copieTF.getText());
		
		
		boolean esito=cARP.checkData(t,tipologia,a,l,ed,desc,data,dispo,prezzo,copie);

	}
	@FXML 
	private void torna() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonI.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("rivistaPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 cARP=new ControllerAddRivistaPage ();
;

		
	}
	
	
	
	
	
	

}
