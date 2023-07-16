package za.co.test.configs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@Configuration

public class RestConfigs {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

	    return builder.setConnectTimeout(Duration.ofMillis(300000))
     .setReadTimeout(Duration.ofMillis(300000)).build();
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
		
	
//	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
//	List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//	interceptors.add(new LoggingRequestInterceptor());
//	restTemplate.setInterceptors(interceptors);
//	return restTemplate;
//	}
}
