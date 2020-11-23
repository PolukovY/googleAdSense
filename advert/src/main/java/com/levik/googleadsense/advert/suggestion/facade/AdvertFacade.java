package com.levik.googleadsense.advert.suggestion.facade;

import com.levik.googleadsense.advert.statistic.model.AdvertMessage;
import com.levik.googleadsense.advert.statistic.sender.Sender;
import com.levik.googleadsense.advert.suggestion.service.SuggestedService;
import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import com.levik.googleadsense.common.IdentityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertFacade {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertFacade.class);

    private final SuggestedService suggestedService;
    private final Sender sender;

    @Autowired
    public AdvertFacade(SuggestedService suggestedService, Sender sender) {
        this.suggestedService = suggestedService;
        this.sender = sender;
    }

    public SuggestedModel getSuggested(IdentityResponse identityResponse, String clientData) {
        SuggestedModel suggestedModel = suggestedService.getSuggested(identityResponse);

        sender.send(
                new AdvertMessage(
                        identityResponse.getIdentity(), suggestedModel.getTitle(),
                        suggestedModel.getUrl(), AdvertMessage.Action.SHOW
                )
        );

        LOG.info("request clientData {} identityResponse {} suggestedModel {}", clientData, identityResponse, suggestedModel);
        return suggestedModel;
    }

    public String redirect(IdentityResponse identityResponse, Long advertIdentity, String clientData) {
        SuggestedModel suggestedModel = suggestedService.getUrl(advertIdentity);

        sender.send(
                new AdvertMessage(
                        identityResponse.getIdentity(), suggestedModel.getTitle(),
                        suggestedModel.getUrl(), AdvertMessage.Action.CLICK
                )
        );

        return suggestedModel.getReal();
    }
}
