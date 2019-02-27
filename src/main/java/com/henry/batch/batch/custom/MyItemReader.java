package com.henry.batch.batch.custom;

import com.henry.batch.model.YourStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.web.client.RestTemplate;

/**
 * com.henry.batch.batch.custom
 * MyItemReader.java
 *
 * @Author : henry
 * @Data : 2019-02-27
 * @Version : 1.0
 */
@Slf4j
public class MyItemReader implements ItemReader<YourStore> {

    private final RestTemplate restTemplate;

    public MyItemReader(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public YourStore read() throws Exception {

        return null;
    }
}
