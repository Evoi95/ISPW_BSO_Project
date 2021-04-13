package controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import com.itextpdf.text.DocumentException;

import entity.factorybook.Libro;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControllerDownload {
	private String nrOrdine;
	private static Libro l;

	public void scaricaLibro() throws FileNotFoundException, DocumentException {
		
		l.scarica();
		l.leggi();
	}

	public void annullaOrdine() {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Annulla ordine");
		a.setContentText("Stai annullando l'ordine");
		a.setContentText("nr ordine in annullamento " + this.nrOrdine);

		a.setHeaderText(null);
		a.showAndWait();
	}

	public ControllerDownload() {
		this.nrOrdine = UUID.randomUUID().toString();
		l = new Libro();
	}

}
