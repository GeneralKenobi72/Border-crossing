package net.etfbl.vozila;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Putnik {
	private String identifikacioniDokument;
	private boolean imaNeispravneDokumente;

	public Putnik(String identifikacioniDokument) {
		this.identifikacioniDokument = identifikacioniDokument;
		Random rand = new Random();
		int percentage = rand.nextInt(99) + 1;
		imaNeispravneDokumente = false;
		if(percentage <= 3)
			imaNeispravneDokumente = true;
	}

	public String getIdentifikacioniDokument() {
		return identifikacioniDokument;
	}

	public boolean getImaNeispravneDokumente() {
		return imaNeispravneDokumente;
	}

	public void setImaNeispravneDokumente(boolean ima) {
		imaNeispravneDokumente = ima;
	}

	public void setIdentifikacioniDokument(String id) {
		identifikacioniDokument = id;
	}

	public void kazniOsobu(ObjectOutputStream oos) {
		try {
			oos.writeObject(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void evidentirajOsobu(PrintWriter pw) { // TODO: Mozda interfejs za ovu i prethodnu metodu
		pw.println(this);
	}

	@Override
	public String toString() {
		return "Identifikacioni dokument putnika: " + identifikacioniDokument + ", neispravan dokument: " + imaNeispravneDokumente;
	}
}
