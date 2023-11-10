package net.etfbl.pj2;
import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Vozilo extends Thread implements Serializable{
	private int brojPutnika;
	private ArrayList<Putnik> putnici;

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

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	@Override
	public String toString() { //TODO: nezavrseno
		return "broj putnika= " + brojPutnika;
	}

	@Override
	public void run() { //TODO: nedostaje implementacija
		return;
	}
}
