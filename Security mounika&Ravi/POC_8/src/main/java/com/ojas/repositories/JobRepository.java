package com.ojas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojas.model.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	
	public Optional<Job> findById(Long id);
	
	public List<Job> findByJobType(String jobType);
	
	public List<Job> findByExperience(Integer experience);

	public List<Job> findByCountry(String country);
	
	public List<Job> findByAvailability(String availability);
	
	public List<Job> findByLanguage(String language);
	
	public List<Job> findBySkills(String skills);
	
	public List<Job> findByPayRateBetween(int low, int high);
	
	public List<Job> findAll();
}
