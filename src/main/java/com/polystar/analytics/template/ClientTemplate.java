package com.polystar.analytics.template;


import com.polystar.analytics.props.Props;

import org.springframework.web.client.RestTemplate;


public class ClientTemplate {

    private Props props;
    RestTemplate restTemplate;

    public ClientTemplate(Props props, RestTemplate restTemplate) {
        this.props = props;
        this.restTemplate = restTemplate;
    }

    public String readAndCountTopWords(String filesPaths) {
        String url = props.getFileServerUrl() + props.getReadPath() + "?filesPaths=" + filesPaths;
        return restTemplate.getForEntity(url, String.class).getBody();

    }
}
