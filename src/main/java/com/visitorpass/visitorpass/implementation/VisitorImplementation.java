package com.visitorpass.visitorpass.implementation;

import com.visitorpass.visitorpass.dto.*;
import com.visitorpass.visitorpass.entity.VisitorIDProofs;
import com.visitorpass.visitorpass.entity.VisitorPassRequestEntity;
import com.visitorpass.visitorpass.entity.VisitorTypeEntity;
import com.visitorpass.visitorpass.exception.ApplicationException;
import com.visitorpass.visitorpass.repository.VisitorIDProofsRepository;
import com.visitorpass.visitorpass.repository.VisitorPassRequestRepository;
import com.visitorpass.visitorpass.repository.VisitorRepository;
import com.visitorpass.visitorpass.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorImplementation implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    VisitorPassRequestRepository visitorPassRequestRepository;

    @Autowired
    VisitorIDProofsRepository visitorIDProofsRepository;

    @Override
    public VisitorTypeEntity storeVisitorType(RequestVisitorTypeDTO requestVisitorTypeDTO) {
        VisitorTypeEntity visitorTypeEntity = new VisitorTypeEntity();
        visitorTypeEntity.setVisitorType(requestVisitorTypeDTO.getVisitorType());
        return visitorRepository.save(visitorTypeEntity);
    }

    @Override
    public List<ResponseVisitorTypeDTO> findAllVisitorTypes() {
        List<VisitorTypeEntity> visitorTypeEntityList = visitorRepository.findAll();

        List<ResponseVisitorTypeDTO> responseVisitorTypeDTOList = new ArrayList<>();
        visitorTypeEntityList.forEach(visitorTypeEntity -> {
            ResponseVisitorTypeDTO responseVisitorTypeDTO = new ResponseVisitorTypeDTO();
            responseVisitorTypeDTO.setVisitorTypeId(visitorTypeEntity.getVisitorTypeId());
            responseVisitorTypeDTO.setVisitorType(visitorTypeEntity.getVisitorType());
            responseVisitorTypeDTOList.add(responseVisitorTypeDTO);
        });

        return responseVisitorTypeDTOList;
    }

    @Override
    public VisitorPassRequestEntity addVisitorPassRequest(RequestVisitorPassRequestDTO requestVisitorPassRequestDTO) {
        VisitorPassRequestEntity visitorPassRequestEntity = new VisitorPassRequestEntity();


        visitorPassRequestEntity.setRaisedByEmployee(requestVisitorPassRequestDTO.getRaisedByEmployee());
        visitorPassRequestEntity.setRequestRaisedOn(requestVisitorPassRequestDTO.getRequestRaisedOn());

        visitorPassRequestEntity.setRequestStatus(String.valueOf((requestVisitorPassRequestDTO.getRequestStatus())));

        visitorPassRequestEntity.setVisitorAge(requestVisitorPassRequestDTO.getVisitorAge());
        visitorPassRequestEntity.setVisitDate(requestVisitorPassRequestDTO.getVisitDate());
        visitorPassRequestEntity.setVisitorName(requestVisitorPassRequestDTO.getVisitorName());

        visitorPassRequestEntity.setLocation(requestVisitorPassRequestDTO.getLocation());
        visitorPassRequestEntity.setComingFrom(requestVisitorPassRequestDTO.getComingFrom());
        visitorPassRequestEntity.setCancellationReason(requestVisitorPassRequestDTO.getCancellationReason());
        visitorPassRequestEntity.setPurposeOfVisit(requestVisitorPassRequestDTO.getPurposeOfVisit());


        Optional<VisitorTypeEntity> visitorTypeEntity = visitorRepository.findById(requestVisitorPassRequestDTO.getVisitorTypeId());
        if(visitorTypeEntity.isEmpty()){
            throw new RuntimeException("no visitor entry available!");
        }
        visitorPassRequestEntity.setVisitorTypeId(visitorTypeEntity.get());

        VisitorIDProofs visitorIDProofs = new VisitorIDProofs();
        visitorIDProofs.setIDProofNumber(requestVisitorPassRequestDTO.getIdProofNumber());
        visitorIDProofs.setIdProofType(String.valueOf((requestVisitorPassRequestDTO.getIDProofType())));
        visitorIDProofs.setVisitorPassRequestEntity(visitorPassRequestEntity);
        visitorIDProofsRepository.save(visitorIDProofs);

        return visitorPassRequestRepository.save(visitorPassRequestEntity);
    }

    @Override
    public List<ResponseVisitorPassRequestDTO> passRequestByLocation(String location) {
        List<ResponseVisitorPassRequestDTO> responseVisitorPassRequestDTOList = new ArrayList<>();
        try {
            List<VisitorPassRequestEntity> visitorPassRequestEntityList = visitorPassRequestRepository.findByLocation(location);
            if(visitorPassRequestEntityList.isEmpty()){
                throw new ApplicationException("record not found for location :"+location,HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
            }
          //  List<ResponseVisitorPassRequestDTO> responseVisitorPassRequestDTOList = new ArrayList<>();
            visitorPassRequestEntityList.forEach(visitorPassRequestEntity -> {
                ResponseVisitorPassRequestDTO responseVisitorPassRequestDTO = new ResponseVisitorPassRequestDTO();

                responseVisitorPassRequestDTO.setVisitorPassRequestId((visitorPassRequestEntity.getVisitorPassRequestId()));
                responseVisitorPassRequestDTO.setRaisedByEmployee(visitorPassRequestEntity.getRaisedByEmployee());
                responseVisitorPassRequestDTO.setRequestRaisedOn(visitorPassRequestEntity.getRequestRaisedOn());

                responseVisitorPassRequestDTO.setRequest_Status(String.valueOf(visitorPassRequestEntity.getRequestStatus()));

                responseVisitorPassRequestDTO.setVisitorAge(visitorPassRequestEntity.getVisitorAge());
                responseVisitorPassRequestDTO.setVisitorName(visitorPassRequestEntity.getVisitorName());
                responseVisitorPassRequestDTO.setVisitorTypeId(visitorPassRequestEntity.getVisitorTypeId());
                responseVisitorPassRequestDTO.setCancellationReason(visitorPassRequestEntity.getCancellationReason());
                responseVisitorPassRequestDTO.setComingFrom(visitorPassRequestEntity.getComingFrom());
                responseVisitorPassRequestDTO.setPurposeOfVisit(visitorPassRequestEntity.getPurposeOfVisit());
                responseVisitorPassRequestDTO.setVisitDate(visitorPassRequestEntity.getVisitDate());
                responseVisitorPassRequestDTOList.add(responseVisitorPassRequestDTO);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseVisitorPassRequestDTOList;
    }

    @Override
    public ResponseVisitorPassRequestDTO getDataByVisitorPassRequestId(Integer visitorPassRequestId) {

        ResponseVisitorPassRequestDTO responseVisitorPassRequestDTO = new ResponseVisitorPassRequestDTO();
    try {
        Optional<VisitorPassRequestEntity> visitorPassRequestEntity = visitorPassRequestRepository.findById(visitorPassRequestId);
        if (visitorPassRequestEntity.isEmpty()) {
            throw new ApplicationException("Record not found for Id :" + visitorPassRequestId, HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST);
        }

        responseVisitorPassRequestDTO.setVisitorAge(visitorPassRequestEntity.get().getVisitorAge());
        responseVisitorPassRequestDTO.setVisitorName(visitorPassRequestEntity.get().getVisitorName());
        responseVisitorPassRequestDTO.setRequest_Status(String.valueOf(visitorPassRequestEntity.get().getRequestStatus()));
        responseVisitorPassRequestDTO.setRequestRaisedOn(visitorPassRequestEntity.get().getRequestRaisedOn());
        responseVisitorPassRequestDTO.setVisitDate(visitorPassRequestEntity.get().getVisitDate());
        responseVisitorPassRequestDTO.setPurposeOfVisit(visitorPassRequestEntity.get().getPurposeOfVisit());
        responseVisitorPassRequestDTO.setVisitorTypeId(visitorPassRequestEntity.get().getVisitorTypeId());
        responseVisitorPassRequestDTO.setComingFrom(visitorPassRequestEntity.get().getComingFrom());
        responseVisitorPassRequestDTO.setCancellationReason(visitorPassRequestEntity.get().getCancellationReason());
        responseVisitorPassRequestDTO.setRaisedByEmployee(visitorPassRequestEntity.get().getRaisedByEmployee());
    }catch (Exception e){
        e.printStackTrace();
    }
        return responseVisitorPassRequestDTO;
    }

    @Override
    public VisitorPassRequestEntity updateApproveRejectRequest(UpdatePassRequestDTO updatePassRequestDTO, Integer visitorPassRequestId) {

        Optional<VisitorPassRequestEntity> visitorPassRequestEntity = visitorPassRequestRepository.findById(visitorPassRequestId);
        if (visitorPassRequestEntity.isEmpty()) {
            throw new RuntimeException("Record not found for Id: " + visitorPassRequestId);
        }
        VisitorPassRequestEntity visitorPassRequestEntityOptional = visitorPassRequestEntity.get();
        visitorPassRequestEntityOptional.setRequestStatus(updatePassRequestDTO.getRequestStatus());
        visitorPassRequestEntityOptional.setVisitorPassRequestId(updatePassRequestDTO.getVisitorPassRequestId());
        visitorPassRequestEntityOptional.setCancellationReason(updatePassRequestDTO.getCancellationReason());

        return visitorPassRequestRepository.save(visitorPassRequestEntityOptional);
    }

}
