package net.etfbl.pj2;

public class Kofer {
	private Putnik pripadaPutniku;
	private boolean imaNedozvoljeneStvari;

	public Kofer(Putnik p, boolean nedzvoljene) {
		pripadaPutniku = p;
		imaNedozvoljeneStvari = nedzvoljene;
	}

	public Putnik getPripadaPutniku() {
		return pripadaPutniku;
	}

	public boolean getImaNedozvoljeneStvari() {
		return imaNedozvoljeneStvari;
	}

	@Override
	public String toString() {
		return "Kofer{pripada putniku:" + pripadaPutniku + ", ima nedzvoljene stvari:" + imaNedozvoljeneStvari;
	}
}
