package net.etfbl.terminali;

import net.etfbl.vozila.*;
import net.etfbl.projektni.*;

public class CarinskiTerminal extends Terminal {
	public CarinskiTerminal(String name) {
		super(name);
	}
	@Override
	public void obradiVozilo(Kamion v) {
		try {
			Thread.sleep(100);
			System.out.println("Obradjen na carinskom terminalu: " + v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void obradiVozilo(Autobus v) {
		try {
			Thread.sleep(100);
			System.out.println("Obradjen na carinskom terminalu: " + v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void obradiVozilo(LicnoVozilo v) {
		try {
			Thread.sleep(100);
			System.out.println("Obradjen na carinskom terminalu: " + v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
