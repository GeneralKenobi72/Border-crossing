package net.etfbl.vozila;

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

	public static boolean cond_wait = true;

	public Vozilo(int maksimalanBroj) {
		Random rand = new Random();
		brojPutnika = rand.nextInt(maksimalanBroj) + 1;
		putnici = new ArrayList<Putnik>();
		for(int i=0;i<brojPutnika;i++) {
			Putnik p = new Putnik("A"); //TODO: treba promijeniti
			putnici.add(p);
		}
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
			sleep(1000);
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
				posaljiVoziloNaPolicijskiTerminal(Simulacija.pt1);
				while(!prosaoCarinskiTerminal) {
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
				posaljiVoziloNaPolicijskiTerminal(Simulacija.pt2);
				while(!prosaoCarinskiTerminal) {
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
				posaljiVoziloNaPolicijskiTerminal(Simulacija.pt3);
				while(!prosaoCarinskiTerminal) {
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
				posaljiVoziloNaCarinskiTerminal(Simulacija.ct1);
				Simulacija.ct1.sem.release();
				Simulacija.ct1.setZauzet(false);
				prosaoCarinskiTerminal = true;
				Platform.runLater(() -> {
					Simulacija.GuiInstance.removeFromTerminalGUI(1,2);
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
				posaljiVoziloNaCarinskiTerminal(Simulacija.ct2);
				Simulacija.ct2.sem.release();
				Simulacija.ct2.setZauzet(false);
				prosaoCarinskiTerminal = true;
				Platform.runLater(() -> {
					Simulacija.GuiInstance.removeFromTerminalGUI(1,6);
				});
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

	abstract void posaljiVoziloNaCarinskiTerminal(CarinskiTerminal ct);
	abstract void posaljiVoziloNaPolicijskiTerminal(PolicijskiTerminal pt);
}
