package com.ojas.service;

import org.springframework.http.ResponseEntity;

import com.ojas.model.Job;

public interface JobService {

	public ResponseEntity<Object> saveJob(Job job) throws Exception;

	public ResponseEntity<Object> getByJobId(Long id) throws Exception;

	public ResponseEntity<Object> getByJobType(String jobType) throws Exception;

	public ResponseEntity<Object> getByExperience(Integer experience) throws Exception;

	public ResponseEntity<Object> getByCountry(String country) throws Exception;

	public ResponseEntity<Object> getByAvailability(String availability) throws Exception;

	public ResponseEntity<Object> getByLanguage(String language) throws Exception;

	public ResponseEntity<Object> getBySkills(String skills) throws Exception;

	public ResponseEntity<Object> getByPayRate(int low, int high) throws Exception;

	public ResponseEntity<Object> getAllJobs(int pageNo, int pageSize) throws Exception;

}
