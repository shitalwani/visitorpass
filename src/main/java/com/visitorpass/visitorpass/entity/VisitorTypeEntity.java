package com.visitorpass.visitorpass.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "visitor_type")
public class VisitorTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitorTypeId;

    @Column(name = "visitor_type")
    private String visitorType;
}
