package com.cromxt.zenspaceserver.config;


import com.cromxt.toolkit.crombucket.BucketUserDetails;
import com.cromxt.toolkit.crombucket.clients.CromBucketWebClient;
import com.cromxt.toolkit.crombucket.clients.impl.CromBucketWebClientImpl;
import com.cromxt.toolkit.crombucket.users.LocalBucketUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class StorageServerConfig {

    @Bean
    public BucketUserDetails bucketUserDetails() {
        return new LocalBucketUserDetails("http://localhost:9090");
    }

    @Bean
    public RestClient restClient(RestClient.Builder resClientBuilder) {
        return resClientBuilder.build();
    }

    @Bean
    public CromBucketWebClient cromBucketWebClient(BucketUserDetails bucketUserDetails, RestClient restClient) {
        return new CromBucketWebClientImpl(bucketUserDetails, restClient);
    }
}
