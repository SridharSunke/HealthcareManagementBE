package com.trion.healthcare.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WardManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ward_id;
	@Column(name = "ward_type")
	private String warddType;
	@Column(name = "capacity")
	private int capacity;
	@Column(name = "reporter")
	private String reporter;

	public int getWardid() {
		return ward_id;
	}

	public void setWardid(int wardid) {
		this.ward_id = wardid;
	}

	public String getWarddType() {
		return warddType;
	}

	public void setWarddType(String warddType) {
		this.warddType = warddType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Override
	public String toString() {

		return warddType;

	}

	@Override
	public int hashCode() {
		//return "Ramesh".hashCode();
		 return (warddType.equals("General") || warddType.equals("General/NON/AC")) ? Objects.hash("General") : Objects.hash(warddType);
	}

	@Override
	public boolean equals(Object obj) {
		// System.out.println("this is hashcode()& equals ()");
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return true;
//		if (getClass() != obj.getClass())
//			return false;
		WardManagement other = (WardManagement) obj;
		return other.getWarddType().equals("General") || other.getWarddType().equals("General/NON/AC");

	}

}
