package com.nwn.crafts.controllers;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.Job;
import com.nwn.crafts.core.models.ProfileNotFoundException;
import com.nwn.crafts.core.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/jobs")
public class JobController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private JobService jobService;


    @Operation(summary = "Get all jobs", description = "Returns the list of all jobs in Database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No job found")
    })
    @GetMapping("/")
    public List<Job> list() {
        return jobService.findAll();
    }


    @Operation(summary = "Get a job Object by job", description = "Returns a job Object from his job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No job Object was found for this job id")
    })
    @GetMapping("{job}")
    public ResponseEntity<Job> get(@PathVariable Long job) {
        try {
            return ResponseEntity.ok(jobService.findById(job));
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Create new Job Object", description = "Create and save new Job Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "No Job is created")
    })
    @PostMapping("/create")
    public Job create(@RequestBody final Job job) {
        return jobService.addJob(job);
    }


    @Operation(summary = "Update Job", description = "Update an existing Job's information from his job id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not found - No Job Object was found for this Job's id")
    })
    @PutMapping("/update/{jobId}")
    public Job update(@PathVariable Long jobId, @RequestBody final Job job) {
        try {
            return jobService.updateJob(jobId, job);
        } catch (CraftsException e) {
            logger.error("Error updating job Object with id: {}", jobId);
            throw new ProfileNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete Job", description = "Delete an existing Job's Object from his job's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No Job's Object was found for this job's id")
    })
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> delete(@PathVariable Long jobId) {
        jobService.deleteById(jobId);
        return ResponseEntity.ok("job deleted successfully");
    }
}

