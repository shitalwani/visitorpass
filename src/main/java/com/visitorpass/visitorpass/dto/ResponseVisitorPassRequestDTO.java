package com.visitorpass.visitorpass.dto;

import com.visitorpass.visitorpass.entity.VisitorIDProofs;
import com.visitorpass.visitorpass.entity.VisitorTypeEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseVisitorPassRequestDTO {

    private Integer visitorPassRequestId;
    private String raisedByEmployee;
    private Date requestRaisedOn;
    private VisitorTypeEntity visitorTypeId;
    private String purposeOfVisit;
    private String Request_Status;
    private Date VisitDate;
    private String cancellationReason;
    private String visitorName;
    private Integer visitorAge;
    private String comingFrom;


//    @OneToOne(mappedBy = "visitorPassRequestEntity",cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private VisitorIDProofs visitorIDProofs;

}
