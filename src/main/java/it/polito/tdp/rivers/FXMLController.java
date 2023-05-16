/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doCompilaCampi(ActionEvent event) {
    	this.txtStartDate.clear();
    	this.txtEndDate.clear();
    	this.txtNumMeasurements.clear();
    	this.txtFMed.clear();
    	
    	River fiume = this.boxRiver.getValue();
    	model.setRiverFlows(fiume);
    	
    	LocalDate primaOsservazione = fiume.getFlows().getFirst().getDay();
    	LocalDate ultimaOsservazione = fiume.getFlows().getLast().getDay();
    	
    	int misurazioni = fiume.getFlows().size();
    	float flussoMedio = (float) fiume.getFlowAvg();
    	
    	this.txtStartDate.setText(primaOsservazione.toString());
    	this.txtEndDate.setText(ultimaOsservazione.toString());
    	this.txtNumMeasurements.setText(Integer.toString(misurazioni));
    	this.txtFMed.setText(Float.toString(flussoMedio));

    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	//pulisci tutto prima di tutto, poi riempi alla fine
    	this.txtResult.clear();
    	//controlli su k
    	String kImmesso = this.txtK.getText();
    	try {
    		float fattore = Float.parseFloat(kImmesso);    
        	River fiume = this.boxRiver.getValue();
        	
        	int fail = model.giorniFail(fiume, fattore);
        	float mediaRiempimento = model.riempimentoMedio(fiume, fattore);
        	
        	this.txtResult.setText("Numero di giorni \"critici\": " + fail + "\n");
        	this.txtResult.appendText("Occupazione media del bacino: " + mediaRiempimento + "\n");
        	this.txtResult.appendText("SIMULAZIONE TERMINATA!");
        	
    	}catch(NullPointerException npe) {
    		this.txtResult.appendText("Scegliere un fiume per cui compiere la simulazione.");
    		
    	}catch(NumberFormatException nfe) {
    		this.txtResult.appendText("Attenzione! Il valore per il fattore di scala scelto non e' valido.");
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	//riempiamo la combo box
    	List<River> fiumi = model.getRivers();
    	boxRiver.getItems().setAll(fiumi);
    }
}
