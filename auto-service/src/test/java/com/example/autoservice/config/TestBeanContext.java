package com.example.autoservice.config;

import com.example.autoservice.repository.AutoRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestBeanContext {
    AutoRepository autoRepository;
}
