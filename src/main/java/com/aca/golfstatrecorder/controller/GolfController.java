package com.aca.golfstatrecorder.controller;

import java.util.List;

import com.aca.golfstatrecorder.model.Course;
import com.aca.golfstatrecorder.model.Player;
import com.aca.golfstatrecorder.model.Scorecard;
import com.aca.golfstatrecorder.service.GolfService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/players")
public class GolfController {
	
	private GolfService player = new GolfService();
	private GolfService scoreCard = new GolfService();
	private GolfService course = new GolfService();
	
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayers() {
		return player.getPlayers();
	}
	@GET
	@Path("/state/{stateValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayersByState(@PathParam("stateValue")String state) {
		return player.getPlayersByState(state);
	}
	
	@GET
	@Path("/lastName/{lastNameValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayersByLastName(@PathParam("lastNameValue")String lastName) {
		System.out.println("last name: " + lastName);
		return player.getPlayersByLastName(lastName);
	}
	
	@GET
	@Path("/firstName/{firstNameValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayersByFirstName(@PathParam("firstNameValue")String firstName) {
		System.out.println("first name: " + firstName);
		return player.getPlayersByFirstName(firstName);
	}
	@GET
	@Path("/city/{cityValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayersByCity(@PathParam("cityValue")String city) {
		return player.getPlayersByCity(city);
	}
	
	@GET
	@Path("/{idPlayerValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayersById(@PathParam("idPlayerValue")Integer idPlayer) {
		return player.getPlayersById(idPlayer);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Player createPlayer(Player newPlayer) {
		return player.createPlayer(newPlayer);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Player updatePlayer(Player updatePlayer) {
		return player.updatePlayer(updatePlayer);
	}
	
	@DELETE
	@Path("/{idPlayerValue}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Player deletePlayerById(@PathParam("idPlayerValue") Integer idPlayer) {
		return player.deletePlayerById(idPlayer);
		
	}
	
	@POST
	@Path("scorecard/createScorecard")
	@Consumes(MediaType.APPLICATION_JSON)
	public Scorecard createScoreCard(Scorecard newScoreCard) {
		return scoreCard.createScoreCard(newScoreCard);
	}
	@GET
	@Path("/scorecard")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Scorecard> getScoreCards() {
		return scoreCard.getScoreCards();
	}
	
	@GET
	@Path("/scorecard/{idPlayerValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Scorecard> getScoreCardsByPlayerId(@PathParam("idPlayerValue")Integer idPlayer) {
		return scoreCard.getScoreCardsByPlayerId(idPlayer);
	}
	
	@GET
	@Path("/scorecard/idVenue/{idVenueValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Scorecard> getScoreCardsByVenueId(@PathParam("idVenueValue")Integer idVenue) {
		return scoreCard.getScoreCardsByVenueId(idVenue);
	}
	
	@GET
	@Path("/course")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses() {
		return course.getCourses();
	}
	
	@GET
	@Path("/course/idVenue/{idVenueValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesByVenueId(@PathParam("idVenueValue")Integer idVenue) {
		return course.getCoursesByVenueId(idVenue);
	}
	
	
}
