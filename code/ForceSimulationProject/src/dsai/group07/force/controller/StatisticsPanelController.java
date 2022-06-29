package dsai.group07.force.controller;

import dsai.group07.force.model.Simulation;
import dsai.group07.force.model.vector.HorizontalVector;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticsPanelController {
	
	private Simulation simul;
	

    @FXML
    private Label posLabel;

    @FXML
    private Label accLabel;

    @FXML
    private Label velLabel;

    @FXML
    private Label aForceLabel;

    @FXML
    private Label fForceLabel;

    @FXML
    private Label sumForceLabel;
    
    
	
	@FXML
   	public void initialize()  {
		 accLabel.setText("Current Accelerate: 0.00 m/s^2");
		 velLabel.setText("Current Velocity : 0.00 m/s");
		 posLabel.setText("Current Position : 0.00 m");
		 aForceLabel.setText("Applied Force: 0 N");
		 fForceLabel.setText("Friction Force: 0 N");
		 sumForceLabel.setText("Net Force: 0 N");
	}
	
	public void setSimul(Simulation simul) {
		this.simul = simul;
		
		this.simul.sysVelProperty().addListener(
				(observable, oldValue, newValue) -> 
				{
					velLabel.textProperty().bind(newValue.valueProperty().asString("Current Velocity : %.2f m/s"));
					System.out.println(observable);
				});
		
		this.simul.sysAccProperty().addListener(
				(observable, oldValue, newValue) -> {
					accLabel.textProperty().bind(newValue.valueProperty().asString("Current Accelerate : %.2f m/s^2;"));
				});
		
		this.simul.objProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.simul.setObject(newValue);
				ObservableStringValue posString = Bindings.createStringBinding(() -> 
				"Current Position : " + this.simul.getObj().getPos() + " m", this.simul.getObj().posProperty());
				posLabel.textProperty().bind(posString);
			} 
		});

		ObservableStringValue aForceString = Bindings.createStringBinding(() -> 
		"Current Applied Force : " + this.simul.getaForce().getValue() + " N", this.simul.getaForce().valueProperty());
		aForceLabel.textProperty().bind(aForceString);
		
		ObservableStringValue fForceString = Bindings.createStringBinding(() -> 
		"Current Friction Force : " + this.simul.getfForce().getValue() + " N", this.simul.getfForce().valueProperty());
		fForceLabel.textProperty().bind(fForceString);
		
		//System.out.println(this.simul.getNetForce().getValue());
		ObservableStringValue netForceString = Bindings.createStringBinding(() -> 
		"Current Net Force : " + this.simul.getNetForce().getValue() + " N", this.simul.getNetForce().valueProperty());
		sumForceLabel.textProperty().bind(netForceString);
	}
}
