package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.Job;
import com.nwn.crafts.core.models.JobNotFoundException;
import com.nwn.crafts.core.models.UserNotFoundException;
import com.nwn.crafts.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
@Service
public class JobService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(format("No Job found for this Job id: %s", jobId)));
    }

    public Job addJob(Job job) {
        return jobRepository.saveAndFlush(job);
    }

    public void deleteById(Long jobId) {
        Objects.requireNonNull(jobId, "Job Id can not be null");
        if (findById(jobId) != null) {
            jobRepository.deleteById(jobId);
        } else {
            throw new UserNotFoundException("le Job " + jobId + " est introuvable");
        }
    }

    public Job updateJob(Long jobId, Job Job) throws CraftsException {
        Objects.requireNonNull(Job);
        logger.debug("Updating Job with id : {}", jobId);
        Job current = findById(jobId);
        if (current != null) {
            BeanUtils.copyProperties(Job, current, "Job");
        } else {
            throw new CraftsException(format("No Job found with id: %s", jobId));
        }
        return jobRepository.saveAndFlush(current);
    }
}
