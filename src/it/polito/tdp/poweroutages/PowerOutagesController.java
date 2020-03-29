
	package it.polito.tdp.poweroutages;

	import java.net.URL;
	import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ChoiceBox;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;

	public class PowerOutagesController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ChoiceBox<Nerc> boxNerc;

	    @FXML
	    private TextField txtYears;

	    @FXML
	    private TextField txtHours;

	    @FXML
	    private Button btnAnalysis;

	    @FXML
	    private TextArea txtResult;

		private Model model;

	    @FXML
	    void doAnalysis(ActionEvent event) {
	    	Nerc nerc = boxNerc.getValue();
	    	try {
	    	int anni = Integer.parseInt(txtYears.getText());
	    	int durataMax = Integer.parseInt(txtHours.getText());
	    	
	    	txtResult.appendText(model.ricercaBlackOut(nerc, anni, durataMax).toString());
	    	}catch(NumberFormatException e) {
	    		txtResult.setText("inserire un anno e un ora corretti!!!!!!!!!");
	    	}
               
	    }

	    @FXML
	    void initialize() {
	        assert boxNerc != null : "fx:id=\"boxNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'PowerOutages.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
            
	    }

		public void setModel(Model model) {
			this.model=model;
			boxNerc.getItems().addAll(model.getNercList());
			
		}
		

	
	}

	
