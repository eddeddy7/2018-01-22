package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.mchange.rmi.Checkable;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	
	
	private Graph <Season, DefaultWeightedEdge> grafo;
	private SerieADAO dao;
	private List <Team> teams;
	private List<Season> seasons;
	private SeasonIdMap seasonmap;
	private TeamIdMap  teammap;
	private List<SquadraStagionePunti>  risultato;
	private int best;
	private List<SquadraStagionePunti> soluzione;
	
	
	public Model() {
		dao=new SerieADAO();
		seasonmap=new SeasonIdMap();
		teammap=new TeamIdMap();
		teams=dao.listTeams(teammap);
		seasons=dao.listAllSeasons(seasonmap);
		best=0;
	}

	
	
	public String ottieniStagioniePuntiSquadra(Team t) {
		
		List<Match> matches=dao.ottieniMatch(t,seasonmap,teammap);
		
		
		 risultato=cercaFine(matches, t);
		
	
		StringBuilder sb=new StringBuilder();
		
		sb.append("La "+ t+ " ha ottenuto:\n");
		
		
		for(SquadraStagionePunti sp: risultato) {
			
			sb.append("stagione: "+ sp.getSeason()+ " punti: "+sp.getPunti()+"\n");
			
			
		}
		
		
		
		
		return sb.toString();
		
	}


	public void creaGrafo() {
		
		grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		
		for(SquadraStagionePunti st: risultato)
			grafo.addVertex(st.getSeason());
		
		
		System.out.println(grafo.vertexSet().size());
		
		
		
		Collections.sort(risultato, new OrdinaPunteggi());
		
		for(int i=0;i<risultato.size();i++) {
			for(int j=i+1;j<risultato.size();j++) {
				 int peso= Math.abs(risultato.get(i).getPunti()-risultato.get(j).getPunti());
					Graphs.addEdge(grafo, risultato.get(i).getSeason(), risultato.get(j).getSeason(), peso);
				
			}
		}

	/*	for(SquadraStagionePunti st: risultato) {
			for(SquadraStagionePunti re: risultato) {
			  if(!st.equals(re)) {
				 if(st.getPunti()>=re.getPunti()) {
					 int peso= st.getPunti()-re.getPunti();
				Graphs.addEdge(grafo, re.getSeason(), st.getSeason(), peso);
					 	 
				 }
				 
/*				 else {
					 
					 
					 int peso= re.getPunti()-st.getPunti();
						Graphs.addEdge(grafo, st.getSeason(), re.getSeason(), peso);
				 }
				
	*/			 
				 
				 
				 
				 
				 
				 
				 
				 
			// }
				
				
			
		//	}
		
		//}
		
		
		System.out.println(grafo.edgeSet().size());
		
	}
	

	
	public SquadraStagionePunti ottieniAnnataOro() {
		SquadraStagionePunti p = null;
		int best=-1;
		for(Season s: grafo.vertexSet()) {
			int punteggio=0;
		List<Season> temp=new ArrayList<>();
		temp.addAll(Graphs.neighborListOf(grafo, s));
		
		for(Season s1: temp) {
			
			if(grafo.containsEdge(s, s1)) {
				
				DefaultWeightedEdge def=grafo.getEdge(s, s1);
				punteggio-=grafo.getEdgeWeight(def);
			}
				
			else {
				
				DefaultWeightedEdge def=grafo.getEdge(s1, s);
				punteggio+=grafo.getEdgeWeight(def);
					
				
			}
			
			
			
		}
			
		//s.setPunteggioOro(punteggio);
		if(punteggio>best) {
			best=punteggio;
		 p=new SquadraStagionePunti(null, s, punteggio);
			
			
			
		}
		
			
		}
		
		
	return p;	
	}
	
	
	
	private List<SquadraStagionePunti> cercaFine(List<Match> matches, Team t) {
		List <SquadraStagionePunti> temp= new ArrayList<>();
		for(Season s:seasons) {
			int somma=0;
			for(Match m:matches) {
				
				if(m.getSeason().equals(s)) {
					
					if(m.getHomeTeam().equals(t)) {
						
						if(m.getFtr().equals("H")) {
						somma+=3;
						}
						if(m.getFtr().equals("D")) {
							
							
							somma+=1;
							
							
						}
						
						
						
					}
					else {
						
						if(m.getFtr().equals("A")) {
							somma+=3;
							}
							if(m.getFtr().equals("D")) {
								
								
								somma+=1;
								
								
							}
						
						
					}
					
					
	
				}
				
				
				
				
			}//fine scansione matches
			
			if(somma!=0) {
				SquadraStagionePunti squadraStagionePunti=new SquadraStagionePunti(t, s, somma);
				temp.add(squadraStagionePunti);
				
			}
			
			
			
			
			
			
		}
		return temp;
	}
	
	
	
	public String calcolaCamminoVirtuoso() {
		
		
		List<SquadraStagionePunti> parziale=new ArrayList<>();
	
		
		
			
			
		Collections.sort(risultato, new OrdinaRisultato());
		System.out.println(risultato);
		List<SquadraStagionePunti> temp=this.recursive(parziale);
			
			StringBuilder stringBuilder=new StringBuilder();
			
			
			for(SquadraStagionePunti sp: temp) {
				stringBuilder.append(sp+ " ");
				
				
				
			}
			
			return stringBuilder.toString();
		}



	private List<SquadraStagionePunti> recursive(List<SquadraStagionePunti> parziale) {
		
		if(parziale.size()!=0) {
		if(punteggio(parziale)>best) {
			
			 soluzione=new ArrayList<>(parziale);
			best=punteggio(parziale);
		//	System.out.println(soluzione);
			
		}
		
		}
		
		
		for(SquadraStagionePunti s:risultato) {
			
			if(check(parziale, s)) {
				parziale.add(s);
				this.recursive(parziale);
				parziale.remove(parziale.size()-1);	
			}
			
		}
		
		
		
		return soluzione;
	}



	private int punteggio(List<SquadraStagionePunti> parziale) {
		int somma=0;
		for(SquadraStagionePunti s: parziale) {
			somma+=s.getPunti();
			
			
		}
		return somma;
	}



	private boolean check(List<SquadraStagionePunti> parziale, SquadraStagionePunti s) {
		
		

		if(parziale.size()>=1) {
		if(!grafo.containsEdge(parziale.get(parziale.size()-1).getSeason(), s.getSeason()))
		  return false;
		
		
		if(s.getSeason().getSeason()<parziale.get(parziale.size()-1).getSeason().getSeason())
			return false;
		
		
		if(parziale.get(parziale.size()-1).getPunti()> s.getPunti())
			return false;
		
		
		for(int i=1;i<risultato.size();i++) {
			if(risultato.get(i).equals(s)) {
				if(!risultato.get(i-1).equals(parziale.get(parziale.size()-1)))
				return false;
				
			}	
			}
			
			
		}
		
		
		
		
		return true;
	}



	public List<Team> ottieniSquadre() {
		// TODO Auto-generated method stub
		return teams;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	



