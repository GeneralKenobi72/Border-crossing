package net.etfbl.gui;

import java.util.logging.*;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import javafx.animation.*;
import javafx.geometry.*;
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
    private GridPane GridIzdvojenaVozila;

	private static int izdvojenaVozilaCol = 0;
	private static int izdvojenaVozilaRow = 0;

    @FXML
    private VBox PrvihPetVozila;

	@FXML
    private MenuItem MenuQuit;

    @FXML
    private Button buttonStart;

	@FXML
	private Button PauseButton;

	@FXML 
	private Label vrijemeLabel;

	private Instant startTime;
	private Instant pauseStart;
	private Duration totalPauseTime = Duration.ZERO;

	public void initialize() {
		vrijemeLabel.setText("0.00");
		PauseButton.setStyle("-fx-text-fill: green;");
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
			Tooltip tooltip = new Tooltip();
			label.setOnMousePressed(event -> {
				tooltip.setText(v.getMessage());
				Tooltip.install(label, tooltip);
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
			Tooltip tooltip = new Tooltip();
			label.setOnMousePressed(event -> {
				tooltip.setText(v.getMessage());
				Tooltip.install(label, tooltip);
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
			Tooltip tooltip = new Tooltip();
			label.setOnMousePressed(event -> {
				tooltip.setText(v.getMessage());
				Tooltip.install(label, tooltip);
				tooltip.show(label,event.getScreenX(), event.getScreenY());
			});
			label.setOnMouseReleased(event -> {
				tooltip.hide();
			});
			return label;
		}
	}
		
	public void GUIMoveCarTerminal(int row1, int col1, int row2, int col2) { //Prebacuje vozilo sa policijskog na carinski terminal
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

	public void removeFromTerminalGUI(int row, int col, boolean dodajUIzdvojena) {
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
		if(dodajUIzdvojena == true)
			dodajUIzvojenaVozilaGUI(labelToRemove);
	}

	public void dodajUIzvojenaVozilaGUI(Node labelToAdd) {
		for(Node node : GridTerminali.getChildren()) {
			if(GridPane.getRowIndex(node) == null || GridPane.getColumnIndex(node) == null)
				continue;
			if(node == labelToAdd)
				return;
		}
			
		if(izdvojenaVozilaCol != 10)
			GridIzdvojenaVozila.add(labelToAdd, izdvojenaVozilaCol++, izdvojenaVozilaRow);
		else {
			izdvojenaVozilaCol = 0;
			izdvojenaVozilaRow++;
			GridIzdvojenaVozila.add(labelToAdd, izdvojenaVozilaCol, izdvojenaVozilaRow);
		}
	}
	@FXML
	void setPause(MouseEvent event) {
		if(!Simulacija.pause) {
			pauseStart = Instant.now();
			PauseButton.setStyle("-fx-text-fill: red;");
		}
		else {
			totalPauseTime = totalPauseTime.plus(Duration.between(pauseStart, Instant.now()));
			PauseButton.setStyle("-fx-text-fill: green;");
		}

		Simulacija.pause = !(Simulacija.pause);
	}

	private void updateElapsedTime() {
		Instant currentTime = Instant.now();

		if(Simulacija.pause || Simulacija.brojZavrsenihVozila == 50) {
			return;
		}
		Duration duration = Duration.between(startTime, currentTime).minus(totalPauseTime);
		long minutes = duration.toMinutes();
		long seconds = duration.minusMinutes(minutes).getSeconds();
		long millis = duration.toMillis() % 1000;
		vrijemeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, millis));
	}

	@FXML
	void MakeInvisible(MouseEvent event) {
		Platform.runLater( () -> {
			buttonStart.setVisible(false);
		});
		startTime = Instant.now();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				updateElapsedTime();
			}
		};
		timer.start();

		Simulacija.StartSimulation();
		new Thread(() -> {
			while(true) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(Simulacija.terminali));
					String linija = br.readLine();
					String[] stringovi = linija.split("#");
					if(stringovi[0].equals("0"))
						Simulacija.pt1.setRadi(false);
					else
						Simulacija.pt1.setRadi(true);
					if(stringovi[1].equals("0"))
						Simulacija.pt2.setRadi(false);
					else
						Simulacija.pt2.setRadi(true);
					if(stringovi[2].equals("0"))
						Simulacija.pt3.setRadi(false);
					else
						Simulacija.pt3.setRadi(true);
					if(stringovi[3].equals("0"))
						Simulacija.ct1.setRadi(false);
					else
						Simulacija.ct1.setRadi(true);
					if(stringovi[4].equals("0"))
						Simulacija.ct2.setRadi(false);
					else
						Simulacija.ct2.setRadi(true);
					br.close();
				} catch(IOException e) {
					Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				}
			}
		}).start();
	}
	@FXML
    void quitApplication(ActionEvent event) {
		Platform.exit();
    }
}
