package it.polito.tdp.seriea.model;

public class TestModel {

	public static void main(String[] args) {
	
		
		
		Model model=new Model();
		
		Team team=new Team("Fiorentina");
		
		System.out.println(model.ottieniStagioniePuntiSquadra(team));
		model.creaGrafo();
		
		System.out.println(model.ottieniAnnataOro());
		model.calcolaCamminoVirtuoso();

	}

}
