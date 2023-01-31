package com.aca.golfstatrecorder.dao;

import java.util.List;

import com.aca.golfstatrecorder.model.Course;
import com.aca.golfstatrecorder.model.Player;
import com.aca.golfstatrecorder.model.Scorecard;

public interface GolfDao {
	
	public List<Player> getPlayers();
	public List<Player> getPlayersByState(String state);
	public List<Player> getPlayersByLastName(String lastName);
	public List<Player> getPlayersByFirstName(String firstName);
	public List<Player> getPlayersByCity(String city);
	public List<Player> getPlayersById(Integer idPlayer);
	public Player createPlayer(Player newPlayer);
	public Player updatePlayer(Player updatePlayer);
	public Player deletePlayerById(Integer idPlayer);
	public Scorecard createScoreCard(Scorecard newScoreCard);
	public List<Scorecard> getScoreCardsByPlayerId(Integer idPlayer);
	public List<Scorecard> getScoreCards();
	public List<Scorecard> getScoreCardsByVenueId(Integer idVenue);
	public List<Course> getCourses();
	public List<Course> getCoursesByVenueId(Integer idVenue);
	

}
