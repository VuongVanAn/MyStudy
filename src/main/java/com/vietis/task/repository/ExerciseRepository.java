package com.vietis.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vietis.task.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
	
	@Query("SELECT ex FROM Exercise ex WHERE ex.userid = ?1 ORDER BY ex.id DESC")
	List<Exercise> findByUserid(Integer id);
	
}
