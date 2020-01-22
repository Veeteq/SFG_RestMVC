package com.wojnarowicz.sfg.gw.batch;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory; 
    
    @Bean
    Job job(ItemProcessor<KiasExpectedPayment, KiasExpectedPayment> itemProcessor,
            ItemWriter<KiasExpectedPayment> itemWriter) throws Exception {

        Step step = stepBuilderFactory.get("KIAS-Payment-Notification")
                .<KiasExpectedPayment, KiasExpectedPayment> chunk(10)
                .reader(itemReader())
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("KIAS-Payment-Job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public JpaPagingItemReader<KiasExpectedPayment> itemReader() {
        return new JpaPagingItemReaderBuilder<KiasExpectedPayment>()
                            .name("KiasExpectedPayment-Reader")                            
                            .entityManagerFactory(entityManagerFactory)
                            .queryString("select p FROM KiasExpectedPayment p WHERE p.paymentStatus != 'REGISTERED'")
                            .pageSize(10)                            
                            .build();
    }
}
