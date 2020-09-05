package com.vietis.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vietis.task.model.ExerciseQuest;

public interface ExerciseQuestRepository extends JpaRepository<ExerciseQuest, Integer> {
	
	List<ExerciseQuest> findByExerciseid(Integer exerciseid);

}
