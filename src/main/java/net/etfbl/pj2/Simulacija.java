package net.etfbl.pj2;

public class Simulacija {
	public static void main(String[] args) {
		Autobus a = new Autobus(52);
		System.out.println(a);
		
		for(int i=0;i<a.getBrojKofera();i++) {
			System.out.println(a.getKofer(i));
		}
	}
}
