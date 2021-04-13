package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import controller.ControllerDownload;
import controller.SingeltonSystemState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BoundaryDownload implements Initializable {
	@FXML
	private SplitPane split;
	@FXML
	private AnchorPane ap1;
	@FXML
	private Label header;
	@FXML
	private AnchorPane ap2;
	@FXML
	private ImageView image;
	@FXML
	private Button buttonI;
	@FXML
	private Button buttonA;

	private ControllerDownload CD;
	private SingeltonSystemState vis = SingeltonSystemState.getIstance() ;

	@FXML
	private void scarica() throws IOException, DocumentException {
		
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.setTitle("Download effettuato");
		a.setHeaderText("Premere ok per scaricarlo e leggerlo\n");
		Optional<ButtonType> result = a.showAndWait();
		
		
		 if ((result.isPresent()) && (result.get() == ButtonType.OK))
	        	
	        {
	            System.out.println("ALL OK..!");
	            if(vis.getIstance().getIsLogged())	
				{
	            	CD.scaricaLibro();
	            	 	
	            	
				}
	            else {
	            	CD.scaricaLibro();

	            }
	            Stage stage;
				Parent root;
				stage = (Stage) buttonA.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

	        }
	}

	@FXML
	private void pulisci() throws IOException {
		CD.annullaOrdine();
		if( vis.getIstance().getIsLogged()) 
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonA.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePageAfterLogin.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonA.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				CD = new ControllerDownload();

	}

}
