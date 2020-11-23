package com.levik.googleadsense.advert.suggestion.service.provider.impl;

import com.levik.googleadsense.advert.suggestion.repository.SuggestionRepository;
import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import com.levik.googleadsense.advert.suggestion.service.provider.ProviderHandler;
import com.levik.googleadsense.common.IdentityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultProviderHandler implements ProviderHandler {

    private final SuggestionRepository suggestionRepository;

    @Autowired
    public DefaultProviderHandler(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public SuggestedModel handle(IdentityResponse identityResponse) {
        return suggestionRepository.getById(1L);
    }

    @Override
    public String name() {
        return "default";
    }
}
