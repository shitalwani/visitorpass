package com.visitorpass.visitorpass.entity;

import com.visitorpass.visitorpass.dto.RequestStatusENUM;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "visitor_pass_request")
public class VisitorPassRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer visitorPassRequestId;

    @Column(name = "raised_by_employee")
    private String raisedByEmployee;

    @Column(name = "request_raised_on")
    private Date requestRaisedOn;

    @ManyToOne(targetEntity = VisitorTypeEntity.class)
    @JoinColumn(name = "visitorTypeId" ,referencedColumnName = "visitorTypeId")
    private VisitorTypeEntity visitorTypeId;

    @Column(name = "purpose_of_visit")
    private String purposeOfVisit;

    private String requestStatus;

    @Column(name = "visit_date")
    private Date VisitDate;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column(name = "location")
    private String location;

    @Column(name = "visitor_name")
    private String visitorName;

    @Column(name = "visitor_age")
    private Integer visitorAge;

    @Column(name = "coming_from")
    private String comingFrom;

    @OneToOne(mappedBy = "visitorPassRequestEntity",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private VisitorIDProofs visitorIDProofs;
}
