package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

public class SeasonIdMap {

	
	private Map <Integer,Season > map;
	
	
	
	
	public SeasonIdMap() {
		
		map=new HashMap<>();
		
		
	}
	
	
	public Season get(Season season) {
		
		Season old=map.get(season.getSeason());
		
		if(old==null) {
			map.put(season.getSeason(), season);
			return season;
			
		}
		
		return old;
		
		
	}
	
   public Season get(int season) {
 return map.get(season);
	
   }
}

