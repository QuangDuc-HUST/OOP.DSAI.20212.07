package dsai.group07.force.controller;

import dsai.group07.force.model.Simulation;
import dsai.group07.force.model.object.Cylinder;
import dsai.group07.force.model.vector.FrictionForce;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class ForcePanelController {

	private Simulation simul;

	@FXML
	private StackPane forcePanel;

	@FXML
	private TextField forceTextField;

	@FXML
	public void initialize() {
		forceTextField.setDisable(true);
	}

	public void setSimul(Simulation simul) {
		this.simul = simul;

		// UPDATE: Add event to link to calculate Acc after aForce change when user
		// enter or unfocusing
		// UPDATE: Disable and Set 0 when the object is null, enable when object is not
		// null, auto start the simulation when value != 0
		// TODO: Unfocus forceTextField when click outside the textfield

		
		this.simul.getaForce().valueProperty().addListener(
				(observable, oldValue, newValue) -> 
				{
					
					this.simul.getObj().updateAcc(this.simul.getaForce());
          fForceListener();
			    netForceListener();
					
					//TODO: update in general
					if (simul.getObj() instanceof Cylinder) {
						// BUG: .......
						try {
							((Cylinder)this.simul.getObj()).updateAngAcc(this.simul.getaForce());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
					if (!this.simul.getIsStart() && newValue.doubleValue() != 0.0)  // newValue.doubleValue() != 0: Prevent auto start when force == 0
					{ 		
						this.simul.start();
					}
					else if (this.simul.getIsPause() && this.simul.getIsStart() ) {
						this.simul.conti();
					}
					
					
					forceTextField.getParent().requestFocus();
					
				}
					);
		
		this.simul.objProperty().addListener(
				(observable, oldValue, newValue) -> {
					if(newValue == null) {
						forceTextField.setDisable(true);
						forceTextField.setText("0");
					}
					else {
            forceTextField.setDisable(false);
            this.simul.setObject(newValue);
            fForceListener();
            netForceListener();
					}
				}
				);
		
	
		// Unfocus forceTextField
		forceTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				this.simul.getaForce().setValue(Integer.parseInt(forceTextField.getText()));
				System.out.println(
						"Current Force Value " + this.simul.getaForce().getValue() + " from Unfocus force Text");
			}
		});

	}

	@FXML
	void forceTextFieldOnAction(ActionEvent event) {
		this.simul.getaForce().setValue(Integer.parseInt(forceTextField.getText()));
		System.out.println(
				"Current Applied Force Value " + this.simul.getaForce().getValue() + " from On Action force Text");
	}

	public void fForceListener() {
		try {
			this.simul.getaForce().valueProperty().addListener(observable -> {
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

	public void netForceListener() {
		this.simul.getaForce().valueProperty().addListener(observable -> {
			try {
				this.simul.updateNetForce();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		this.simul.getfForce().valueProperty().addListener(observable -> {
			try {
				this.simul.updateNetForce();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
