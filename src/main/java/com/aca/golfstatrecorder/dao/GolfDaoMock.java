package com.aca.golfstatrecorder.dao;

import java.util.ArrayList;
import java.util.List;

import com.aca.golfstatrecorder.model.Course;
import com.aca.golfstatrecorder.model.Player;
import com.aca.golfstatrecorder.model.Scorecard;

public class GolfDaoMock implements GolfDao {
	
	private static Integer lastIdPlayer = 0;
	private static List<Player> players = new ArrayList<Player>();
	
	private static Integer getNextIdPlayer() {
		return ++lastIdPlayer;
	}
	
	static {
		Player player1 = new Player();
		player1.setIdPlayer(getNextIdPlayer());
		player1.setFirstName("Trae");
		player1.setLastName("Brown");
		player1.setCity("Conway");
		player1.setState("Ar");
		
		
		Player player2 = new Player();
		player2.setIdPlayer(getNextIdPlayer());
		player2.setFirstName("Brian");
		player2.setLastName("Slaughter");
		player2.setCity("Searcy");
		player2.setState("Ar");
		
		
		
		
		players.add(player1);
		players.add(player2);
		
	}

	@Override
	public List<Player> getPlayers() {
		List<Player> myPlayers = new ArrayList<Player>();
		myPlayers.addAll(players);
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByState(String state) {
		List<Player> myPlayers = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getState().equals(state)) {
				myPlayers.add(player);
			}
		}
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByLastName(String lastName) {
		List<Player> myPlayers = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getLastName().equals(lastName)) {
				myPlayers.add(player);
			}
		}
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByFirstName(String firstName) {
		List<Player> myPlayers = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getFirstName().equals(firstName)) {
				myPlayers.add(player);
			}
		}
		return myPlayers;
		
	}

	@Override
	public List<Player> getPlayersByCity(String city) {
		List<Player> myPlayers = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getCity().equals(city)) {
				myPlayers.add(player);
			}
		}
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersById(Integer idPlayer) {
		List<Player> myPlayers = new ArrayList<Player>();
		for (Player player : players) {
			if(player.getIdPlayer().intValue() == idPlayer.intValue()) {
				myPlayers.add(player);
				break;
			}
		}
		return myPlayers;
	}

	@Override
	public Player createPlayer(Player newPlayer) {
		newPlayer.setIdPlayer(getNextIdPlayer());
		players.add(newPlayer);
		return newPlayer;
	}

	@Override
	public Player updatePlayer(Player updatePlayer) {
		
		List<Player> players = getPlayersById(updatePlayer.getIdPlayer());
		
		if (players.size() > 0) {
			Player player = players.get(0);
			player.setFirstName(updatePlayer.getFirstName());
			player.setLastName(updatePlayer.getLastName());
			player.setCity(updatePlayer.getCity());
			player.setState(updatePlayer.getState());
			
		}
		
		return updatePlayer;
	}

	@Override
	public Player deletePlayerById(Integer idPlayer) {
		List<Player> players = getPlayersById(idPlayer);
		Player player = null;
		
		if (players.size() > 0) {
			player = players.get(0);
			GolfDaoMock.players.remove(player);
		}
		
		return player;
	}

	@Override
	public Scorecard createScoreCard(Scorecard newScoreCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scorecard> getScoreCardsByPlayerId(Integer idPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scorecard> getScoreCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scorecard> getScoreCardsByVenueId(Integer idVenue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getCoursesByVenueId(Integer idVenue) {
		// TODO Auto-generated method stub
		return null;
	}

}
