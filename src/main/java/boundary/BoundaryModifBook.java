package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.ControllerModifBook;
import controller.SingeltonSystemState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BoundaryModifBook implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private GridPane gridpane ;
	@FXML
	private TextField titoloT;
	@FXML 
	private TextField numeroPagineT;
	@FXML
	private TextField codeIsbnT;
	@FXML
	private TextField editoreT;
	@FXML
	private TextField autoreT;
	@FXML
	private TextField linguaT;
	@FXML
	private TextField categoriaTF ;
	@FXML
	private DatePicker dataP;
	@FXML
	private TextField recensioneT;
	@FXML 
	private TextArea descrizioneA;
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
	private Label disponibilitaL;
	@FXML
	private Label prezzoL;
	@FXML
	private Label copieRimanentiL;
	
	@FXML
	private Label labelT;
	@FXML
	private Label labelNP;
	@FXML
	private Label labelCod;
	@FXML
	private Label labelE;
	@FXML
	private Label labelA;
	@FXML
	private Label labelL;
	@FXML
	private Label labelCat;
	@FXML
	private Label labelD;
	@FXML
	private Label labelR;
	@FXML
	private Label labelDesc;
	@FXML
	private Label labelDisp;
	@FXML
	private Label labelP;
	@FXML
	private Label labelCopie;
	
	
	
	
	
	
	
	
	
	
	
	
	
	private ControllerModifBook cMB;
	private SingeltonSystemState vis= SingeltonSystemState.getIstance();
	
	//private ObservableList<String> items = FXCollections.observableArrayList();

	@FXML
	private void aggiorna() throws SQLException
	{
		//cMB.getLibriById(vis.getIstance().getId());
		
		
		String t=titoloT.getText();
		int np=Integer.parseInt(numeroPagineT.getText());
		String cod=codeIsbnT.getText();
		String ed=editoreT.getText();
		String a=autoreT.getText();
		String l=linguaT.getText();
		String c=categoriaTF.getText();
		LocalDate d=dataP.getValue();
		String r=recensioneT.getText();
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
		
		

		cMB.checkData(t,np,cod,ed,a,l,c,d,r,dispo,prezzo,copie,vis.getIstance().getId());
		
		
	}
		
		
	@FXML
	private void annulla() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonA.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("bookPage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* settare valori textArea*/
		cMB=new ControllerModifBook();
		
		try {
			labelT.setText(cMB.getLibriById(vis.getIstance().getId()).get(0).getTitolo());
			labelNP.setText(""+cMB.getLibriById(vis.getIstance().getId()).get(0).getNumPag());
			labelCod.setText(cMB.getLibriById(vis.getIstance().getId()).get(0).getCodIsbn());
			labelE.setText(cMB.getLibriById(vis.getIstance().getId()).get(0).getEditore());
			labelA.setText(cMB.getLibriById(vis.getIstance().getId()).get(0).getAutore());
			labelL.setText(cMB.getLibriById(vis.getIstance().getId()).get(0).getLingua());
			labelCat.setText(""+cMB.getLibriById(vis.getIstance().getId()).get(0).getCategoria());
			labelR.setText(cMB.getLibriById(vis.getIstance().getId()).get(0).getRecensione());
			labelP.setText(""+cMB.getLibriById(vis.getIstance().getId()).get(0).getPrezzo());
			labelCopie.setText(""+cMB.getLibriById(vis.getIstance().getId()).get(0).getCopieRim());





		} catch (SQLException e) {
		 
			
		}

	}

	
			


}
