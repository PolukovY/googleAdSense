package com.levik.googleadsense.statistic.api;

import com.levik.googleadsense.statistic.api.dto.StatisticResponse;
import com.levik.googleadsense.statistic.service.InMemoryStoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/details")
@AllArgsConstructor
public class StatisticController {

    private final InMemoryStoreService inMemoryStoreService;

    @GetMapping
    public StatisticResponse statistic() {
        return new StatisticResponse(inMemoryStoreService.getStore());
    }
}
