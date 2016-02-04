/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iclick.doctor.beans;

import org.iclick.doctor.FrontDesk;
import org.iclick.doctor.dbaccess.UserDataAccessManager;

/**
 * Bean that Represent a user
 */
public abstract class User {

	private int id;
	private String first;
	private String last;
	private String efirst;
	private String elast;
	private String gender;
	private String mobile;
	private String emobile;
	private String BDate;
	private String address;
	private String user;
	private String pass;

	public User(String name, int id, String paswrd) {
		this.first = name;
		this.id = id;
		this.pass = paswrd;
	}

	public User(String name, String paswrd) {
		this.first = name;
		this.pass = paswrd;
	}

	public User(String fname, String lname, String n) {
		this.first = fname;
		this.last = lname;
	}

	public User(String name) {
		this.first = name;
	}

	public User(int id) {
		this.id = id;
	}

	public User() {

	}

	public User(String first, String last, String gender, String mobile, String BDate, String address, String user, String pass) {
		this.first = first;
		this.last = last;
		this.gender = gender;
		this.mobile = mobile;
		this.BDate = BDate;
		this.address = address;
		this.user = user;
		this.pass = pass;
	}

	public User(String first, String last, String gender, String mobile, String BDate, String address, String user, String pass, String
			efirst, String elast, String emobile) {
		this.first = first;
		this.last = last;
		this.gender = gender;
		this.mobile = mobile;
		this.BDate = BDate;
		this.address = address;
		this.user = user;
		this.pass = pass;
		this.efirst = efirst;
		this.elast = elast;
		this.emobile = emobile;
	}

	public User(String first, String last, String gender, String mobile, String BDate, String address, String efirst, String elast, String
            emobile) {
		this.first = first;
		this.last = last;
		this.gender = gender;
		this.mobile = mobile;
		this.BDate = BDate;
		this.address = address;
		this.efirst = efirst;
		this.elast = elast;
		this.emobile = emobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBDate() {
		return BDate;
	}

	public void setBDate(String BDate) {
		this.BDate = BDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEfirst() {
		return efirst;
	}

	public void setEfirst(String efirst) {
		this.efirst = efirst;
	}

	public String getElast() {
		return elast;
	}

	public void setElast(String elast) {
		this.elast = elast;
	}

	public String getEmobile() {
		return emobile;
	}

	public void setEmobile(String emobile) {
		this.emobile = emobile;
	}

	public void getuserType(String user) {
		UserDataAccessManager userda = new UserDataAccessManager();
		userda.getType(user);
	}

	public void changePassword(String name, String oldpass, String newpass) {
		FrontDesk thefrontdesk = new FrontDesk();
		thefrontdesk.changePassword(name, oldpass, newpass);
	}

}
