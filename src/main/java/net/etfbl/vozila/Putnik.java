package net.etfbl.vozila;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Putnik implements Serializable{
	private String name;
	private int identifikacioniDokument;
	private boolean imaNeispravneDokumente;

	public Putnik(String name) {
		this.name = name;
		Random rand = new Random();
		identifikacioniDokument = rand.nextInt(100000);
		int percentage = rand.nextInt(99) + 1;
		imaNeispravneDokumente = false;
		if(percentage <= 3)
			imaNeispravneDokumente = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdentifikacioniDokument() {
		return identifikacioniDokument;
	}

	public boolean getImaNeispravneDokumente() {
		return imaNeispravneDokumente;
	}

	public void setImaNeispravneDokumente(boolean ima) {
		imaNeispravneDokumente = ima;
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
	public boolean equals(Object obj) {
		Putnik p = (Putnik) obj;
		if(identifikacioniDokument == p.getIdentifikacioniDokument() && name.equals(p.getName()) && imaNeispravneDokumente == p.getImaNeispravneDokumente())
			return true;
		else return false;
	}

	@Override
	public String toString() {
		return "Identifikacioni dokument putnika: " + identifikacioniDokument + ", neispravan dokument: " + imaNeispravneDokumente;
	}
}
