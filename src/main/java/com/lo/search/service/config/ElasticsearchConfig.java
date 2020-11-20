package com.lo.search.service.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

	@Bean
	public RestHighLevelClient restHighLevelClient() {
		return new RestHighLevelClient(restClientBuilder());
	}
	
	@Bean
	public RestClient restClient() {
		return restClientBuilder().build();
	}
	
	@Bean
	public RestClientBuilder restClientBuilder() {
		RestClientBuilder builder = RestClient.builder(
		        new HttpHost("localhost", 9200, "http"));
		
		return builder.setRequestConfigCallback(
		    new RestClientBuilder.RequestConfigCallback() {
		        @Override
		        public RequestConfig.Builder customizeRequestConfig(
		                RequestConfig.Builder requestConfigBuilder) {
		            return requestConfigBuilder.setSocketTimeout(90000); 
		        }
		    });
	}
}
