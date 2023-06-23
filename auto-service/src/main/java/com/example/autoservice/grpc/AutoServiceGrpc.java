package com.example.autoservice.grpc;

import auto.AutoServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AutoServiceGrpc extends auto.AutoServiceGrpc.AutoServiceImplBase {

    @Override
    public void createAuto(AutoServiceOuterClass.AutoRequest request,
                           StreamObserver<AutoServiceOuterClass.AutoResponse> responseObserver) {
        super.createAuto(request, responseObserver);
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
