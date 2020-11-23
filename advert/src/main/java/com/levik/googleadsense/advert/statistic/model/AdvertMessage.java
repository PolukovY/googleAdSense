package com.levik.googleadsense.advert.statistic.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdvertMessage {
    private final Long identity;
    private final String title;
    private final String url;
    private final Action action;

    public enum Action {
        SHOW,
        CLICK
    }
}
