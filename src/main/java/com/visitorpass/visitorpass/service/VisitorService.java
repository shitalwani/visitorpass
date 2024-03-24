package com.visitorpass.visitorpass.service;

import com.visitorpass.visitorpass.dto.*;
import com.visitorpass.visitorpass.entity.VisitorPassRequestEntity;
import com.visitorpass.visitorpass.entity.VisitorTypeEntity;

import java.util.List;

public interface VisitorService {
    VisitorTypeEntity storeVisitorType(RequestVisitorTypeDTO requestVisitorTypeDTO);

    List<ResponseVisitorTypeDTO> findAllVisitorTypes();

    VisitorPassRequestEntity addVisitorPassRequest(RequestVisitorPassRequestDTO requestVisitorPassRequestDTO);

    List<ResponseVisitorPassRequestDTO> passRequestByLocation(String location);

    ResponseVisitorPassRequestDTO getDataByVisitorPassRequestId(Integer visitorPassRequestId);

    VisitorPassRequestEntity updateApproveRejectRequest(UpdatePassRequestDTO updatePassRequestDTO, Integer visitorPassRequestId);
}
