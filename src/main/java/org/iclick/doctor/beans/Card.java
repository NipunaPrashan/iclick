/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.iclick.doctor.beans;

/**
 * Bean that represent a prescription card
 */
public abstract class Card {
	private int crd_Id;
	private String details;
	private String remarks;

	public Card(int crd_Id, String details, String remarks) {
		this.crd_Id = crd_Id;
		this.details = details;
		this.remarks = remarks;
	}

	public Card(String details) {
		this.details = details;
	}

	public int getCrd_Id() {
		return crd_Id;
	}

	public void setCrd_Id(int crd_Id) {
		this.crd_Id = crd_Id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
