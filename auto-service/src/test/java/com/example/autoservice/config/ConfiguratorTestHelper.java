package com.example.autoservice.config;

import com.example.autoservice.entity.Auto;
import com.example.autoservice.repository.AutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;

public abstract class ConfiguratorTestHelper {
    protected static final ObjectMapper MAPPER;

    private static final int RETRIES = 2;
    private static final int BACK_OFF_PERIOD = 0;

    private static final RetryTemplate RETRY_TEMPLATE = configRetryTemplate();

    static {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
    }

    private AutoRepository autoRepository;

    protected void config(TestBeanContext context) {
        autoRepository = context.autoRepository();
    }

    protected Auto createAuto(String vin) {
        return createUser(UUID.randomUUID(), vin, "X111XX111RUS");
    }

    protected Auto createUser(
            UUID id,
            String vin,
            String stateNumber
    ) {
        Auto auto = new Auto();

        auto.setId(id);
        auto.setVin(vin);
        auto.setStateNumber(stateNumber);
        auto.setCreatedAt(OffsetDateTime.now());

        return save(autoRepository, auto);
    }



    private static RetryTemplate configRetryTemplate() {
        var retryPolicy = new SimpleRetryPolicy(
                RETRIES, Collections.singletonMap(DataIntegrityViolationException.class, Boolean.TRUE)
        );
        var backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(BACK_OFF_PERIOD);

        var retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.registerListener(new CustomRetryListener());

        return retryTemplate;
    }

    @Slf4j
    private static class CustomRetryListener extends RetryListenerSupport {

        @Override
        public <T, E extends Throwable> void onError(
                RetryContext context,
                RetryCallback<T, E> callback,
                Throwable throwable) {
            log.warn("Retrying...started current retry count is {} Retry Method {}",
                    context.getRetryCount(), context.getAttribute("context.name")
            );
            super.onError(context, callback, throwable);
        }
    }

    protected <T> T save(CrudRepository<T, UUID> repository, T entity) {
        return RETRY_TEMPLATE.execute(context -> repository.save(entity));
    }
}
