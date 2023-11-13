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

	public static final ArrayList<Object> red = new ArrayList<>();
	private static ArrayList<Object> listaVozila = new ArrayList<>();

	public static void main(String[] args) {
		kreirajRed();
		for(int i=0;i<red.size();i++)
			System.out.println(red.get(i));
		for(int i=0;i<listaVozila.size();i++){
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
			v.setRed(i);
			listaVozila.add(v);
			red.add(v);
			i++;
		}
	}
}
