package com.levik.googleadsense.advert.suggestion.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuggestedModel {
    private Long identity;
    private String title;
    private String url;
    private String real;

    public SuggestedModel(Long identity, String title, String gateway, String real) {
        this.identity = identity;
        this.title = title;
        this.url = String.format(gateway, identity);
        this.real = real;
    }
}
