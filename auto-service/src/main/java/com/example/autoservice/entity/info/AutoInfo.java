package com.example.autoservice.entity.info;

import java.util.List;

public interface AutoInfo {
    Long getId();
    String getVin();
    String getStateNumber();
    Long getDriverId();
    String getMake();
    Integer getAge();
    List<String> getDetailCode();
}
