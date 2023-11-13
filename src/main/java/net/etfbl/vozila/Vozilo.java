package net.etfbl.vozila;

import net.etfbl.projektni.*;
import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Vozilo extends Thread implements Serializable{
	private int brojPutnika;
	private ArrayList<Putnik> putnici;
	private int redVozila;

	public static ArrayList<Object> red = Simulacija.red;

	public Vozilo(int maksimalanBroj) {
		Random rand = new Random();
		brojPutnika = rand.nextInt(maksimalanBroj) + 1;
		putnici = new ArrayList<Putnik>();
		for(int i=0;i<brojPutnika;i++) {
			Putnik p = new Putnik("A"); //TODO: treba promijeniti
			putnici.add(p);
		}
	}

	public Putnik getPutnik(int i) {
		return putnici.get(i);
	}

	public int getBrojPutnika() {
		return brojPutnika;
	}

	public int getRedVozila() {
		return redVozila;
	}

	public void setRed(int red) {
		this.redVozila = red;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	@Override
	public String toString() { //TODO: nezavrseno
		return "broj putnika=" + brojPutnika;
	}

	@Override
	public void run() {
		try {
			sleep(1000);
			synchronized(red) {
				while(!red.isEmpty()) {
					redVozila = red.indexOf(this);

					if(redVozila == 0) {
						System.out.println("Zavrsen " + this);
						red.remove(0);
						red.notifyAll();
						return;
					} else {
						red.wait();
					}
				}
				red.notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
