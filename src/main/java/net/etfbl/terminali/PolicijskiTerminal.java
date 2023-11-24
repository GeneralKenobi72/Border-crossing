package net.etfbl.terminali;

import net.etfbl.vozila.*;

public class PolicijskiTerminal extends Terminal {
	public PolicijskiTerminal(String name) {
		super(name);
	}
	@Override
	public void obradiVozilo(Kamion v) {
		try {
			Thread.sleep(2000);
			System.out.println("Obradjen na policijskom terminalu: " + v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void obradiVozilo(LicnoVozilo v) {
		try {
			Thread.sleep(2000);
			System.out.println("Obradjen na policijskom terminalu: " + v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void obradiVozilo(Autobus v) {
		try {
			Thread.sleep(2000);
			System.out.println("Obradjen na policijskom terminalu: " + v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
