package net.etfbl.terminali;

public abstract class Terminal {
	private boolean zauzet = false;

	public boolean getZauzet() {
		return zauzet;
	}

	public void setZauzet(boolean b) {
		zauzet = b;
	}
}
