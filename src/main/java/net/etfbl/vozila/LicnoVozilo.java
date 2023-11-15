package net.etfbl.vozila;

import net.etfbl.projektni.*;
import net.etfbl.terminali.*;

public class LicnoVozilo extends Vozilo implements KoristiMaliTerminalInterface{
	public LicnoVozilo() {
		super(Simulacija.MAX_BROJ_PUTNIKA_LICNOG_VOZILA);
	}

	@Override
	void posaljiVoziloNaPolicijskiTerminal(PolicijskiTerminal pt) {
		pt.obradiVozilo(this);
	}

	@Override
	void posaljiVoziloNaCarinskiTerminal(CarinskiTerminal ct) {
		ct.obradiVozilo(this);
	}

	@Override
	public String toString() {
		return "Licno vozilo{" + super.toString() + ", red=" + getRedVozila() + "}";
	}
}
