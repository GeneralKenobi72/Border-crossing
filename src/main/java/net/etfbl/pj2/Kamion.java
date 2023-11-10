package net.etfbl.pj2;
import java.util.Random;

public class Kamion extends Vozilo {
	private boolean potrebnaCarinskaDokumentacija;
	private int deklarisanaMasaTereta;
	private int stvarnaMasaTereta;

	public Kamion(int maksimalanKapacitet) {
		super(maksimalanKapacitet);
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
	public String toString() {
		return "Kamion{" + super.toString() + ", stvarna masa tereta= " + stvarnaMasaTereta + ", deklarisana masa tereta=" + deklarisanaMasaTereta + ", potrebna carinska dokumentacija=" + potrebnaCarinskaDokumentacija + "}";
	}
}
