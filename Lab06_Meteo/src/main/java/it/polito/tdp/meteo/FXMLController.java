/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxMese"
    private ChoiceBox<String> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnUmidita"
    private Button btnUmidita; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaSequenza(ActionEvent event) {

    	txtResult.clear();
    	String mese = boxMese.getValue();
    	
    	if(mese == null) {
    		txtResult.appendText("Scegliere un mese prima di calcolare la sequenza ottimale");
    		return;
    	}
    	
    	txtResult.appendText(model.trovaSequenza(mese));
    	txtResult.appendText("\nCosto totale: "+model.getCostoMin());
    	
    }

    @FXML
    void doCalcolaUmidita(ActionEvent event) {
    	
    	txtResult.clear();
    	String mese = boxMese.getValue();
    	
    	if(mese == null) {
    		txtResult.appendText("Scegliere un mese prima di calcolare l'umidita'");
    		return;
    	}
    	
    	txtResult.appendText(model.getUmiditaMedia(mese));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        boxMese.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}

