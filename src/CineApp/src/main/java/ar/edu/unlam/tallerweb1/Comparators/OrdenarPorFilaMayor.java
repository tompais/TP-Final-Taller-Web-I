package ar.edu.unlam.tallerweb1.Comparators;

import java.util.Comparator;

import ar.edu.unlam.tallerweb1.Models.Asiento;

public class OrdenarPorFilaMayor implements Comparator<Asiento>{

	@Override
	public int compare(Asiento o1, Asiento o2) {
		return o2.getFila() - o1.getFila();
	}

}
