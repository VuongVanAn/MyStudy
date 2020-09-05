package com.vietis.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vietis.task.model.Result;

public interface ResultRepository extends JpaRepository<Result, Integer> {
	
	@Query("SELECT res FROM Result res WHERE res.exerciseid = ?1 ORDER BY res.id DESC")
	List<Result> findByExerciseid(Integer exerciseid);

}
