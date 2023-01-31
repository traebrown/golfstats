package com.aca.golfstatrecorder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.golfstatrecorder.model.Course;
import com.aca.golfstatrecorder.model.Player;
import com.aca.golfstatrecorder.model.Scorecard;

public class GolfDaoImpl implements GolfDao{
	
	private static String selectAllPlayers = 
			"SELECT idPlayer, firstName, lastName, city, state " +
			"FROM player";
	
	private static String selectPlayersByState =
			"SELECT idPlayer, firstName, lastName, city, state " +
			"FROM player " +
			"WHERE state LIKE ? ";
	
	private static String selectPlayersById = 
			"SELECT idPlayer, firstName, lastName, city, state " +
			"FROM player " +
			"WHERE idPlayer = ? ";
	
	private static String selectPlayersByFirstName =
			"SELECT idPlayer, firstName, lastName, city, state " +
			"FROM player " +
			"WHERE firstName LIKE ? ";
	
	private static String selectPlayersByLastName =
			"SELECT idPlayer, firstName, lastName, city, state " +
			"FROM player " +
			"WHERE lastName LIKE ? ";
	
	private static String selectPlayersByCity = 
			"SELECT idPlayer, firstName, lastName, city, state " +
			"FROM player " +
			"WHERE city LIKE ? ";
	
	private static String deletePlayersById =
			"DELETE FROM player " +
			"WHERE idPlayer = ? ";
	
	private static String updatePlayersById = 
			"UPDATE player " +
			"SET firstName = ?, " +
			"    lastName = ?, " +
			"    city = ?, " +
			"    state = ? "+
			"WHERE idPlayer = ?";
	
	private static String insertPlayer =
			"INSERT INTO player (firstName, lastName, city, state) " +
			"VALUES " +
			"(?,?,?,?)";
	
	private static String selectNewPlayerId = 
			"SELECT LAST_INSERT_ID() AS idPlayer";
	
	private static String insertScoreCard = 
			"INSERT INTO scorecard (idPlayer, idVenue, toPar, score) " +
			"VALUES " +
			"(?, ?, ?, ?)";
	
	private static String selectAllScoreCards = 
			"SELECT firstName, lastName, scorecard.idPlayer, idVenue, scoreCardId, toPar, score, createDateTime " +
			"FROM scorecard inner join player on scorecard.idPlayer = player.idPlayer";
	
	//SELECT scorecard.idPlayer, firstName, lastName, idVenue, toPar, score
	//FROM scorecard
	//INNER JOIN player ON scorecard.idPlayer = player.idPlayer;
	
	private static String selectScoreCardsByPlayerId =
			"SELECT idPlayer, idVenue, scoreCardId, toPar, score, createDateTime " +
			"FROM scorecard " +
			"WHERE idPlayer = ? ";
	
	private static String selectScoreCardsByVenueId =
			"SELECT idPlayer, idVenue, scoreCardId, toPar, score, createDateTime " +
			"FROM scorecard " +
			"WHERE idVenue = ? ";
	
	private static String selectAllCourses = 
			"SELECT idVenue, par, courseName, courseCity, courseState " +
			"FROM course";
	
	private static String selectCoursesByVenueId = 
			"SELECT idVenue, par, courseName, courseCity, courseState " +
			"FROM course " +
			"WHERE idVenue = ?";
			
			
			

	@Override
	public List<Player> getPlayers() {
		List<Player> myPlayers = new ArrayList<Player>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllPlayers);
			myPlayers = makePlayer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myPlayers;
	}

	@Override
	public List<Scorecard> getScoreCards() {
		List<Scorecard> myScoreCards = new ArrayList<Scorecard>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllScoreCards);
			myScoreCards = makeScoreCard(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myScoreCards;
	}
	
	
	
	private List<Course> makeCourse(ResultSet result) throws SQLException {
		List<Course> myCourses = new ArrayList<Course>();
		
		while(result.next()) {
			Course course = new Course();
			
			course.setIdVenue(result.getInt("idVenue"));
			course.setPar(result.getInt("par"));
			course.setCourseName(result.getString("courseName"));
			course.setCourseCity(result.getString("courseCity"));
			course.setCourseState(result.getString("courseState"));
			
			//TODO set update
			
			myCourses.add(course);
		}
		
		return myCourses;
		
	}
	
	
	
	
	private List<Scorecard> makeScoreCard(ResultSet result) throws SQLException {
		List<Scorecard> myScoreCards = new ArrayList<Scorecard>();
		
		while(result.next()) {
			Scorecard scorecard = new Scorecard();
			
			scorecard.setFirstName(result.getString("firstName"));
			scorecard.setLastName(result.getString("lastName"));
			scorecard.setIdPlayer(result.getInt("idPlayer"));
			scorecard.setIdVenue(result.getInt("idVenue"));
			scorecard.setScoreCardId(result.getInt("scoreCardId"));
			scorecard.setToPar(result.getInt("topar"));
			scorecard.setScore(result.getInt("score"));
			scorecard.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
			
			//TODO set update
			
			myScoreCards.add(scorecard);
		}
		
		return myScoreCards;
		
	}
	
	
	
	private List<Player> makePlayer(ResultSet result) throws SQLException {
		List<Player> myPlayers = new ArrayList<Player>();
		
		while(result.next()) {
			Player player = new Player();
			
			player.setIdPlayer(result.getInt("idPlayer"));
			player.setFirstName(result.getString("firstName"));
			player.setLastName(result.getString("lastName"));
			player.setCity(result.getString("city"));
			player.setState(result.getString("state"));
			
			//TODO set update
			
			myPlayers.add(player);
		}
		
		
		
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByState(String state) {
		List<Player> myPlayers = new ArrayList<Player>();
		ResultSet result = null; //allows us to see the rows of data.
		PreparedStatement ps = null; //allows for '?' substitution.
		
		Connection conn = MariaDbUtil.getConnection(); // takes URL, and sends request to mySQL, listening on port 3306
		
		try {
			ps = conn.prepareStatement(selectPlayersByState);
			state = "%" + state + "%";
			ps.setString(1, state.toString()); // replaces substitution "?" with title.
			
			result = ps.executeQuery(); // sends to driver to mySQL and we get back result.
			myPlayers = makePlayer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByLastName(String lastName) {
		List<Player> myPlayers = new ArrayList<Player>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlayersByLastName);
			lastName = "%" + lastName + "%";
			ps.setString(1, lastName.toString());
			
			result = ps.executeQuery();
			myPlayers = makePlayer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByFirstName(String firstName) {
		List<Player> myPlayers = new ArrayList<Player>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlayersByFirstName);
			firstName = "%" + firstName + "%";
			ps.setString(1, firstName.toString());
			
			result = ps.executeQuery();
			myPlayers = makePlayer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersByCity(String city) {
		List<Player> myPlayers = new ArrayList<Player>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlayersByCity);
			city = "%" + city + "%";
			ps.setString(1, city.toString());
			
			result = ps.executeQuery();
			myPlayers = makePlayer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myPlayers;
	}

	@Override
	public List<Player> getPlayersById(Integer idPlayer) {
		List<Player> myPlayers = new ArrayList<Player>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectPlayersById);
			ps.setInt(1, idPlayer);
			
			result = ps.executeQuery();
			myPlayers = makePlayer(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myPlayers;
	}

	@Override
	public Player createPlayer(Player newPlayer) {
		
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				ps = conn.prepareStatement(insertPlayer);
				ps.setString(1, newPlayer.getFirstName());
				ps.setString(2, newPlayer.getLastName());
				ps.setString(3, newPlayer.getCity());
				ps.setString(4, newPlayer.getState());
				updateRowCount = ps.executeUpdate();
				System.out.println("insert row count: " + updateRowCount);
				int playerId = getNewPlayerId(conn);
				newPlayer.setIdPlayer(playerId);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return newPlayer;
	}

	private int getNewPlayerId(Connection conn) {
		ResultSet result = null;
		Statement statement = null;
		int newPlayerId = 0;
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectNewPlayerId);
			
			while(result.next()) {
			newPlayerId = result.getInt("idPlayer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newPlayerId;
	}

	@Override
	public Player updatePlayer(Player updatePlayer) {
		List<Player> players = this.getPlayersById(updatePlayer.getIdPlayer());
		
		
		if (players.size() > 0) {
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				ps = conn.prepareStatement(updatePlayersById);
				ps.setString(1, updatePlayer.getFirstName());
				ps.setString(2, updatePlayer.getLastName());
				ps.setString(3, updatePlayer.getCity());
				ps.setString(4, updatePlayer.getState());
				ps.setInt(5, updatePlayer.getIdPlayer());
				
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updatePlayer;
	}

	@Override
	public Player deletePlayerById(Integer idPlayer) {
		List<Player> players = this.getPlayersById(idPlayer);
		Player playerToDelete = null;
		
		if (players.size() > 0) {
			playerToDelete = players.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;
			
			Connection conn = MariaDbUtil.getConnection();
			
			try {
				ps = conn.prepareStatement(deletePlayersById);
				ps.setInt(1, idPlayer);
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return playerToDelete;
	}

	@Override
	public Scorecard createScoreCard(Scorecard newScoreCard) {
		int updateRowCount = 0;
		PreparedStatement ps = null;
		
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(insertScoreCard);
			
			ps.setInt(1, newScoreCard.getIdPlayer());
			ps.setInt(2, newScoreCard.getIdVenue());
			ps.setInt(3, newScoreCard.getToPar());
			ps.setInt(4, newScoreCard.getScore());
			updateRowCount = ps.executeUpdate();
			System.out.println("insert row count: " + updateRowCount);
	
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return newScoreCard;
	}

	@Override
	public List<Scorecard> getScoreCardsByPlayerId(Integer idPlayer) {
		List<Scorecard> myScoreCards = new ArrayList<Scorecard>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectScoreCardsByPlayerId);
			ps.setInt(1, idPlayer);
			
			result = ps.executeQuery();
			myScoreCards = makeScoreCard(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myScoreCards;
	}

	@Override
	public List<Scorecard> getScoreCardsByVenueId(Integer idVenue) {
		List<Scorecard> myScoreCards = new ArrayList<Scorecard>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectScoreCardsByVenueId);
			ps.setInt(1, idVenue);
			
			result = ps.executeQuery();
			myScoreCards = makeScoreCard(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myScoreCards;
	}

	@Override
	public List<Course> getCourses() {
		List<Course> myCourses = new ArrayList<Course>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllCourses);
			myCourses = makeCourse(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myCourses;
	}

	@Override
	public List<Course> getCoursesByVenueId(Integer idVenue) {
		List<Course> myCourses = new ArrayList<Course>();
		ResultSet result = null;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(selectCoursesByVenueId);
			ps.setInt(1, idVenue);
			
			result = ps.executeQuery();
			myCourses = makeCourse(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return myCourses;
	}



}
