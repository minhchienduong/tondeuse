package com.astrelya.tondeuse.batch;

import com.astrelya.tondeuse.batch.processor.MowerItemProcessor;
import com.astrelya.tondeuse.batch.reader.MowerItemReader;
import com.astrelya.tondeuse.batch.writer.MowerItemWriter;
import com.astrelya.tondeuse.model.Mower;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    @Bean
    public ItemReader<Mower> reader(@Value("${mower.input.filePath}") String inputFilePath) {
        return new MowerItemReader(new FileSystemResource(inputFilePath));
    }

    @Bean
    public ItemProcessor<Mower, Mower> processor() {
        return new MowerItemProcessor();
    }

    @Bean
    public ItemWriter<Mower> writer(@Value("${mower.output.filePath}") String outputFilePath) {
        return new MowerItemWriter(outputFilePath);
    }

    @Bean
    public Step step1(ItemReader<Mower> reader, ItemWriter<Mower> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Mower, Mower> chunk(1, transactionManager)
                .reader(reader)
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job processMowerJob(@Qualifier("step1") Step step1) {
        return new JobBuilder("processMowerJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }
}
