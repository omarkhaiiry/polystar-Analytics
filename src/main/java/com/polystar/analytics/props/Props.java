package com.polystar.analytics.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "props")
public class Props {

    private String fileServerUrl;
    private String readPath;


}
