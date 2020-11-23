package com.levik.googleadsense.website.api;

import com.levik.googleadsense.website.api.dto.SuggestedResponse;
import com.levik.googleadsense.website.api.service.AdvertApi;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/advert")
@AllArgsConstructor
public class AdvertController {

    private static final String SELF = "http://localhost:8085/advert/redirect/%s/%s";

    private final AdvertApi advertApi;

    @GetMapping("/{clientIdentity}")
    public SuggestedResponse suggestedItem(@PathVariable String clientIdentity) {
        SuggestedResponse suggestedResponse = advertApi.findIdentity(clientIdentity);

        String redirectUrl = String.format(SELF, clientIdentity, suggestedResponse.getIdentity());
        suggestedResponse.setUrl(redirectUrl);

        return suggestedResponse;
    }

    @GetMapping("/redirect/{clientIdentity}/{advertIdentity}")
    public ResponseEntity<Void> redirect(@PathVariable String clientIdentity, @PathVariable String advertIdentity) {
        String redirect = advertApi.redirect(clientIdentity, advertIdentity);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(redirect))
                .build();
    }
}
