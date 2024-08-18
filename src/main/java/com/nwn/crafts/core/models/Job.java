package com.nwn.crafts.core.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;
    private String nomJob;
    private String title;
    private String picture;
}
