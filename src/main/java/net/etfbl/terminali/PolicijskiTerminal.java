package net.etfbl.terminali;

import java.util.logging.*;
import net.etfbl.vozila.*;
import net.etfbl.projektni.*;
import java.io.*;

public class PolicijskiTerminal extends Terminal {
	public PolicijskiTerminal(String name) {
		super(name);
	}

	@Override
	public int obradiVozilo(Kamion v) {
		int brojPutnikaSaNeispravnimDokumentima = 0;
		try {
			for(int i=0;i<v.getBrojPutnika();i++) {
				Vozilo.checkPause();
				Thread.sleep(500);
				Putnik putnik = v.getPutnici().get(i);
				if(obradiPutnika(putnik) == true)
					brojPutnikaSaNeispravnimDokumentima++;
			}
			System.out.println("Obradjen na policijskom terminalu: " + v);
		} catch (Exception e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return brojPutnikaSaNeispravnimDokumentima;
	}

	@Override
	public int obradiVozilo(LicnoVozilo v) {
		int brojPutnikaSaNeispravnimDokumentima = 0;
		try {
			for(int i=0;i<v.getBrojPutnika();i++) {
				Thread.sleep(500);
				Vozilo.checkPause();
				Putnik putnik = v.getPutnici().get(i);
				if(obradiPutnika(putnik) == true)
					brojPutnikaSaNeispravnimDokumentima++;
			}
			System.out.println("Obradjen na policijskom terminalu: " + v);
		} catch (Exception e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return brojPutnikaSaNeispravnimDokumentima;
	}

	@Override
	public int obradiVozilo(Autobus v) {
		int brojPutnikaSaNeispravnimDokumentima = 0;
		try {
			for(int i=0;i<v.getBrojPutnika();i++) {
				Thread.sleep(500);
				Vozilo.checkPause();
				Putnik putnik = v.getPutnici().get(i);
				if (obradiPutnika(putnik) == true) 
					brojPutnikaSaNeispravnimDokumentima++;
			}
			System.out.println("Obradjen na policijskom terminalu: " + v);
		} catch (Exception e) {
			Logger.getLogger(Simulacija.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return brojPutnikaSaNeispravnimDokumentima;
	}
}
