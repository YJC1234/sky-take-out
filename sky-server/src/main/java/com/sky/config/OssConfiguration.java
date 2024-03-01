package com.sky.config;

import com.sky.properties.QCloudOssProperties;
import com.sky.utils.QCloudOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean
    QCloudOssUtil qCloudOssUtil(QCloudOssProperties qCloudOssProperties) {
        return new QCloudOssUtil(qCloudOssProperties.getBucketAddr(),
                qCloudOssProperties.getAccessKeyId(),
                qCloudOssProperties.getAccessKeySecret(),
                qCloudOssProperties.getBucketName());
    }
}
