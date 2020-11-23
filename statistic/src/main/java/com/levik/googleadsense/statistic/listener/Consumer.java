package com.levik.googleadsense.statistic.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levik.googleadsense.statistic.service.InMemoryStoreService;
import com.levik.googleadsense.statistic.service.model.AdvertMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    private final InMemoryStoreService inMemoryStoreService;
    private final ObjectMapper objectMapper;

    public Consumer(InMemoryStoreService inMemoryStoreService, ObjectMapper objectMapper) {
        this.inMemoryStoreService = inMemoryStoreService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${kafka.topic}")
    public void receive(String payload) {
        LOG.info("received payload='{}'", payload);

        AdvertMessage advertMessage = getAdvertMessage(payload);
        if (advertMessage != null) {
            inMemoryStoreService.addItem(advertMessage);
        }
    }

    public AdvertMessage getAdvertMessage(String payload) {
        try {
            return objectMapper.readValue(payload, AdvertMessage.class);
        } catch (JsonProcessingException exe) {
            LOG.warn("Can't get advert message {}", exe.getMessage());
        }

        return null;
    }
}
