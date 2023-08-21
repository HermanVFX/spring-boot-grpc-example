package com.example.autoservice.grpc;

import auto.AutoServiceOuterClass;
import com.example.autoservice.entity.Auto;
import com.example.autoservice.repository.AutoRepository;
import com.example.autoservice.service.AutoService;
import io.grpc.stub.StreamObserver;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.OffsetDateTime;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class AutoServiceGrpc extends auto.AutoServiceGrpc.AutoServiceImplBase {

    private final AutoService autoService;

    @Override
    @Transactional
    public void createAuto(AutoServiceOuterClass.AutoRequest request,
                           StreamObserver<AutoServiceOuterClass.AutoResponse> responseObserver) {
        responseObserver.onNext(autoService.createAuto(request));
        responseObserver.onCompleted();
    }

    @Override
    public void getAutoById(AutoServiceOuterClass.Id request,
                            StreamObserver<AutoServiceOuterClass.AutoResponse> responseObserver) {
        super.getAutoById(request, responseObserver);
    }

    @Override
    public void updateAuto(AutoServiceOuterClass.Id request,
                           StreamObserver<AutoServiceOuterClass.AutoResponse> responseObserver) {
        super.updateAuto(request, responseObserver);
    }

    @Override
    public void deleteAuto(AutoServiceOuterClass.Id request,
                           StreamObserver<AutoServiceOuterClass.DeletedResponse> responseObserver) {
        super.deleteAuto(request, responseObserver);
    }
}
