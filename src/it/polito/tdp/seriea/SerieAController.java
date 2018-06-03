/**
 * Sample Skeleton for 'SerieA.fxml' Controller Class
 */

package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.SquadraStagionePunti;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SerieAController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxSquadra"
    private ChoiceBox<Team> boxSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="btnSelezionaSquadra"
    private Button btnSelezionaSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaAnnataOro"
    private Button btnTrovaAnnataOro; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaCamminoVirtuoso"
    private Button btnTrovaCamminoVirtuoso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void doSelezionaSquadra(ActionEvent event) {
    	txtResult.clear();
try {
	
	Team team=this.boxSquadra.getValue();
	if(team==null) {
		txtResult.setText("errore");
	return;
	}
	String j=model.ottieniStagioniePuntiSquadra(team);
	if(j==null || j.equals("")) {
		txtResult.setText("errore");
		return;	
	}
	
	txtResult.setText(j);
	model.creaGrafo();
	
}catch(RuntimeException rte) {
	
	
}
    	
    	
    	
    }

    @FXML
    void doTrovaAnnataOro(ActionEvent event) {
    	model.creaGrafo();
    	SquadraStagionePunti squadraStagionePunti=model.ottieniAnnataOro();
    	
    	txtResult.appendText("\nL'annata d'oro è stata nel: "+squadraStagionePunti.toString());
    	
    	
    	
    }

    @FXML
    void doTrovaCamminoVirtuoso(ActionEvent event) {
    	
    	txtResult.appendText("\nMiglior sequenza di punteggio: "+model.calcolaCamminoVirtuoso());
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSelezionaSquadra != null : "fx:id=\"btnSelezionaSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnTrovaAnnataOro != null : "fx:id=\"btnTrovaAnnataOro\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnTrovaCamminoVirtuoso != null : "fx:id=\"btnTrovaCamminoVirtuoso\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void etModel(Model model) {
		this.model=model;
		this.boxSquadra.getItems().addAll(model.ottieniSquadre());
		
	}
}
