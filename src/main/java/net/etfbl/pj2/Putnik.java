package net.etfbl.pj2;

public class Putnik {
	private String identifikacioniDokument;

	public Putnik(String identifikacioniDokument) {
		this.identifikacioniDokument = identifikacioniDokument;
	}

	public String getIdentifikacioniDokument() {
		return identifikacioniDokument;
	}

	public void setIdentifikacioniDokument(String id) {
		identifikacioniDokument = id;
	}

	@Override
	public String toString() {
		return "Identifikacioni dokument putnika: " + identifikacioniDokument;
	}
}
