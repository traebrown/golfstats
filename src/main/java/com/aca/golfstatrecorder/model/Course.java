package com.aca.golfstatrecorder.model;

public class Course {
	
	private Integer idVenue;
	private String courseName;
	private Integer par;
	private String courseCity;
	private String courseState;
	
	
	

	public String getCourseCity() {
		return courseCity;
	}
	public void setCourseCity(String courseCity) {
		this.courseCity = courseCity;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}
	public Integer getIdVenue() {
		return idVenue;
	}
	public void setIdVenue(Integer idVenue) {
		this.idVenue = idVenue;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getPar() {
		return par;
	}
	public void setPar(Integer par) {
		this.par = par;
	}

	
	
	

}
