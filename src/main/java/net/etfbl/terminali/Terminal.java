package net.etfbl.terminali;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import net.etfbl.vozila.*;

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

	public abstract void obradiVozilo(Kamion v);
	public abstract void obradiVozilo(LicnoVozilo v);
	public abstract void obradiVozilo(Autobus v);
}
