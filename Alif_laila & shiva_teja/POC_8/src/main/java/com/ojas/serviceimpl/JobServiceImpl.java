package com.ojas.serviceimpl;

import static com.ojas.constants.Constants.GET_ALLJOBS_DATA;
import static com.ojas.constants.Constants.GET_BY_AVAILABILITY;
import static com.ojas.constants.Constants.GET_BY_COUNTRY;
import static com.ojas.constants.Constants.GET_BY_EXPERIENCE;
import static com.ojas.constants.Constants.GET_BY_JOBID;
import static com.ojas.constants.Constants.GET_BY_JOBTYPE;
import static com.ojas.constants.Constants.GET_BY_LANGUAGE;
import static com.ojas.constants.Constants.GET_BY_PAYRATE;
import static com.ojas.constants.Constants.GET_BY_SKILLS;
import static com.ojas.constants.Constants.INVALID_FIELDS;
import static com.ojas.constants.Constants.INVALID_REQUEST;
import static com.ojas.constants.Constants.JOB_SAVE;
import static com.ojas.constants.Constants.RECORD_NOT_FOUND;
import static com.ojas.constants.Constants.SUCCESS;
import static com.ojas.constants.Constants.SUCCESS_STATUS;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.exception.CustomException;
import com.ojas.exception.Response;
import com.ojas.model.Job;
import com.ojas.repositories.JobRepository;
import com.ojas.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepo;

	static Logger log = Logger.getLogger(JobServiceImpl.class.getName());

	@Override
	public ResponseEntity<Object> saveJob(Job job) { 
		log.debug("Incoming request job service : " + job);

		if (job == null) {
			log.error("Invalid request");
			return new ResponseEntity<>(new CustomException(INVALID_REQUEST), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if ((job.getJobType() == null || job.getJobType().isEmpty())
				|| (job.getJobTitle() == null || job.getJobTitle().isEmpty()) || (job.getExperience() == null)
				|| (job.getAvailability() == null || job.getAvailability().isEmpty())
				|| (job.getJobDescription() == null || job.getJobDescription().isEmpty())
				|| (job.getCountry() == null || job.getCountry().isEmpty())
				|| (job.getLanguage() == null || job.getLanguage().isEmpty())
				|| (job.getState() == null || job.getState().isEmpty()) || (job.getReplyRate() == null)
				) {
			log.error("Fields should not be null");
			throw new CustomException(INVALID_FIELDS);
		}

		Job save = jobRepo.save(job);
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(JOB_SAVE);
		response.setTimestamp(new Date());
		response.setData(save);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getByJobId(Long id) {
		log.debug("Incoming request job service id method : " + id);
		if (id == null || id == 0) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		Optional<Job> findById = jobRepo.findById(id);
		if (!findById.isPresent()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_JOBID);
		response.setTimestamp(new Date());
		response.setData(findById);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getByJobType(String jobType) {
		log.debug("Incoming request job service type method : " + jobType);

		if (jobType == null || jobType.isEmpty()) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		List<Job> findByJobType = jobRepo.findByJobType(jobType);
		if (findByJobType.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_JOBTYPE);
		response.setTimestamp(new Date());
		response.setData(findByJobType);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getByExperience(Integer experience) {
		log.debug("Incoming request job service experience method : " + experience);

		if (experience == null) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		List<Job> findByExperience = jobRepo.findByExperience(experience);
		if (findByExperience.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_EXPERIENCE);
		response.setTimestamp(new Date());
		response.setData(findByExperience);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getByCountry(String country) {
		log.debug("Incoming request job service country method : " + country);

		if (country == null || country.isEmpty()) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		List<Job> findByCountry = jobRepo.findByCountry(country);
		if (findByCountry.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_COUNTRY);
		response.setTimestamp(new Date());
		response.setData(findByCountry);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getByAvailability(String availability) {
		log.debug("Incoming request job service availability method : " + availability);
		if (availability == null || availability.isEmpty()) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		List<Job> findByAvailability = jobRepo.findByAvailability(availability);
		if (findByAvailability.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_AVAILABILITY);
		response.setTimestamp(new Date());
		response.setData(findByAvailability);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getByLanguage(String language) {
		log.debug("Incoming request job service language method : " + language);

		if (language == null || language.isEmpty()) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		List<Job> findByLanguage = jobRepo.findByLanguage(language);
		if (findByLanguage.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_LANGUAGE);
		response.setTimestamp(new Date());
		response.setData(findByLanguage);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllJobs(int pageNo, int pageSize) {
		log.debug("Incoming request job service getalljob method ");
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
		List<Job> findAllJobs = jobRepo.findAll(pageRequest).toList();
		if (findAllJobs.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_ALLJOBS_DATA);
		response.setTimestamp(new Date());
		response.setData(findAllJobs);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getBySkills(String skills) {
		log.debug("Incoming request job service skills method : " + skills);
		if (skills == null || skills.isEmpty()) {
			log.error("Invalid request");
			throw new CustomException(INVALID_FIELDS);
		}
		List<Job> findBySkills = jobRepo.findBySkills(skills);
		if (findBySkills.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_SKILLS);
		response.setTimestamp(new Date());
		response.setData(findBySkills);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getByPayRate(int low, int high) {
		log.debug("Incoming request job service payrate method : " + low + high);
		List<Job> findByPayRate = jobRepo.findByPayRateBetween(low, high);
		if (findByPayRate.isEmpty()) {
			log.error("Record not found");
			throw new CustomException(RECORD_NOT_FOUND);
		}
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(GET_BY_PAYRATE);
		response.setTimestamp(new Date());
		response.setData(findByPayRate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
