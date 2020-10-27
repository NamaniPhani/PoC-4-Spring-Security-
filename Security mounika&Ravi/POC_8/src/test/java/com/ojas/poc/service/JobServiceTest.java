//package com.ojas.poc.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.ojas.exception.CustomException;
//import com.ojas.exception.Response;
//import com.ojas.model.Job;
//import com.ojas.model.User;
//import com.ojas.repositories.JobRepository;
//import com.ojas.serviceimpl.JobServiceImpl;
//
//public class JobServiceTest {
//
//	@InjectMocks
//	private JobServiceImpl jobServiceImpl;
//
//	@Mock
//	private JobRepository jobRepo;
//
//	@Mock
//	Response response;
//
//	@Mock
//	Job job;
//
//	@Mock
//	private ResponseEntity<Object> entity;
//
//	@Mock
//	private CustomException cex;
//
//	@Mock
//	private PageImpl<Job> pageImpl;
//
//	@Mock
//	private Pageable page;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(jobServiceImpl).build();
//	}
//
//	@Test
//	public void nullRequestTest() throws Exception {
//		Job job = null;
//		doThrow(new CustomException("inavlid request")).when(jobRepo).save(job);
//		ResponseEntity<Object> saveJob = jobServiceImpl.saveJob(job);
//		assertEquals(saveJob.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
//	}
//
//	@Test
//	public void saveUserTest() throws Exception {
//		Job job = new Job();
//		User user = new User();
//		user.setUsername("Ojas"); 
//		job.setId(1);
//		job.setJobTitle("Java Developer");
//		job.setJobDescription("Ecommerce website");
//		job.setCountry("INDIA");
//		job.setExperience(2);
//		job.setAvailability("Part-Time");
//		job.setJobType("Development");
//		job.setLanguage("TELUGU");
//		job.setPayRate(99);
//		job.setReplyRate(0);
//		job.setSkills("Java, Spring Boot, Hibernate, Micro Service, REST");
//		job.setState("AP");
//		when(jobRepo.save(job)).thenReturn(job);
//		ResponseEntity<Object> saveJob = jobServiceImpl.saveJob(job);
//		assertEquals(saveJob.getStatusCode(), HttpStatus.OK);
//	}
//}
