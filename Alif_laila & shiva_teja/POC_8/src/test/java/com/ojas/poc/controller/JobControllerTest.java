package com.ojas.poc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.ojas.controller.JobController;
import com.ojas.model.Job;
import com.ojas.serviceimpl.JobServiceImpl;

public class JobControllerTest {

	@InjectMocks
	private JobController jobContoller;

	@Mock
	private JobServiceImpl jobService;

	@Mock 
	private ResponseEntity<Object> entity;

	@Mock
	private MultipartFile file;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(jobContoller).build();
	}

	@Test
	public void saveUserTest() throws Exception {
		Job job = new Job();
		job.setId(1);
		job.setJobTitle("Java Developer"); 
		job.setJobDescription("Ecommerce website");
		job.setCountry("INDIA");
		job.setExperience(2);
		job.setAvailability("Part-Time");
		job.setJobType("Development");
		job.setLanguage("TELUGU");
		job.setPayRate(99);
		job.setReplyRate(0);
		job.setState("AP");
		when(jobService.saveJob(job)).thenReturn(entity);
		ResponseEntity<Object> saveJob = jobContoller.saveJob(job);
		assertEquals(saveJob.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobIdTest() throws Exception {
		when(jobService.getByJobId((long) 1)).thenReturn(entity);
		ResponseEntity<Object> byJobId = jobContoller.getByJobId((long) 1);
		assertEquals(byJobId.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobTypeTest() throws Exception {
		when(jobService.getByJobType("Development")).thenReturn(entity);
		ResponseEntity<Object> byJobType = jobContoller.getByJobType("Development");
		assertEquals(byJobType.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobExpTest() throws Exception {
		when(jobService.getByExperience(1)).thenReturn(entity);
		ResponseEntity<Object> byJobExp = jobContoller.getByExperience(1);
		assertEquals(byJobExp.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobCountryTest() throws Exception {
		when(jobService.getByCountry("INDIA")).thenReturn(entity);
		ResponseEntity<Object> byJobCountry = jobContoller.getByCountry("INDIA");
		assertEquals(byJobCountry.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobAvailabilityTest() throws Exception {
		when(jobService.getByAvailability("Part-Time")).thenReturn(entity);
		ResponseEntity<Object> byJobAvailability = jobContoller.getByAvailability("Part-Time");
		assertEquals(byJobAvailability.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobSkillsTest() throws Exception {
		when(jobService.getBySkills("Java")).thenReturn(entity);
		ResponseEntity<Object> byJobSkills = jobContoller.getBySkills("Java");
		assertEquals(byJobSkills.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobLanguageTest() throws Exception {
		when(jobService.getByLanguage("ENGLISH")).thenReturn(entity);
		ResponseEntity<Object> byJobLanguage = jobContoller.getByLanguage("ENGLISH");
		assertEquals(byJobLanguage.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByJobPayRateTest() throws Exception {
		when(jobService.getByPayRate(1, 12)).thenReturn(entity);
		ResponseEntity<Object> byJobLanguage = jobContoller.getByPayRate(1, 12);
		assertEquals(byJobLanguage.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getAllJobsTest() throws Exception {
		when(jobService.getAllJobs(0, 1)).thenReturn(entity);
		ResponseEntity<Object> allJobs = jobContoller.getAllJobs(0, 1);
		assertEquals(allJobs.getStatusCode(), HttpStatus.OK);
	}


}
