package net.etfbl.pj2;

public class LicnoVozilo extends Vozilo {
	public LicnoVozilo(int maksimalanBroj) {
		super(maksimalanBroj);
	}

	@Override
	public String toString() {
		return "Licno vozilo{" + super.toString() + "}";
	}
}
