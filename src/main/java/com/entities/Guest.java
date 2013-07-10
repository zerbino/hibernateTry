package com.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "guests_02")
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "guests_id")
	private Integer id;
	@Column(name = "guests_name")
	private String name;
	@Column(name = "guests_email")
	private String email;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(joinColumns = @JoinColumn(name = "guest_event"), inverseJoinColumns = @JoinColumn(name = "events_guest"))
	private List<Event> events;

	public Guest() {
	}

	public Guest(String name, String email) {
		super();
		this.name = name;
		this.email = email;
		events = new ArrayList<Event>();
	}
}