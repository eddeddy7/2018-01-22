package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.SeasonIdMap;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamIdMap;

public class SerieADAO {

	public List<Season> listAllSeasons(SeasonIdMap seasonmap) {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Season season=(new Season(res.getInt("season"), res.getString("description")));
				result.add(seasonmap.get(season));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams(TeamIdMap teammap) {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Team team=new Team(res.getString("team"));
				result.add(teammap.get(team));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Match> ottieniMatch(Team t, SeasonIdMap seasonmap, TeamIdMap teammap) {
		
		String sql = "SELECT m.match_id, m.Season, m.HomeTeam, m.AwayTeam, m.FTR "+
				"FROM matches as m "+
				"WHERE (m.HomeTeam=? OR m.AwayTeam=?)";
;
		List<Match> matches = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, t.getTeam());
			st.setString(2, t.getTeam());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Season season=seasonmap.get(res.getInt("m.Season"));
				Team team=teammap.get(res.getString("m.HomeTeam"));
				Team team2=teammap.get(res.getString("m.AwayTeam"));
				matches.add(new Match(res.getInt("m.match_id"), season,team,team2, res.getString("m.FTR")));
			}

			conn.close();
			return matches;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

	}

}
