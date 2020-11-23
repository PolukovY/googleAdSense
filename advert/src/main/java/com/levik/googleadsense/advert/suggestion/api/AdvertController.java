package com.levik.googleadsense.advert.suggestion.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levik.googleadsense.advert.suggestion.api.dto.SuggestedResponse;
import com.levik.googleadsense.advert.suggestion.facade.AdvertFacade;
import com.levik.googleadsense.advert.suggestion.service.model.SuggestedModel;
import com.levik.googleadsense.common.IdentityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/suggested")
public class AdvertController {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertFacade.class);

    private final ObjectMapper objectMapper;
    private final AdvertFacade advertFacade;

    @Autowired
    public AdvertController(ObjectMapper objectMapper, AdvertFacade advertFacade) {
        this.objectMapper = objectMapper;
        this.advertFacade = advertFacade;
    }

    @GetMapping
    public SuggestedResponse suggested(
            @RequestParam(name = "client_data", required = false) String clientData,
            HttpServletRequest request) {
        var identityResponse = getIdentityResponse(request);
        SuggestedModel suggested = advertFacade.getSuggested(identityResponse, clientData);
        return new SuggestedResponse(suggested.getIdentity(), suggested.getTitle(), suggested.getUrl());
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirect(@RequestParam(name = "client_data", required = false) String clientData,
                                         @RequestParam(name = "advertIdentity") Long advertIdentity,
                                         HttpServletRequest request) {
        var identityResponse = getIdentityResponse(request);
        String url = advertFacade.redirect(identityResponse, advertIdentity, clientData);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @GetMapping("/link")
    public String moveToLink(@RequestParam(name = "client_data", required = false) String clientData,
                             @RequestParam(name = "advertIdentity") Long advertIdentity,
                             HttpServletRequest request) {
        var identityResponse = getIdentityResponse(request);
        return advertFacade.redirect(identityResponse, advertIdentity, clientData);
    }


    private IdentityResponse getIdentityResponse(HttpServletRequest request) {
        String authDetails = request.getHeader("auth");
        IdentityResponse identityResponse = null;
        try {
             identityResponse = objectMapper.readValue(authDetails, IdentityResponse.class);
        } catch (JsonProcessingException exe) {
            LOG.warn("Can't read authDetails {}", exe.getMessage());
        }

        return identityResponse;
    }
}
