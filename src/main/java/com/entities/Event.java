package com.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "events_02")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "events_id")
	private Integer id;
	@Column(name = "events_title")
	private String title;
	@Column(name = "events_description")
	private String description;
	@Temporal(TemporalType.DATE)
	private Calendar beginDate;
	private boolean allDay;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="events_userID")
	private User user;


	public Event() {
	}

	public Event(String title, String description, boolean allDay) {
		this.title = title;
		this.description = description;
		this.allDay = allDay;
			

		this.beginDate = new GregorianCalendar(new SimpleTimeZone(3600000,
				"Europe/Paris", Calendar.MARCH, -1, Calendar.SUNDAY, 3600000,
				SimpleTimeZone.UTC_TIME, Calendar.OCTOBER, -1, Calendar.SUNDAY,
				3600000, SimpleTimeZone.UTC_TIME, 3600000));
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String toString() {
		return this.getTitle() + (this.isAllDay() ? " all day long." : "");
	}

	

}
