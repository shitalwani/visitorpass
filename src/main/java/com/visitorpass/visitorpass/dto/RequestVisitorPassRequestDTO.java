package com.visitorpass.visitorpass.dto;

import com.visitorpass.visitorpass.entity.VisitorIDProofs;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
public class RequestVisitorPassRequestDTO {
    private String raisedByEmployee;
    private Date requestRaisedOn;
    private Integer visitorTypeId;
    private String purposeOfVisit;

    @Enumerated(EnumType.STRING)
    private RequestStatusENUM.REQUEST_STATUS requestStatus;

    private Date VisitDate;
    private String cancellationReason;
    private String location;
    private String visitorName;
    private Integer visitorAge;
    private String comingFrom;

    @Enumerated(EnumType.STRING)
    private RequestStatusENUM.ID_PROOFS_TYPES IDProofType;
    private String IdProofNumber;
}
