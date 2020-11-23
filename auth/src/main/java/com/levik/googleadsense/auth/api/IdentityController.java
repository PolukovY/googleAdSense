package com.levik.googleadsense.auth.api;

import com.levik.googleadsense.auth.api.exception.IdentityNotFoundException;
import com.levik.googleadsense.common.IdentityResponse;
import com.levik.googleadsense.common.KeyWord;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping(value = "/api/v1/identity")
public class IdentityController {

    @PostMapping("/{identity}")
    public IdentityResponse identityResponse(@PathVariable String identity ) {
        IdentityResponse identityResponse = new IdentityResponse();
        identityResponse.setIdentity(Long.parseLong(identity));
        identityResponse.setProvider("random");

        //TODO implement registration UI for websites, current impl just stub
        if (identity.startsWith("1")) {
            identityResponse.setKeyWords(Collections.singletonList(new KeyWord(1, "Sport")));
        } else if (identity.startsWith("2")) {
            identityResponse.setKeyWords(
                    Arrays.asList(
                        new KeyWord(1, "News"),
                        new KeyWord(2, "Ping-Pong")
                    )
            );
        } else if (identity.startsWith("3")) {
            throw new IdentityNotFoundException(String.format("Identity not found %s", identity));
        } else {
            identityResponse.setKeyWords(Collections.singletonList(new KeyWord(1, "Default")));
        }

        return identityResponse;
    }
}
