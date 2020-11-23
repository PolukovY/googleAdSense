package com.levik.googleadsense.statistic.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class StatisticDetails {
    private Details show;
    private Details click;

    public StatisticDetails() {
        this.show= new Details();
        this.click = new Details();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Details {
        private AtomicInteger count = new AtomicInteger(0);


        public void increment() {
            count.incrementAndGet();
        }
    }
}
