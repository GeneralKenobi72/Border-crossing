package net.etfbl.pj2;

public class LicnoVozilo extends Vozilo {
	public LicnoVozilo() {
		super(Simulacija.MAX_BROJ_PUTNIKA_LICNOG_VOZILA);
	}

	@Override
	public String toString() {
		return "Licno vozilo{" + super.toString() + ", red=" + getRedVozila() + "}";
	}
}
