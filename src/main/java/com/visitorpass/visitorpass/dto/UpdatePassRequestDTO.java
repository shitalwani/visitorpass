package com.visitorpass.visitorpass.dto;

import lombok.Data;

@Data
public class UpdatePassRequestDTO {

    private Integer visitorPassRequestId;
    private String requestStatus;
    private String cancellationReason;
}
