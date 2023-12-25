package net.etfbl.vozila;

import java.util.logging.*;
import net.etfbl.projektni.*;
import net.etfbl.terminali.*;
import java.util.ArrayList;
import java.util.Random;

public class Autobus extends Vozilo implements KoristiMaliTerminalInterface {

	private ArrayList<Kofer> koferi;

	public Autobus() {
		super(Simulacija.MAX_BROJ_PUTNIKA_AUTOBUSA);
		Random rand = new Random();
		koferi = new ArrayList<>();
		for(int i=0;i<getBrojPutnika();i++){
			int percentage = rand.nextInt(99) + 1;
			Kofer k = null;
			if(percentage <= 70){
				percentage = rand.nextInt(99) + 1;
				boolean imaNedozvoljeneStvari = false;
				if(percentage <= 10)
					imaNedozvoljeneStvari = true;
				k = new Kofer(getPutnik(i), imaNedozvoljeneStvari);
			}
			koferi.add(k);
		}
	}

	public Kofer getKofer(int i) {
		return koferi.get(i);
	}

	public int getBrojKofera() {
		return koferi.size();
	}

	@Override
	int posaljiVoziloNaPolicijskiTerminal(PolicijskiTerminal pt) {
		return pt.obradiVozilo(this);
	}

	@Override
	int posaljiVoziloNaCarinskiTerminal(CarinskiTerminal ct) {
		return ct.obradiVozilo(this);
	}

	@Override
	public String toString() {
		return "Autobus{" + super.toString() + ", broj kofera=" + koferi.size() + " red=" + getRedVozila() + "}";
	}
}
