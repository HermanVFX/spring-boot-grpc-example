package com.example.autoservice.service;

import auto.AutoServiceOuterClass;

public interface AutoService {
    AutoServiceOuterClass.AutoResponse createAuto(AutoServiceOuterClass.AutoRequest request);
}
