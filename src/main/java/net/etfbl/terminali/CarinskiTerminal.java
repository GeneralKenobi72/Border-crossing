package net.etfbl.terminali;

import java.util.logging.*;
import java.io.*;
import javafx.application.Platform;
import javafx.scene.*;
import net.etfbl.vozila.*;
import net.etfbl.gui.GUI;
import net.etfbl.projektni.*;

public class CarinskiTerminal extends Terminal {
	public CarinskiTerminal(String name) {
		super(name);
	}
	@Override
	public int obradiVozilo(Kamion v) {
		try {
			Vozilo.checkPause();
			Thread.sleep(200);
			if((v.getPotrebnaCarinskaDokumentacija() == true) && (v.getStvarnaMasaTereta() != v.getDeklarisanaMasaTereta())) {
				try {
					FileWriter fw = new FileWriter(Simulacija.tekstualnaDokumentacija, true);
					fw.write("#" + v.getName());
					fw.close();
				} catch(IOException e) {
					Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				}
				System.out.println("Obradjen na carinskom terminalu: " + v);
				return v.getStvarnaMasaTereta() - v.getDeklarisanaMasaTereta();
			}
			System.out.println("Obradjen na carinskom terminalu: " + v);
		} catch (Exception e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return 0;
	}
	@Override
	public int obradiVozilo(Autobus v) {
		int brojIzbacenih = 0;
		try {
			for(int i=0;i<v.getBrojPutnika();i++) {
				Thread.sleep(100);
				Vozilo.checkPause();
				Kofer k = v.getKofer(i);
				if(k != null && k.getImaNedozvoljeneStvari()) {
					try {
						brojIzbacenih++;
						FileWriter fw = new FileWriter(Simulacija.tekstualnaDokumentacija, true);
						fw.write("#" + v.getName() + "-" + k.getPripadaPutniku().getName());
						fw.close();
					} catch (IOException e) {
						Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
					}
				}
			}
			System.out.println("Obradjen na carinskom terminalu: " + v);
		} catch (Exception e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return brojIzbacenih;
	}
	@Override
	public int obradiVozilo(LicnoVozilo v) {
		try {
			Vozilo.checkPause();
			Thread.sleep(2000);
			Vozilo.checkPause();
			System.out.println("Obradjen na carinskom terminalu: " + v);
		} catch (Exception e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return 0;
	}
}
