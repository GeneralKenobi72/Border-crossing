package net.etfbl.vozila;

import java.io.*;
import net.etfbl.gui.*;
import javafx.application.Platform;
import net.etfbl.projektni.*;
import net.etfbl.terminali.*;
import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Vozilo extends Thread implements Serializable{
	private int brojPutnika;
	private ArrayList<Putnik> putnici;
	private int redVozila;
	private boolean prosaoPolicijskiTerminal = false;
	private boolean prosaoCarinskiTerminal = false;
	private int brojPutnikaSaNeispravnimDokumentima;

	public static boolean cond_wait = true;

	public Vozilo(int maksimalanBroj) {
		Random rand = new Random();
		brojPutnika = rand.nextInt(maksimalanBroj) + 1;
		putnici = new ArrayList<Putnik>();
		for(int i=0;i<brojPutnika;i++) {
			Putnik p = new Putnik("P" + (i+1));
			putnici.add(p);
		}
	}

	public int getBrojPutnikaSaNeispravnimDokumentima() {
		return brojPutnikaSaNeispravnimDokumentima;
	}

	public ArrayList<Putnik> getPutnici() {
		return putnici;
	}

	public boolean getProsaoCarinskiTerminal() {
		return prosaoCarinskiTerminal;
	}

	public boolean getProsaoPolicijskiTerminal() {
		return prosaoPolicijskiTerminal;
	}

	public Putnik getPutnik(int i) {
		return putnici.get(i);
	}

	public int getBrojPutnika() {
		return brojPutnika;
	}

	public int getRedVozila() {
		return redVozila;
	}

	public void setRedVozila(int red) {
		redVozila = red;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	@Override
	public String toString() { //TODO: nezavrseno
		return "broj putnika=" + brojPutnika;
	}

	@Override
	public void run() {
		try {
			sleep(10);
			while(!Simulacija.red.isEmpty()) {
				if(Simulacija.red.indexOf(this) == 0) {
					while(!prosaoPolicijskiTerminal)
						naPolicijskiTerminal();
					System.out.println("Zavrsen " + this);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void naPolicijskiTerminal() {
		if(this instanceof KoristiMaliTerminalInterface && !Simulacija.pt1.getZauzet() && Simulacija.pt1.getRadi() && Simulacija.pt1.sem.tryAcquire()) {
			try {
				System.out.println("Vozilo " + getRedVozila() + " Pristupilo policijskom terminalu: pt1");
				Simulacija.pt1.setZauzet(true);
				Simulacija.red.remove(this);
				Platform.runLater(() -> {
					Simulacija.GuiInstance.updateVBoxAfterCarMove(3,2);
				});
				brojPutnikaSaNeispravnimDokumentima = posaljiVoziloNaPolicijskiTerminal(Simulacija.pt1);
				if(getPutnik(0).getImaNeispravneDokumente()) {
					Platform.runLater(() -> {
							Simulacija.GuiInstance.removeFromTerminalGUI(3, 2, true);
					});
					Simulacija.pt1.setZauzet(false);
					Simulacija.pt1.sem.release();
					prosaoCarinskiTerminal = true;
				}
				while(!prosaoCarinskiTerminal && !prosaoPolicijskiTerminal) {
					naCarinskiTerminal(Simulacija.pt1);
				}
				prosaoPolicijskiTerminal = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(this instanceof KoristiMaliTerminalInterface && !Simulacija.pt2.getZauzet() && Simulacija.pt2.getRadi() && Simulacija.pt2.sem.tryAcquire()) {
			try {
				System.out.println("Vozilo " + getRedVozila() + " Pristupilo policijskom terminalu: pt2");
				Simulacija.pt2.setZauzet(true);
				Simulacija.red.remove(this);
				Platform.runLater(() -> {
					Simulacija.GuiInstance.updateVBoxAfterCarMove(3,4);
				});
				brojPutnikaSaNeispravnimDokumentima = posaljiVoziloNaPolicijskiTerminal(Simulacija.pt2);
				if(getPutnik(0).getImaNeispravneDokumente()) {
					Platform.runLater(() -> {
							Simulacija.GuiInstance.removeFromTerminalGUI(3, 4, true);
					});
					Simulacija.pt2.setZauzet(false);
					Simulacija.pt2.sem.release();
					prosaoCarinskiTerminal = true;
				}
				while(!prosaoCarinskiTerminal && !prosaoPolicijskiTerminal) {
					naCarinskiTerminal(Simulacija.pt2);
				}
				prosaoPolicijskiTerminal = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(this instanceof KoristiVelikiTerminalInterface && !Simulacija.pt3.getZauzet() && Simulacija.pt3.getRadi() && Simulacija.pt3.sem.tryAcquire()) {
			try {
				System.out.println("Vozilo " + getRedVozila() + " Pristupilo policijskom terminalu: pt3");
				Simulacija.pt3.setZauzet(true);
				Simulacija.red.remove(this);
				Platform.runLater(() -> {
					Simulacija.GuiInstance.updateVBoxAfterCarMove(3,6);
				});
				brojPutnikaSaNeispravnimDokumentima = posaljiVoziloNaPolicijskiTerminal(Simulacija.pt3);
				if(getPutnik(0).getImaNeispravneDokumente()) {
					Platform.runLater(() -> {
							Simulacija.GuiInstance.removeFromTerminalGUI(3, 6, true);
					});
					Simulacija.pt3.setZauzet(false);
					Simulacija.pt3.sem.release();
					prosaoCarinskiTerminal = true;
				}
				while(!prosaoCarinskiTerminal && !prosaoPolicijskiTerminal) {
					naCarinskiTerminal(Simulacija.pt3);
				}
				prosaoPolicijskiTerminal = true;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public void naCarinskiTerminal(PolicijskiTerminal pt) {
		if(this instanceof KoristiMaliTerminalInterface && !Simulacija.ct1.getZauzet() && Simulacija.ct1.getRadi() && Simulacija.ct1.sem.tryAcquire()) {
			try {
				System.out.println("Vozilo " + getRedVozila() + " Pristupilo carinskom terminalu: ct1");
				Simulacija.ct1.setZauzet(true);
				pt.setZauzet(false);
				pt.sem.release();
				Platform.runLater(() -> {
					if(pt == Simulacija.pt1)
						Simulacija.GuiInstance.GUIMoveCarTerminal(3,2,1,2);
					else
						Simulacija.GuiInstance.GUIMoveCarTerminal(3,4,1,2);
				});
				int carinaPOM = posaljiVoziloNaCarinskiTerminal(Simulacija.ct1);
				Simulacija.ct1.sem.release();
				Simulacija.ct1.setZauzet(false);
				prosaoCarinskiTerminal = true;
				Platform.runLater(() -> {
					Simulacija.GuiInstance.removeFromTerminalGUI(1,2, brojPutnikaSaNeispravnimDokumentima > 0 || carinaPOM > 0);
				});
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else if(this instanceof KoristiVelikiTerminalInterface && !Simulacija.ct2.getZauzet() && Simulacija.ct2.getRadi() && Simulacija.ct2.sem.tryAcquire()) {
			try {
				System.out.println("Vozilo " + getRedVozila() + " Pristupilo carinskom terminalu: ct2");
				Simulacija.ct2.setZauzet(true);
				pt.setZauzet(false);
				pt.sem.release();
				Platform.runLater(() -> {
					Simulacija.GuiInstance.GUIMoveCarTerminal(3,6,1,6);
				});
				int carinaPOM = posaljiVoziloNaCarinskiTerminal(Simulacija.ct2);
				Simulacija.ct2.sem.release();
				Simulacija.ct2.setZauzet(false);
				prosaoCarinskiTerminal = true;
				Platform.runLater(() -> {
					Simulacija.GuiInstance.removeFromTerminalGUI(1, 6, brojPutnikaSaNeispravnimDokumentima > 0 || carinaPOM > 0);
				});
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	public String getMessage() {
		String ret = "Naziv vozila: " + getName() + ", broj putnika: " + getBrojPutnika() + "\n";

		try {
			BufferedReader br = new BufferedReader(new FileReader(Simulacija.tekstualnaDokumentacija));
			String linija;
			if((linija = br.readLine()) != null) {
				String[] stringovi = linija.split("#");

				for(String string : stringovi) {
					String[] s = string.split("-");
					if(s[0].equals(getName())) {
						if(s[0].contains("A"))
							ret += "Putnik " + s[1] + " ima nedozvoljene stvari\n";
						else {
							ret += "Stvarna masa tereta i deklarisana\nmasa tereta su razlicite";
							break;
						}

					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(Simulacija.kaznjeneOsobe.length() == 0)
				return ret;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Simulacija.kaznjeneOsobe));
			while(true) {
				try {
					Putnik deSer = (Putnik)ois.readObject();
					if(deSer == null)
						break;
					if(deSer.equals(getPutnik(0)))
						ret += "Vozac ima neispravne dokumente\n";
					for(int i=1;i<brojPutnika;i++)
						if(deSer.equals(getPutnik(i)))
							ret += "Putnik broj " + (i+1) + " ima neispravne dokumente\n";
				} catch(EOFException e) {
					break;
				}
			}
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ret;
	}

	abstract int posaljiVoziloNaCarinskiTerminal(CarinskiTerminal ct);
	abstract int posaljiVoziloNaPolicijskiTerminal(PolicijskiTerminal pt);
}
