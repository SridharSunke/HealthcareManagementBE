package com.trion.healthcare.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Department implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1950746722247092067L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "ward_id")
	private WardManagement wardId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WardManagement getWardId() {
		return wardId;
	}

	public void setWardId(WardManagement wardId) {
		this.wardId = wardId;
	}
}
