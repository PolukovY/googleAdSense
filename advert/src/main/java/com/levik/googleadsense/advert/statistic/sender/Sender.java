package com.levik.googleadsense.advert.statistic.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levik.googleadsense.advert.statistic.model.AdvertMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topic;

    @Autowired
    public Sender(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    public void send(AdvertMessage advertMessage) {
        LOG.info("sending {}", advertMessage);
        String payload = convertClickModelToSting(advertMessage);
        if (payload != null) {
            kafkaTemplate.send(topic, payload);
        }
    }

    public String convertClickModelToSting(AdvertMessage advertMessage) {
        String identityDetails = null;
        try {
            identityDetails = objectMapper.writeValueAsString(advertMessage);
        } catch (JsonProcessingException exe) {
            LOG.warn("Can't convert message {}", exe.getMessage(), exe);
        }

        return identityDetails;
    }

}
