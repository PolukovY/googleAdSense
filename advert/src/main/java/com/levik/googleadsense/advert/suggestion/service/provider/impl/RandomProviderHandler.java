package com.levik.googleadsense.advert.suggestion.service.provider.impl;

import com.levik.googleadsense.advert.suggestion.repository.SuggestionRepository;
import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import com.levik.googleadsense.advert.suggestion.service.provider.ProviderHandler;
import com.levik.googleadsense.advert.suggestion.utils.IntRandomNumberGeneratorUtils;
import com.levik.googleadsense.common.IdentityResponse;
import com.levik.googleadsense.common.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class RandomProviderHandler implements ProviderHandler {

    private final SuggestionRepository suggestionRepository;

    @Autowired
    public RandomProviderHandler(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public SuggestedModel handle(IdentityResponse identityResponse) {
        List<KeyWord> keyWords = identityResponse.getKeyWords();

        List<SuggestedModel> items = keyWords.stream()
                .filter(it -> suggestionRepository.getByProvider(it.getName()) != null)
                .flatMap(it -> suggestionRepository.getByProvider(it.getName()).stream())
                .collect(Collectors.toList());

        int index = IntRandomNumberGeneratorUtils.generate(items.size());
        return items.get(index);
    }

    @Override
    public String name() {
        return "random";
    }
}
