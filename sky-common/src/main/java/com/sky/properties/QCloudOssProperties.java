package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.qcloudoss")
@Data
public class QCloudOssProperties {

    private String bucketAddr;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
