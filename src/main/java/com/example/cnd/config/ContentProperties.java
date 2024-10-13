package com.example.cnd.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ContentProperties {

    @Value("${app.version.server:/api/v1}")
    private String versionUrl;
}
