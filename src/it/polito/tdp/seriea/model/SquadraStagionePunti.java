package it.polito.tdp.seriea.model;

public class SquadraStagionePunti {

	
	private Team team;
	private Season season;
	private int punti;
	
	
	
	
	
	public String toString() {
		
		
		
		return season+ " "+ punti;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquadraStagionePunti other = (SquadraStagionePunti) obj;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		return true;
	}
	public SquadraStagionePunti(Team team, Season season, int punti) {
		super();
		this.team = team;
		this.season = season;
		this.punti = punti;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	
	
	
	
	
}
