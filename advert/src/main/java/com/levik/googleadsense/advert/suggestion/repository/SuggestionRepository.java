package com.levik.googleadsense.advert.suggestion.repository;

import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SuggestionRepository {


    private final Map<String, List<SuggestedModel>> suggestedItems;
    private final Map<Long, SuggestedModel> transform;
    private final String gateway;

    public SuggestionRepository(@Value("${gateway.host}") String gateway) {
        this.gateway = gateway;
        this.suggestedItems = new HashMap<>();

        //TODO move to database and create UI for registration
        suggestedItems.put("Default",
                Collections.singletonList(new SuggestedModel(1L, "Google", gateway, "http://google.com.ua"))
        );

        suggestedItems.put("News", Arrays.asList(
                new SuggestedModel(2L, "News-One", gateway, "http://news-one.com"),
                new SuggestedModel(3L, "News-two", gateway, "http://news-two.com"))
        );

        suggestedItems.put("Ping-Pong", Arrays.asList(
                new SuggestedModel(4L, "Ping-Pong-One", gateway, "http://ping-pong-one.com"),
                new SuggestedModel(5L, "Ping-Pong-two", gateway, "http://ping-pong-two.com"))
        );

        suggestedItems.put("Sport",
                Collections.singletonList(new SuggestedModel(6L, "Sport", gateway, "http://sport-one.com"))
        );

        this.transform = suggestedItems.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(SuggestedModel::getIdentity, it -> it));
    }

    public List<SuggestedModel> getByProvider(String provider) {
        return suggestedItems.getOrDefault(provider, new ArrayList<>());
    }

    public SuggestedModel getById(Long identity) {
        return transform.get(identity);
    }
}
