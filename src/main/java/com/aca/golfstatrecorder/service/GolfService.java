package com.aca.golfstatrecorder.service;

import java.util.List;

import com.aca.golfstatrecorder.dao.GolfDao;
import com.aca.golfstatrecorder.dao.GolfDaoImpl;
import com.aca.golfstatrecorder.model.Course;
import com.aca.golfstatrecorder.model.Player;
import com.aca.golfstatrecorder.model.RequestError;
import com.aca.golfstatrecorder.model.Scorecard;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class GolfService {
	
	// private GolfDao golfDao = new GolfDaoMock();
	private GolfDao golfDao = new GolfDaoImpl();
	
	public List<Player> getPlayers() {
		return golfDao.getPlayers();
	}
	
	public List<Player> getPlayersById(Integer idPlayer) {
		validatePlayersId(idPlayer);
		return golfDao.getPlayersById(idPlayer);
	}
	
	private void validatePlayersId(Integer idPlayer) {
		List<Player> players = golfDao.getPlayersById(idPlayer);
		if (null == idPlayer || idPlayer < 0  || players.size() == 0) {
			RequestError error = new RequestError();
			error.setId(1);
			error.setMessage("Invalid value for '" + idPlayer + "'.  This player Id does not exist.");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);
		}
		
	}

	public List<Player> getPlayersByState(String state) {
		return golfDao.getPlayersByState(state);
	}
	
	public List<Player> getPlayersByLastName(String lastName) {
		return golfDao.getPlayersByLastName(lastName);
	}

	public List<Player> getPlayersByFirstName(String firstName) {
		return golfDao.getPlayersByFirstName(firstName);
	}
	public List<Player> getPlayersByCity(String city) {
		return golfDao.getPlayersByCity(city);
	}

	public Player createPlayer(Player newPlayer) {
		return golfDao.createPlayer(newPlayer);
	}

	public Player updatePlayer(Player updatePlayer) {
		validateFirstName(updatePlayer.getFirstName());
		validateLastName(updatePlayer.getLastName());
		validatePlayersId(updatePlayer.getIdPlayer());
		return golfDao.updatePlayer(updatePlayer);
	}

	private void validateLastName(String lastName) {
			if (null == lastName || lastName.length() < 1 ) {
				RequestError error = new RequestError();
				error.setId(3);
				error.setMessage("Invalid value '" + lastName + "' for last name. Value must contain one or more characters.");
				Response response = Response.status(400).entity(error).build();
				throw new WebApplicationException(response);
			}
		
	}
		


	private void validateFirstName(String firstName) {
			if (null == firstName || firstName.length() < 1 ) {
				RequestError error = new RequestError();
				error.setId(2);
				error.setMessage("Invalid value '" + firstName + "' for first name. Value must contain one or more characters.");
				Response response = Response.status(400).entity(error).build();
				throw new WebApplicationException(response);
			}
		
	}

	public Player deletePlayerById(Integer idPlayer) {
		validatePlayersId(idPlayer);
		return golfDao.deletePlayerById(idPlayer);
	}
	
	public Scorecard createScoreCard(Scorecard newScoreCard) {
		return golfDao.createScoreCard(newScoreCard);
	}
	public List<Scorecard> getScoreCardsByPlayerId(Integer idPlayer) {
		validatePlayersId(idPlayer);
		return golfDao.getScoreCardsByPlayerId(idPlayer);
	}

	public List<Scorecard> getScoreCards() {
		return golfDao.getScoreCards();
		
	}
	public List<Scorecard> getScoreCardsByVenueId(Integer idVenue) {
		validateVenueId(idVenue);
		return golfDao.getScoreCardsByVenueId(idVenue);
	}

	private void validateVenueId(Integer idVenue) {
		List<Scorecard> scorecards = golfDao.getScoreCardsByVenueId(idVenue);
		if (null == idVenue || idVenue < 0  || scorecards.size() == 0) {
			RequestError error = new RequestError();
			error.setId(7);
			error.setMessage("Invalid value for '" + idVenue + "'.  This venue Id does not exist.");
			Response response = Response.status(600).entity(error).build();
			throw new WebApplicationException(response);
		
	}
  }
	
	public List<Course> getCourses() {
		return golfDao.getCourses();
	}
	
	public List<Course> getCoursesByVenueId(Integer idVenue) {
		validateVenueId(idVenue);
		return golfDao.getCoursesByVenueId(idVenue);
	}
	
}
