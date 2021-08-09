package com.my.app.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@Column(name = "SEQ")
	@SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
	// @GenericGenerator(name = "userMaxGenerator", strategy =
	// "com.my.app.common.generator.UserMaxGenerator")
	// @GenericGenerator(name = "increment", strategy = "increment", parameters = {
	// @Parameter(name = "column", value = "seq"), @Parameter(name = "tables", value
	// = "User") })
	// @GeneratedValue(generator = "userMaxGenerator")
	private Integer seq;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "CREATE_DT")
	private Date createDt;

	@Column(name = "UPDATE_DT")
	private Date updateDt;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}
