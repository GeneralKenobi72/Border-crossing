package net.etfbl.pj2;
import java.util.ArrayList;
import java.util.Random;

public class Autobus extends Vozilo {

	private ArrayList<Kofer> koferi;

	public Autobus(int maksimalanKapacitet) {
		super(maksimalanKapacitet);
		Random rand = new Random();
		koferi = new ArrayList<>();
		for(int i=0;i<getBrojPutnika();i++){
			int percentage = rand.nextInt(99) + 1;
			if(percentage <= 70){
				percentage = rand.nextInt(99) + 1;
				boolean imaNedozvoljeneStvari = false;
				if(percentage <= 10)
					imaNedozvoljeneStvari = true;
				Kofer k = new Kofer(getPutnik(i), imaNedozvoljeneStvari);
				koferi.add(k);
			}
		}
	}

	public Kofer getKofer(int i) {
		return koferi.get(i);
	}

	public int getBrojKofera() {
		return koferi.size();
	}

	@Override
	public String toString() {
		return "Autobus{" + super.toString() + ", broj kofera: " + koferi.size() + "}";
	}
}
