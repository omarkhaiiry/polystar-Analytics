package com.polystar.analytics.configration;


import com.polystar.analytics.props.Props;
import com.polystar.analytics.template.ClientTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableConfigurationProperties(Props.class)
public class ClientConfig {


    @Bean
    public ClientTemplate clientTemplate(Props props, RestTemplate restTemplate) {
        return new ClientTemplate(props, restTemplate);
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
