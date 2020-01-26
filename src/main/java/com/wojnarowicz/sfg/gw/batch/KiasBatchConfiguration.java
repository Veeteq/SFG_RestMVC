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

import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

@Configuration
@EnableBatchProcessing
public class KiasBatchConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    
    @Autowired
    private StepBuilderFactory stepBuilderFactory; 
    
    @Bean(name = "kiasJob")
    Job kiasJob(ItemProcessor<KiasExpectedPayment, KiasExpectedPayment> kiasItemProcessor,
            ItemWriter<KiasExpectedPayment> kiasItemWriter) throws Exception {

        Step step = stepBuilderFactory.get("KIAS-Payment-Notification")
                .<KiasExpectedPayment, KiasExpectedPayment> chunk(10)
                .reader(itemReader())
                .processor(kiasItemProcessor)
                .writer(kiasItemWriter)
                .build();

        return jobBuilderFactory.get("KIAS-Payment-Job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean(name = "sapJob")
    Job sapJob(ItemProcessor<BCExpectedPayment, BCExpectedPayment> sapItemProcessor,
            ItemWriter<BCExpectedPayment> sapItemWriter) throws Exception {

        Step step = stepBuilderFactory.get("SAP-Payment-Notification")
                .<BCExpectedPayment, BCExpectedPayment> chunk(10)
                .reader(sapItemReader())
                .processor(sapItemProcessor)
                .writer(sapItemWriter)
                .build();

        return jobBuilderFactory.get("SAP-Payment-Job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public JpaPagingItemReader<KiasExpectedPayment> itemReader() {
        return new JpaPagingItemReaderBuilder<KiasExpectedPayment>()
                            .name("KiasExpectedPayment-Reader")                            
                            .entityManagerFactory(entityManagerFactory)
                            .queryString("select p FROM KiasExpectedPayment p WHERE p.paymentStatus = 'REGISTERED'")
                            .pageSize(10)                            
                            .build();
    }

    @Bean
    public JpaPagingItemReader<BCExpectedPayment> sapItemReader() {
        return new JpaPagingItemReaderBuilder<BCExpectedPayment>()
                            .name("SapExpectedPayment-Reader")                            
                            .entityManagerFactory(entityManagerFactory)
                            .queryString("select p FROM BCExpectedPayment p WHERE p.publicId = 'bc:4002'")
                            .pageSize(10)                            
                            .build();
    }
}
