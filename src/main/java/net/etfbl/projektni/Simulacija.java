package net.etfbl.projektni;

import java.io.File;
import java.net.URL;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.etfbl.vozila.*;
import net.etfbl.gui.GUI;
import net.etfbl.terminali.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Simulacija extends Application{
	public static final int MAX_BROJ_PUTNIKA_KAMIONA = 3;
	public static final int MAX_BROJ_PUTNIKA_AUTOBUSA = 52;
	public static final int MAX_BROJ_PUTNIKA_LICNOG_VOZILA = 5;

	public static CarinskiTerminal ct1 = new CarinskiTerminal("ct1");
	public static CarinskiTerminal ct2 = new CarinskiTerminal("ct2");
	public static PolicijskiTerminal pt1 = new PolicijskiTerminal("pt1");
	public static PolicijskiTerminal pt2 = new PolicijskiTerminal("pt2");
	public static PolicijskiTerminal pt3 = new PolicijskiTerminal("pt3");

	public static final ArrayList<Vozilo> red = new ArrayList<>();
	private static ArrayList<Vozilo> listaVozila = new ArrayList<>();

	public static GUI GuiInstance;

	public static void main(String[] args) {
		kreirajRed();
		launch(args);
	}

	public static void StartSimulation() {
		for(int i=listaVozila.size()-1;i>=0;i--){
			Vozilo v = (Vozilo)red.get(i);
			v.start();
		}
	}

	public static void kreirajRed() {
		int i;
		Object[] rred = new Object[50];
		for(i=0;i<10;i++) {
			Kamion k = new Kamion();
			k.setName("K" + i);
			rred[i] = k;
		}
		for(;i<15;i++) {
			Autobus a = new Autobus();
			a.setName("A" + i);
			rred[i] = a;
		}
		for(;i<50;i++) {
			LicnoVozilo lv = new LicnoVozilo();
			lv.setName("LV" + i);
			rred[i] = lv;
		}
		List<Object> list = Arrays.asList(rred);
		Collections.shuffle(list);
		i = 0;
		for(Iterator<Object> iter = list.iterator();iter.hasNext();) {
			Vozilo v = (Vozilo)iter.next();
			v.setRedVozila(i);
			listaVozila.add(v);
			red.add(v);
			i++;
		}
		for(int j=0;j<red.size();j++)
			System.out.println(red.get(j));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(new File("src/main/java/net/etfbl/gui/guiRadnaScena.fxml").toURI().toURL());
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Granicni prelaz");
		primaryStage.show();

		GuiInstance = loader.getController();
	}
}
