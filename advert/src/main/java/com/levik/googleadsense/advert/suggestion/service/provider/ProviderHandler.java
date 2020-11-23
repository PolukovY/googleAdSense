package com.levik.googleadsense.advert.suggestion.service.provider;

import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import com.levik.googleadsense.common.IdentityResponse;

public interface ProviderHandler {

    SuggestedModel handle(IdentityResponse identityResponse);

    String name();
}
