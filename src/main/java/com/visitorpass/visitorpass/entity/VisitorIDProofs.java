package com.visitorpass.visitorpass.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.visitorpass.visitorpass.dto.RequestStatusENUM;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "visitor_id_proofs")
public class VisitorIDProofs {

    @Id
    @Column(name = "request_id")
    private Integer requestId;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    @MapsId
    @JoinColumn(name = "visitorPassRequestId")
    private VisitorPassRequestEntity visitorPassRequestEntity;


    private String IdProofType;

    private String IDProofNumber;
}
