package com.vietis.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exercisequest")
public class ExerciseQuest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String question;

	@Column
	private String option1;

	@Column
	private String option2;

	@Column
	private String option3;

	@Column
	private String option4;

	@Column
	private String correct;

	@ManyToOne
	@JoinColumn(name = "exerciseid", insertable = false, updatable = false)
	private Exercise exerciseEntity;
	
	private Integer exerciseid;
	
	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User userEntity;
	
	private Integer userid;
	
	public ExerciseQuest() {
	}

	public ExerciseQuest(String question, String option1, String option2, String option3, String option4,
			String correct, Integer exerciseid) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct = correct;
		this.exerciseid = exerciseid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public Exercise getExerciseEntity() {
		return exerciseEntity;
	}

	public void setExerciseEntity(Exercise exerciseEntity) {
		this.exerciseEntity = exerciseEntity;
	}

	public Integer getExerciseid() {
		return exerciseid;
	}

	public void setExerciseid(Integer exerciseid) {
		this.exerciseid = exerciseid;
	}

	public User getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(User userEntity) {
		this.userEntity = userEntity;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
