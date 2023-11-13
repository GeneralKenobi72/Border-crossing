package net.etfbl.vozila;

import net.etfbl.projektni.*;

public class LicnoVozilo extends Vozilo {
	public LicnoVozilo() {
		super(Simulacija.MAX_BROJ_PUTNIKA_LICNOG_VOZILA);
	}

	@Override
	public String toString() {
		return "Licno vozilo{" + super.toString() + ", red=" + getRedVozila() + "}";
	}
}
