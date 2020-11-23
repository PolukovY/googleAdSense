package com.levik.googleadsense.website.api.service;


import com.levik.googleadsense.website.api.dto.SuggestedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gateway", url = "http://127.0.0.1:9999")
public interface AdvertApi {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/advert/api/v1/suggested?clientIdentity={clientIdentity}",
            produces = "application/json"
    )
    SuggestedResponse findIdentity(@PathVariable("clientIdentity") String clientIdentity);


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/advert/api/v1/suggested/link?clientIdentity={clientIdentity}&advertIdentity={advertIdentity}",
            produces = "application/json"
    )
    String redirect(@PathVariable("clientIdentity") String clientIdentity,
                    @PathVariable("advertIdentity") String advertIdentity);
}
