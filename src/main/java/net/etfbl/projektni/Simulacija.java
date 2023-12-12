package net.etfbl.projektni;

import java.io.*;
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

	public static GUI GuiInstance;

	public static File kaznjeneOsobe = new File("src" + File.separator + "main" + File.separator + "java" + File.separator + "net" + File.separator + "etfbl" + File.separator + "output", "kaznjene_osobe.ser");
	public static File tekstualnaDokumentacija = new File("src" + File.separator + "main" + File.separator + "java" + File.separator + "net" + File.separator + "etfbl" + File.separator + "output", "tekstualna_dokumentacija.txt");
	public static File terminali = new File("src" + File.separator + "main" + File.separator + "java" + File.separator + "net" + File.separator + "etfbl" + File.separator + "output", "RadTerminala.txt");
	public static FileOutputStream fos1;
	public static FileWriter fw;

	public static void main(String[] args) {
		createFiles();
		kreirajRed();
		launch(args);
	}

	public static void StartSimulation() {
		for(int i=red.size()-1;i>=0;i--){
			Vozilo v = (Vozilo)red.get(i);
			v.start();
		}
	}

	public static void createFiles() { // TODO: uradi bolje
		File parent1 = kaznjeneOsobe.getParentFile();
		File parent2 = tekstualnaDokumentacija.getParentFile();
		File parent3 = terminali.getParentFile();

		try {
			fos1 = new FileOutputStream(kaznjeneOsobe, false);
			fos1.close();
			fw = new FileWriter(tekstualnaDokumentacija, false);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!parent1.exists())
			parent1.mkdirs();
		if(!parent2.exists())
			parent2.mkdirs();
		if(!parent3.exists())
			parent3.mkdirs();
		try {
			kaznjeneOsobe.createNewFile();
			tekstualnaDokumentacija.createNewFile();
			terminali.createNewFile();
			try {
				FileWriter fw1 = new FileWriter(terminali);
				fw1.write("1#1#1#1#1");
				fw1.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			red.add(v);
			i++;
		}
		for(int j=0;j<red.size();j++)
			System.out.println(red.get(j));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String pathToFXML = "src" + File.separator + "main" + File.separator + "java" + File.separator + "net" + File.separator + "etfbl" + File.separator + "gui" + File.separator + "guiRadnaScena.fxml";
		FXMLLoader loader = new FXMLLoader(new File(pathToFXML).toURI().toURL());
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Granicni prelaz");
		primaryStage.show();

		GuiInstance = loader.getController();
	}
}
