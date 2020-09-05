package com.vietis.task.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "exercise")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String title;
	
	@Column(name = "description")
	private String shortDescription;
	
	@Column(name = "createddate")
    private Date createdDate;
	
	@Column(name = "updateddate")
	private Date updateDate;
	
	@Column
	private int amount;
	
	@Column
	private int checked;
	
	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;
	
	private Integer userid;
	
	@OneToMany(mappedBy = "exerciseEntity", fetch = FetchType.LAZY)
	private List<ExerciseQuest> exerciseQuestEntities;
	
	@OneToMany(mappedBy = "exerciseEntity", fetch = FetchType.LAZY)
	private List<Result> resultEntities;

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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<ExerciseQuest> getExerciseQuestEntities() {
		return exerciseQuestEntities;
	}

	public void setExerciseQuestEntities(List<ExerciseQuest> exerciseQuestEntities) {
		this.exerciseQuestEntities = exerciseQuestEntities;
	}

	public List<Result> getResultEntities() {
		return resultEntities;
	}

	public void setResultEntities(List<Result> resultEntities) {
		this.resultEntities = resultEntities;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}
	
}
