package com.example.mainservice.controller;

import com.example.mainservice.dto.AutoRequest;
import com.example.mainservice.dto.AutoResponse;
import com.example.mainservice.dto.DriverResponse;
import com.example.mainservice.service.grpc.AutoServiceGrpcImpl;
import com.example.mainserviceapi.controller.AutoController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AutoControllerImpl implements AutoController {

    private final AutoServiceGrpcImpl autoService;

    @Override
    public ResponseEntity<AutoResponse> createAuto(AutoRequest autoRequest) {
        return new ResponseEntity<>(autoService.createAuto(autoRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteAuto(UUID id) {
        return new ResponseEntity<>(autoService.deleteAuto(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AutoResponse> getAutoById(UUID id) {
        return new ResponseEntity<>(autoService.getAutoById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AutoResponse> updateAuto(UUID id, AutoRequest autoRequest) {
        return new ResponseEntity<>(autoService.updateAuto(id, autoRequest), HttpStatus.CREATED);
    }
}
