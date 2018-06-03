package it.polito.tdp.seriea.model;

import java.util.Comparator;

public class OrdinaRisultato implements Comparator<SquadraStagionePunti> {

	@Override
	public int compare(SquadraStagionePunti o1, SquadraStagionePunti o2) {
		
		return o1.getSeason().getSeason()-o2.getSeason().getSeason();
	}

	
}
