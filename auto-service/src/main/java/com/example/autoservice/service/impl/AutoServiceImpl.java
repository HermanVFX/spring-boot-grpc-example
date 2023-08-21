package com.example.autoservice.service.impl;

import auto.AutoServiceOuterClass;
import com.example.autoservice.entity.Auto;
import com.example.autoservice.repository.AutoRepository;
import com.example.autoservice.service.AutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AutoServiceImpl implements AutoService {

    private final AutoRepository autoRepository;

    @Override
    public AutoServiceOuterClass.AutoResponse createAuto(AutoServiceOuterClass.AutoRequest request) {
        Auto auto = new Auto();
        auto.setCreatedAt(OffsetDateTime.now());
        auto.setVin(request.getVin());
        auto.setStateNumber(request.getStateNumber());

        var response = autoRepository.save(auto);

        return AutoServiceOuterClass.AutoResponse.newBuilder()
                .setId(response.getId())
                .setVin(response.getVin())
                .setStateNumber(response.getStateNumber())
                .build();
    }
}
