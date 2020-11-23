package com.levik.googleadsense.website.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuggestedResponse {
    private Long identity;
    private String title;
    private String url;
}
