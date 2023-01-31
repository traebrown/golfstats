package com.aca.golfstatrecorder.model;

import java.time.LocalDateTime;

public class Scorecard {
	
	private Integer idVenue;
	private Integer idPlayer;
	private Integer scoreCardId;
	private Integer toPar;
	private Integer score;
	private LocalDateTime createDateTime;
	private String firstName;
	private String lastName;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getIdVenue() {
		return idVenue;
	}
	public void setIdVenue(Integer idVenue) {
		this.idVenue = idVenue;
	}
	public Integer getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}
	
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Integer getScoreCardId() {
		return scoreCardId;
	}
	public void setScoreCardId(Integer scoreCardId) {
		this.scoreCardId = scoreCardId;
	}
	public Integer getToPar() {
		return toPar;
	}
	public void setToPar(Integer toPar) {
		this.toPar = toPar;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	
	

}
