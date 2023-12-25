package net.etfbl.vozila;

import java.util.logging.*;
import net.etfbl.terminali.*;
import net.etfbl.projektni.*;
import java.util.Random;

public class Kamion extends Vozilo implements KoristiVelikiTerminalInterface {
	private boolean potrebnaCarinskaDokumentacija;
	private int deklarisanaMasaTereta;
	private int stvarnaMasaTereta;

	public Kamion() {
		super(Simulacija.MAX_BROJ_PUTNIKA_KAMIONA);
		Random rand = new Random();
		potrebnaCarinskaDokumentacija = rand.nextBoolean();
		deklarisanaMasaTereta = rand.nextInt(10000) + 10000;
		int percentage = rand.nextInt(99) + 1;
		int increment = 0;
		if(percentage <= 20) {
			increment = rand.nextInt(29) + 1;
		}
		stvarnaMasaTereta = deklarisanaMasaTereta + (deklarisanaMasaTereta*increment/100);
	}

	public boolean getPotrebnaCarinskaDokumentacija() {
		return potrebnaCarinskaDokumentacija;
	}

	public int getDeklarisanaMasaTereta() {
		return deklarisanaMasaTereta;
	}

	public int getStvarnaMasaTereta() {
		return stvarnaMasaTereta;
	}

	public void setPotrebnaCarinskaDokumentacija(boolean pcd) {
		potrebnaCarinskaDokumentacija = pcd;
	}

	public void setDeklarisanaMasaTereta(int deklarisana) {
		deklarisanaMasaTereta = deklarisana;
	}

	public void setStvarnaMasaTereta(int stvarna) {
		stvarnaMasaTereta = stvarna;
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
		return "Kamion{" + super.toString() + ", deklarisana masa tereta=" + deklarisanaMasaTereta + ", stvarna masa tereta=" + stvarnaMasaTereta + ", potrebna carinska dokumentacija=" + potrebnaCarinskaDokumentacija + ", red=" + getRedVozila() + "}";
	}
}
