package com.ojas.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.model.Job;
import com.ojas.serviceimpl.JobServiceImpl;

@RestController
@RequestMapping("/poc/job")
public class JobController {

	@Autowired
	private JobServiceImpl jobService;
	static Logger log = Logger.getLogger(UserController.class.getName());

	@Secured({ "ADMIN" })
	@PostMapping(path = "/postjob", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> saveJob(@RequestBody Job jobReq) {
		log.debug("Incoming request job controller : " + jobReq);
		ResponseEntity<Object> saveJob = jobService.saveJob(jobReq);
		return new ResponseEntity<>(saveJob, HttpStatus.OK);
	}

	@GetMapping(path = "/getjob/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByJobId(@PathVariable Long id) {
		log.debug("Incoming request jobid method : " + id);
		ResponseEntity<Object> byJobId = jobService.getByJobId(id);
		return new ResponseEntity<>(byJobId, HttpStatus.OK);
	}

	@GetMapping(path = "/getByType/{type}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByJobType(@PathVariable String type) {
		log.debug("Incoming request type method : " + type);
		ResponseEntity<Object> byJobType = jobService.getByJobType(type);
		return new ResponseEntity<>(byJobType, HttpStatus.OK);
	}

	@GetMapping(path = "/getByExp/{exp}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByExperience(@PathVariable Integer exp) {
		log.debug("Incoming request experience method : " + exp);
		ResponseEntity<Object> byExperience = jobService.getByExperience(exp);
		return new ResponseEntity<>(byExperience, HttpStatus.OK);
	}

	@GetMapping(path = "/getByCountry/{country}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByCountry(@PathVariable String country) {
		log.debug("Incoming request country method : " + country);
		ResponseEntity<Object> byCountry = jobService.getByCountry(country);
		return new ResponseEntity<>(byCountry, HttpStatus.OK);
	}

	@GetMapping(path = "/getByAvailability/{availability}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByAvailability(@PathVariable String availability) {
		log.debug("Incoming request availability method : " + availability);
		ResponseEntity<Object> byAvailability = jobService.getByAvailability(availability);
		return new ResponseEntity<>(byAvailability, HttpStatus.OK);
	}

	@GetMapping(path = "/getBySkills/{skills}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getBySkills(@PathVariable String skills) {
		log.debug("Incoming request skills method : " + skills);
		ResponseEntity<Object> bySkills = jobService.getBySkills(skills);
		return new ResponseEntity<>(bySkills, HttpStatus.OK);
	}

	@GetMapping(path = "/getByLanguage/{language}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByLanguage(@PathVariable String language) {
		log.debug("Incoming request language method : " + language);
		ResponseEntity<Object> byLanguage = jobService.getByLanguage(language);
		return new ResponseEntity<>(byLanguage, HttpStatus.OK);
	}

	@GetMapping(path = "/getByPayRate/{low}/{high}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getByPayRate(@PathVariable int low, @PathVariable int high) {
		log.debug("Incoming request payrate method : " + low + high);
		ResponseEntity<Object> byPayRate = jobService.getByPayRate(low, high);
		return new ResponseEntity<>(byPayRate, HttpStatus.OK);
	}

	@GetMapping(path = "/getalljobs/{pageNo}/{pageSize}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> getAllJobs(@PathVariable int pageNo, @PathVariable int pageSize) {
		log.debug("Incoming request getalljob method ");
		ResponseEntity<Object> allJobs = jobService.getAllJobs(pageNo, pageSize);
		return new ResponseEntity<>(allJobs, HttpStatus.OK);
	}

	
}