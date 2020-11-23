package com.levik.googleadsense.statistic.service;

import com.levik.googleadsense.statistic.service.model.AdvertMessage;
import com.levik.googleadsense.statistic.service.model.StatisticDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class InMemoryStoreService {

    private final Map<Long, StatisticDetails> store;

    public InMemoryStoreService() {
        this.store = new HashMap<>();
    }

    public void addItem(AdvertMessage message) {
        StatisticDetails statisticDetails = store.getOrDefault(message.getIdentity(), new StatisticDetails());

        AdvertMessage.Action action = message.getAction();

        if (AdvertMessage.Action.SHOW == action) {
            statisticDetails.getShow().increment();
        } else {
            statisticDetails.getClick().increment();
        }

        store.put(message.getIdentity(), statisticDetails);
    }

    public Map<Long, StatisticDetails> getStore() {
        return store;
    }
}
