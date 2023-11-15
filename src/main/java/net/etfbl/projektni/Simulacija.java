package net.etfbl.projektni;

import net.etfbl.vozila.*;
import net.etfbl.terminali.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Simulacija {
	public static final int MAX_BROJ_PUTNIKA_KAMIONA = 3;
	public static final int MAX_BROJ_PUTNIKA_AUTOBUSA = 52;
	public static final int MAX_BROJ_PUTNIKA_LICNOG_VOZILA = 5;

	public static CarinskiTerminal ct1 = new CarinskiTerminal("ct1");
	public static CarinskiTerminal ct2 = new CarinskiTerminal("ct2");
	public static PolicijskiTerminal pt1 = new PolicijskiTerminal("pt1");
	public static PolicijskiTerminal pt2 = new PolicijskiTerminal("pt2");
	public static PolicijskiTerminal pt3 = new PolicijskiTerminal("pt3");

	public static final ArrayList<Vozilo> red = new ArrayList<>();
	private static ArrayList<Vozilo> listaVozila = new ArrayList<>();

	public static void main(String[] args) {
		kreirajRed();
		for(int i=0;i<red.size();i++)
			System.out.println(red.get(i));
		for(int i=listaVozila.size()-1;i>=0;i--){
			Vozilo v = (Vozilo)red.get(i);
			v.start();
		}
		try {
			for(int i=0;i<red.size();i++){
				Vozilo v = (Vozilo)red.get(i);
				v.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void kreirajRed() {
		int i;
		Object[] rred = new Object[50];
		for(i=0;i<10;i++) {
			Kamion k = new Kamion();
			rred[i] = k;
		}
		for(;i<15;i++) {
			Autobus a = new Autobus();
			rred[i] = a;
		}
		for(;i<50;i++) {
			LicnoVozilo lv = new LicnoVozilo();
			rred[i] = lv;
		}
		List<Object> list = Arrays.asList(rred);
		Collections.shuffle(list);
		i = 0;
		for(Iterator<Object> iter = list.iterator();iter.hasNext();) {
			Vozilo v = (Vozilo)iter.next();
			v.setRedVozila(i);
			listaVozila.add(v);
			red.add(v);
			i++;
		}
	}
}
