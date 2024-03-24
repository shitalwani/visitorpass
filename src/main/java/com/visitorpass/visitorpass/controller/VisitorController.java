package com.visitorpass.visitorpass.controller;

import com.visitorpass.visitorpass.dto.*;
import com.visitorpass.visitorpass.entity.VisitorPassRequestEntity;
import com.visitorpass.visitorpass.entity.VisitorTypeEntity;
import com.visitorpass.visitorpass.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @PostMapping("/visitorType")
    public ResponseEntity<VisitorTypeEntity> createVisitorType(@RequestBody RequestVisitorTypeDTO requestVisitorTypeDTO){
        VisitorTypeEntity addType = visitorService.storeVisitorType(requestVisitorTypeDTO);
        return new ResponseEntity<>(addType, HttpStatus.CREATED);
    }

    @GetMapping("/getVisitorTypes")
    public ResponseEntity<List<ResponseVisitorTypeDTO>> getAllVisitorTypes(){
        List<ResponseVisitorTypeDTO> getTypes = visitorService.findAllVisitorTypes();
        return new ResponseEntity<>(getTypes,HttpStatus.OK);
    }

    @PostMapping("/addVisitorPassRequest")
    public ResponseEntity<VisitorPassRequestEntity> storeVisitorPassRequestEntity(@RequestBody RequestVisitorPassRequestDTO requestVisitorPassRequestDTO){
        VisitorPassRequestEntity addPassRequest = visitorService.addVisitorPassRequest(requestVisitorPassRequestDTO);
        return new ResponseEntity<>(addPassRequest,HttpStatus.CREATED);
    }
    @GetMapping("/get/passRequest/{location}")
    public ResponseEntity<List<ResponseVisitorPassRequestDTO>> getByLocation(@PathVariable(name = "location")String location){
        List<ResponseVisitorPassRequestDTO> visitorPassByLocation = visitorService.passRequestByLocation(location);
        return new ResponseEntity<>(visitorPassByLocation,HttpStatus.OK);
    }

    @GetMapping("/getPassRequest/{visitorPassRequestId}")
    public ResponseEntity<ResponseVisitorPassRequestDTO> getByVisitorPassRequestId(@PathVariable(name = "visitorPassRequestId")Integer visitorPassRequestId){
        ResponseVisitorPassRequestDTO getByRequestId = visitorService.getDataByVisitorPassRequestId(visitorPassRequestId);
        return new ResponseEntity<>(getByRequestId,HttpStatus.OK);
    }

    @PutMapping("/update/{visitorPassRequestId}")
    public ResponseEntity approveRejectRequest(@RequestBody UpdatePassRequestDTO updatePassRequestDTO,@PathVariable(name = "visitorPassRequestId")Integer visitorPassRequestId){
        VisitorPassRequestEntity updateRequest = visitorService.updateApproveRejectRequest(updatePassRequestDTO,visitorPassRequestId);
    return new ResponseEntity<>(updateRequest,HttpStatus.OK);
    }

}
