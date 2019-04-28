package com.tony.note.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jli2
 * @date 4/10/2019 5:50 PM
 **/
@Data
@Component
@ConfigurationProperties(prefix = GitHubProperties.PREFIX)
public class GitHubProperties {
    public static final String PREFIX="client";
    private String id;
    private String secret;
}
