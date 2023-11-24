package net.etfbl.gui;

import javafx.geometry.*;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import net.etfbl.vozila.*;
import net.etfbl.projektni.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.application.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GUI {

	@FXML
    public GridPane GridTerminali;

	@FXML
    private GridPane GridOstalaVozila;

    @FXML
    private VBox PrvihPetVozila;

	@FXML
    private MenuItem MenuQuit;

    @FXML
    private Button buttonStart;

	public void initialize() {
		int i=0;
		for(i=0;i<5;i++) {
			Label label = createLabel(Simulacija.red.get(i));
			PrvihPetVozila.getChildren().add(label);
		}

		int row = 0;
		int cols = 0;
		for(;i<50;i++) {
			Label label = createLabel(Simulacija.red.get(i));
			GridOstalaVozila.add(label,cols,row);
			cols++;
			if(cols == 10) {
				cols = 0;
				row++;
			}
		}	
		PrvihPetVozila.setAlignment(Pos.CENTER);
		GridTerminali.setAlignment(Pos.CENTER);
		GridOstalaVozila.setAlignment(Pos.CENTER);
	}

	public Label createLabel(Vozilo v) {
		if(v instanceof LicnoVozilo) {
			Label label = new Label("V");
			label.setStyle("-fx-background-color: #cccccc" + ";-fx-padding: 5px");
			label.setAlignment(javafx.geometry.Pos.CENTER);
	        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
	        label.setPrefHeight(40.0);
	        label.setPrefWidth(40.0);
			Tooltip tooltip = new Tooltip(v.getName());
			Tooltip.install(label, tooltip);
			label.setOnMousePressed(event -> {
				tooltip.show(label,event.getScreenX(), event.getScreenY());
			});
			label.setOnMouseReleased(event -> {
				tooltip.hide();
			});
			return label;
		}
		else if(v instanceof Kamion) {
			Label label = new Label("K");
			label.setStyle("-fx-background-color: #aa44ff" + ";-fx-padding: 5px");
			label.setAlignment(javafx.geometry.Pos.CENTER);
	        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
	        label.setPrefHeight(40.0);
	        label.setPrefWidth(40.0);
			Tooltip tooltip = new Tooltip(v.getName());
			Tooltip.install(label, tooltip);
			label.setOnMousePressed(event -> {
				tooltip.show(label,event.getScreenX(), event.getScreenY());
			});
			label.setOnMouseReleased(event -> {
				tooltip.hide();
			});
			return label;
		}
		else {
			Label label = new Label("A");
			label.setStyle("-fx-background-color: #ff88aa" + ";-fx-padding: 5px");
			label.setAlignment(javafx.geometry.Pos.CENTER);
	        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
	        label.setPrefHeight(40.0);
	        label.setPrefWidth(40.0);
			Tooltip tooltip = new Tooltip(v.getName()); Tooltip.install(label, tooltip); label.setOnMousePressed(event -> {
				tooltip.show(label,event.getScreenX(), event.getScreenY());
			});
			label.setOnMouseReleased(event -> {
				tooltip.hide();
			});
			return label;
		}
	}
		
	public void GUIMoveCarTerminal(int row1, int col1, int row2, int col2) {
		Node nodeToRemove = null;
		for (Node node : GridTerminali.getChildren()) {
	        Integer rowIndex = GridPane.getRowIndex(node);
	        Integer colIndex = GridPane.getColumnIndex(node);

	        if (rowIndex != null && colIndex != null && rowIndex == row1 && colIndex == col1) {
	            nodeToRemove = node;
	            break;
	    	}
	    }
		if (nodeToRemove != null) {
	        GridTerminali.getChildren().remove(nodeToRemove);
			GridPane.setConstraints(nodeToRemove, col2, row2);
	        GridTerminali.getChildren().add(nodeToRemove);
	    }
	}

	public void updateVBoxAfterCarMove(int row, int col) {
		Label labelToMove = (Label) PrvihPetVozila.getChildren().remove(0);
		GridTerminali.add(labelToMove,col,row);

		List<Node> remainingNodes = new ArrayList<>(PrvihPetVozila.getChildren());
		PrvihPetVozila.getChildren().clear();
		PrvihPetVozila.getChildren().addAll(remainingNodes);

		Node labelToRemove = null;
		for (Node node : GridOstalaVozila.getChildren()) {
		    if (GridPane.getRowIndex(node) == 0 && GridPane.getColumnIndex(node) == 0) {
		        labelToRemove = node;
		        break;
		    }
		}

		if(labelToRemove != null) {
			GridOstalaVozila.getChildren().remove(labelToRemove);
			PrvihPetVozila.getChildren().add(labelToRemove);

			for (Node node : GridOstalaVozila.getChildren()) {
		        Integer colIndex = GridPane.getColumnIndex(node);
		        Integer rowIndexInGrid = GridPane.getRowIndex(node);

				if(colIndex == 0 && rowIndexInGrid == 0)
					continue;

				if (colIndex != null && rowIndexInGrid != null) {
		            if (colIndex != 0) {
		                GridPane.setColumnIndex(node, colIndex - 1);
		            }
					else {
		                GridPane.setRowIndex(node, rowIndexInGrid - 1);
						GridPane.setColumnIndex(node, 9);
		            }
		        }
            }
		}
	}

	public void removeFromTerminalGUI(int row, int col) {
		Node labelToRemove = null;
		for (Node node : GridTerminali.getChildren()) {
			if(GridPane.getRowIndex(node) == null || GridPane.getColumnIndex(node) == null)
					continue;
		    if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
		        labelToRemove = node;
		        break;
		    }
		}
		if(labelToRemove != null) 
			GridTerminali.getChildren().remove(labelToRemove);
	}

	@FXML
	void MakeInvisible(MouseEvent event) {
		Platform.runLater( () -> {
			buttonStart.setVisible(false);
		});
		Simulacija.StartSimulation();
	}
	@FXML
    void quitApplication(ActionEvent event) {
		Platform.exit();
    }
}
