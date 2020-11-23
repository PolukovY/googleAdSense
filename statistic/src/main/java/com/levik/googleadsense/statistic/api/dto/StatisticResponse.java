package com.levik.googleadsense.statistic.api.dto;

import com.levik.googleadsense.statistic.service.model.StatisticDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponse {

    private Map<Long, StatisticDetails> store;
}
