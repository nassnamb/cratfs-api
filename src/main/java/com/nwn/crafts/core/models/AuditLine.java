package com.nwn.crafts.core.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "AUDIT_LINES")
public class AuditLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //sequence auto-generated numbers
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_TIMESTAMP", nullable = false)
    private Date eventTimeStamp;

    @Column(name = "ACTION_ID", nullable = false)
    private String actionId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OBJECTS_JSON")
    private String objectJSON;

}
