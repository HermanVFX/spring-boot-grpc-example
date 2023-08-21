package com.example.mainservice.controller;

import com.example.mainservice.dto.AutoRequest;
import com.example.mainservice.dto.AutoResponse;
import com.example.mainservice.dto.PageAutoResponse;
import com.example.mainservice.service.grpc.AutoServiceGrpcImpl;
import com.example.mainserviceapi.controller.AutoController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    public ResponseEntity<Void> deleteAuto(Long id) {
        autoService.deleteAuto(id);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PageAutoResponse> getAllAuto(@NotNull Integer page, @NotNull Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(autoService.getAllAuto(pageable), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AutoResponse> getAutoById(Long id) {
        return new ResponseEntity<>(autoService.getAutoById(id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<AutoResponse> updateAuto(Long id, AutoRequest autoRequest) {
        return new ResponseEntity<>(autoService.updateAuto(id, autoRequest), HttpStatus.CREATED);
    }
}
