package dsai.group07.force.controller;

import dsai.group07.force.model.Simulation;
import dsai.group07.force.model.vector.FrictionForce;

import java.text.NumberFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SurfacePanelController {
	
	private Simulation simul;
    
    @FXML
    private TextField staticCoefTextField;

    @FXML
    private TextField kineticCoefTextField;

    @FXML 
    private Slider staticCoefSlider;

    @FXML
    private Slider kineticCoefSlider;

    @FXML
    public void initialize() {
        staticCoefTextField.textProperty().bindBidirectional(
            staticCoefSlider.valueProperty(), NumberFormat.getNumberInstance());

        kineticCoefTextField.textProperty().bindBidirectional(
            kineticCoefSlider.valueProperty(), NumberFormat.getNumberInstance());
    }

	public void setSimul(Simulation simul) {
		this.simul = simul;
		
        staticCoefTextField.textProperty().bindBidirectional(
                simul.getSur().staCoefProperty(), NumberFormat.getNumberInstance());

        kineticCoefTextField.textProperty().bindBidirectional(
            simul.getSur().kiCoefProperty(), NumberFormat.getNumberInstance());

        staticCoefSlider.valueProperty().bindBidirectional(simul.getSur().staCoefProperty());
        kineticCoefSlider.valueProperty().bindBidirectional(simul.getSur().kiCoefProperty());
        
        staticCoefTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                simul.getSur().setStaCoef(Double.parseDouble(newValue));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(e.getMessage());
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        });

        kineticCoefTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                simul.getSur().setKiCoef(Double.parseDouble(newValue));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(e.getMessage());
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
            }
        }); 
        
       surfaceListener();
        
	}
	
	public void surfaceListener() {
		try {
			this.simul.getSur().staCoefProperty().addListener((observable, oldValue, newValue) -> {
				try {
					System.out.println(this.simul.getaForce().getValue());
					((FrictionForce) this.simul.getfForce()).updateFrictionForce();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			this.simul.getSur().kiCoefProperty().addListener((observable, oldValue, newValue) -> {
				try {
					((FrictionForce) this.simul.getfForce()).updateFrictionForce();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
