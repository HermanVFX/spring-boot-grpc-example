package com.example.mainservice.service.grpc;

import com.example.mainservice.dto.AutoRequest;
import com.example.mainservice.dto.AutoResponse;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import auto.AutoServiceGrpc;

import java.util.UUID;

@Service
public class AutoServiceGrpcImpl {

    @GrpcClient("GLOBAL")
    AutoServiceGrpc.AutoServiceBlockingStub autoServiceBlockingStub;

    @Transactional
    public AutoResponse createAuto(AutoRequest autoRequest) {
        return generatedAutoResponse(autoServiceBlockingStub.createAuto(generatedAutoRequest(autoRequest)));
    }

    public Void deleteAuto(UUID id) {
        return null;
    }

    public AutoResponse getAutoById(UUID id) {
        return null;
    }

    public AutoResponse updateAuto(UUID id, AutoRequest autoRequest) {
        return null;
    }

    private auto.AutoServiceOuterClass.AutoRequest generatedAutoRequest(AutoRequest autoRequest) {
        return auto.AutoServiceOuterClass.AutoRequest.newBuilder()
                .setVin(autoRequest.getVin())
                .setStateNumber(autoRequest.getStateNumber())
                .build();
    }

    private AutoResponse generatedAutoResponse(auto.AutoServiceOuterClass.AutoResponse autoResponse) {
        var response = new AutoResponse();
        response.setId(UUID.fromString(autoResponse.getId()));
        response.setVin(autoResponse.getVin());
        response.setStateNumber(autoResponse.getStateNumber());
        return response;
    }
}
