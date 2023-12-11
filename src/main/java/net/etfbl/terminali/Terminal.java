package net.etfbl.terminali;

import java.io.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import net.etfbl.vozila.*;
import net.etfbl.projektni.Simulacija;

public abstract class Terminal {
	private String name;
	private boolean zauzet = false;
	private boolean radi = true;
	public Semaphore sem = new Semaphore(1);
	public ReentrantLock lock = new ReentrantLock(); 

	public Terminal(String name) {
		this.name = name;
	}

	public boolean getRadi() {
		return radi;
	}

	public void setRadi(boolean r) {
		radi = r;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getZauzet() {
		return zauzet;
	}

	public void setZauzet(boolean b) {
		zauzet = b;
	}

	public abstract int obradiVozilo(Kamion v);
	public abstract int obradiVozilo(LicnoVozilo v);
	public abstract int obradiVozilo(Autobus v);

	public boolean obradiPutnika(Putnik putnik) {
		boolean izbaci = false;
		if(putnik.getImaNeispravneDokumente() == true){
			try {
				izbaci = true;
				if(Simulacija.kaznjeneOsobe.length() == 0) {
					FileOutputStream fos = new FileOutputStream(Simulacija.kaznjeneOsobe, true);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(putnik);
					oos.close();
				} else {
					FileOutputStream fos = new FileOutputStream(Simulacija.kaznjeneOsobe, true);
					AppendableObjectOutputStream aoos = new AppendableObjectOutputStream(fos);
					aoos.writeObject(putnik);
					aoos.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return izbaci;
	}
}
