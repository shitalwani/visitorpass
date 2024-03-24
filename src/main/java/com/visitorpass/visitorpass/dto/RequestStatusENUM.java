package com.visitorpass.visitorpass.dto;

import jakarta.persistence.Enumerated;

public class RequestStatusENUM {

    public enum REQUEST_STATUS{
        PENDING,
        APPROVED,
        REJECTED
    }
    public enum ID_PROOFS_TYPES{
        AADHAR ,
        VOTERID,
        PASSPORT

    }
}
