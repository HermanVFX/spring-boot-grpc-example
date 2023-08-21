package com.example.mainservice.service.grpc;

import auto.AutoServiceGrpc;
import auto.AutoServiceOuterClass;
import com.example.mainservice.dto.AutoRequest;
import com.example.mainservice.dto.AutoResponse;
import com.example.mainservice.dto.PageAutoResponse;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AutoServiceGrpcImpl {

    @GrpcClient("GLOBAL")
    AutoServiceGrpc.AutoServiceBlockingStub autoServiceBlockingStub;

    public AutoResponse createAuto(AutoRequest autoRequest) {
        log.info("AutoRequest: {" + autoRequest.toString() + "}" );
        return generatedAutoResponse(autoServiceBlockingStub.createAuto(generatedAutoRequest(autoRequest)));
    }

    public void deleteAuto(Long id) {
        autoServiceBlockingStub.deleteAuto(AutoServiceOuterClass.Id.newBuilder().setId(id).build());
    }

    public AutoResponse getAutoById(Long id) {
        return generatedAutoResponse(autoServiceBlockingStub.getAutoById(
                AutoServiceOuterClass.Id.newBuilder().setId(id).build()));
    }

    public AutoResponse updateAuto(Long id, AutoRequest autoRequest) {
        return generatedAutoResponse(autoServiceBlockingStub.updateAuto(AutoServiceOuterClass.UpdateAutoRequest.newBuilder()
                        .setId(id)
                        .setAuto(AutoServiceOuterClass.AutoRequest.newBuilder()
                                .setStateNumber(autoRequest.getStateNumber())
                                .setVin(autoRequest.getVin())
                                .setAge(autoRequest.getAge().intValue())
                                .setMake(autoRequest.getMake())
                                .build())
                .build()));
    }

    public PageAutoResponse getAllAuto(Pageable pageable) {
        return generatedPageAutoResponse(autoServiceBlockingStub.getAllAuto(
                AutoServiceOuterClass.Pageble.newBuilder()
                        .setPage(pageable.getPageNumber())
                        .setSize(pageable.getPageSize())
                        .build()));
    }

    private auto.AutoServiceOuterClass.AutoRequest generatedAutoRequest(AutoRequest autoRequest) {
        return auto.AutoServiceOuterClass.AutoRequest.newBuilder()
                .setVin(autoRequest.getVin())
                .setStateNumber(autoRequest.getStateNumber())
                .build();
    }

    private AutoResponse generatedAutoResponse(auto.AutoServiceOuterClass.AutoResponse autoResponse) {
        var response = new AutoResponse();
        response.setId(autoResponse.getId());
        response.setVin(autoResponse.getVin());
        response.setStateNumber(autoResponse.getStateNumber());
        return response;
    }

    private PageAutoResponse generatedPageAutoResponse(AutoServiceOuterClass.PageAutoResponse pageAutoResponse) {
        var response = new PageAutoResponse();

        List<AutoResponse> items = new ArrayList<>();

        for (auto.AutoServiceOuterClass.AutoResponse autoResponse : pageAutoResponse.getItemsList()) {
            items.add(generatedAutoResponse(autoResponse));
        }

        response.setItems(items);
        response.setAllPages(BigDecimal.valueOf(pageAutoResponse.getAllPages()));
        response.setCurrentPage(BigDecimal.valueOf(pageAutoResponse.getCurrentPage()));
        return response;
    }
}
