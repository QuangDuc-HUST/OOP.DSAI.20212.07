package dsai.group07.force.controller;

import java.io.IOException;

import dsai.group07.force.model.Simulation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ControlPanelController {
	
	private Simulation simul;
	private ObjectPanelController objController;
	private StatisticsPanelController staController;
	private ForcePanelController forceController;
	private SurfacePanelController surfaceController;
	private StackPane topStackPane;
	private StackPane downStackPane;
//	private StackPane forcePanel;
	private Circle cir;
	private Rectangle rec;
	
	@FXML
	private GridPane controlPanelGridPane;
	
	
	@FXML
   	public void initialize()  {
		
    }
	
	public void init(Simulation simul, StackPane topStackPane, StackPane downStackPane) {
		setSimul(simul);
		setTopStackPane(topStackPane);
		setDownStackPane(downStackPane);
		showObjectPanel();
		showForcePanel();
		showSurfacePanel();
		showStatisticsPanel();
	}
	
	
	
	public void setTopStackPane(StackPane topStackPane) {
		this.topStackPane = topStackPane;
	}
	
	public void setDownStackPane(StackPane downStackPane) {
		this.downStackPane = downStackPane;
	}
	
	public void setSimul(Simulation simul) {
		this.simul = simul;
		
	}
	
	public ObjectPanelController getObjController() {
		return objController;
	}

	public GridPane getControlPanelGridPane() {
		return controlPanelGridPane;
	}

	private void showObjectPanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/dsai/group07/force/view/ObjectPanel.fxml"));
			GridPane ObjectPanel = (GridPane) loader.load();
			controlPanelGridPane.add(ObjectPanel, 0, 0);
		
			objController = loader.getController();
		
			objController.setSimul(simul);
			objController.setTopStackPane(topStackPane);
			objController.setDownStackPane(downStackPane);
		
			this.rec = objController.getRec();
			this.cir = objController.getCir();
		
		}
			catch(IOException e) {
				e.printStackTrace();
		}
	}
	
	private void showStatisticsPanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/dsai/group07/force/view/StatisticsPanel.fxml"));
			StackPane panel = (StackPane) loader.load();
		
			topStackPane.getChildren().add(panel);
		
			staController = loader.getController();
			staController.init(simul, this.rec, this.cir, this.topStackPane, this.downStackPane);
		}
			catch(IOException e) {
				e.printStackTrace();
		}
		
	}
	
	private void showForcePanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/dsai/group07/force/view/ForcePanel.fxml"));
			StackPane forcePanel = (StackPane) loader.load();
			
			controlPanelGridPane.add(forcePanel, 1, 0);
			
			forceController = loader.getController();
			
			forceController.setSimul(simul);
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showSurfacePanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/dsai/group07/force/view/SurfacePanel.fxml"));
			GridPane surfacePanel = (GridPane) loader.load();
			
			controlPanelGridPane.add(surfacePanel, 2, 0);
			surfacePanel.setAlignment(Pos.BOTTOM_CENTER);
			
			surfaceController = loader.getController();

			surfaceController.setSimul(simul);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
