package com.nwn.crafts.core.services;


import com.nwn.crafts.core.models.AuditLine;
import com.nwn.crafts.core.models.AuditType;
import com.nwn.crafts.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.String.format;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public void saveAudit(String object, String userLogin, AuditType auditType, String objectJSON, boolean success) {
        var auditLine = new AuditLine();
        auditLine.setLogin(userLogin);
        auditLine.setEventTimeStamp(new Date());
        auditLine.setActionId(auditType.getAction());
        auditLine.setDescription(success ? format(auditType.getDescription(), object) : format(auditType.getDescriptionKO(), object));
        auditLine.setObjectJSON(objectJSON);
        auditRepository.save(auditLine);

    }
}
