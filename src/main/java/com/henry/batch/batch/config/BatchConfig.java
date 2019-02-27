package com.henry.batch.batch.config;

import com.henry.batch.batch.custom.MyItemReader;
import com.henry.batch.model.MyStore;
import com.henry.batch.model.YourStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * com.henry.batch.batch
 * BatchConfig.java
 *
 * @Author : henry
 * @Data : 2019-02-27
 * @Version : 1.0
 */
@Configuration
@Slf4j
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobs;

    @Autowired
    StepBuilderFactory steps;

    private static final int chunkSize = 30;

    @Bean
    Step myStep1(ItemReader<YourStore> myReader,
                 ItemProcessor<YourStore, MyStore> myProcessor,
                 ItemWriter<MyStore> myWriter){
        return steps
                .get("myStep1")
                .<YourStore, MyStore>chunk(chunkSize)
                .reader(myReader)
                .processor(myProcessor)
                .writer(myWriter)
                .build();
    }

    @Bean
    Job myJob(@Qualifier("myStep1")Step myStep1){
        return jobs
                .get("myJob")
                .incrementer(new RunIdIncrementer())
                .flow(myStep1)
                .end()
                .build();
    }

    @Bean
    ItemReader<YourStore> customReader(RestTemplate restTemplate){
        return new MyItemReader(restTemplate);

    }

}
