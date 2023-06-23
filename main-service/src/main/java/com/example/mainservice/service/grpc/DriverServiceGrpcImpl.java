package com.example.mainservice.service.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import driver.DriverServiceGrpc;

@Service
public class DriverServiceGrpcImpl {

    @GrpcClient("GLOBAL")
    DriverServiceGrpc.DriverServiceBlockingStub driverServiceBlockingStub;
}
