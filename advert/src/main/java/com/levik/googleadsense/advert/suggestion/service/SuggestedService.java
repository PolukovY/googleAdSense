package com.levik.googleadsense.advert.suggestion.service;

import com.levik.googleadsense.advert.suggestion.repository.SuggestionRepository;
import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import com.levik.googleadsense.advert.suggestion.service.provider.ProviderHandler;
import com.levik.googleadsense.advert.suggestion.service.provider.impl.DefaultProviderHandler;
import com.levik.googleadsense.common.IdentityResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SuggestedService {

    private final DefaultProviderHandler defaultProviderHandler;
    private final SuggestionRepository suggestionRepository;
    private final Map<String, ProviderHandler> handlers;

    public SuggestedService(DefaultProviderHandler defaultProviderHandler,
                            SuggestionRepository suggestionRepository,
                            List<ProviderHandler> providerHandlers) {
        this.defaultProviderHandler = defaultProviderHandler;
        this.suggestionRepository = suggestionRepository;
        this.handlers = providerHandlers.stream().collect(Collectors.toMap(ProviderHandler::name, it -> it));
    }

    public SuggestedModel getSuggested(IdentityResponse identityResponse) {
        String identity = (identityResponse != null &&identityResponse.getProvider() != null) ? identityResponse.getProvider() : "default";
        ProviderHandler providerHandler = handlers.getOrDefault(identity, defaultProviderHandler);
        return providerHandler.handle(identityResponse);
    }

    public SuggestedModel getUrl(Long advertIdentity) {
        return suggestionRepository.getById(advertIdentity);
    }
}
