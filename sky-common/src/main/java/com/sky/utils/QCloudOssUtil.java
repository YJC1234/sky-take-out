package com.sky.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class QCloudOssUtil {

    private String bucketAddr;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    private COSClient cosClient;

    //前四个参数的构造函数
    public QCloudOssUtil(String bucketAddr, String accessKeyId, String accessKeySecret, String bucketName) {
        this.bucketAddr = bucketAddr;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    @PostConstruct
    public void initCOSClient() {
        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        Region region = new Region(bucketAddr);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }

    @PreDestroy
    public void destroyCOSClient() {
        cosClient.shutdown();
    }

    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {
        log.info("开始上传文件，文件地址:{}", objectName);
        try {
            // 创建PutObject请求。
            ObjectMetadata objectMetadata = new ObjectMetadata();
            cosClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes), objectMetadata);
        } catch (Exception e) {
            log.info("文件上传失败");
            e.printStackTrace();
            return null;
        }
        //文件访问路径规则 https://sky-itcast-1323829851.cos.ap-nanjing.myqcloud.com
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".cos.")
                .append(bucketAddr)
                .append(".myqcloud.com/")
                .append(objectName);

        log.info("文件上传到:{}", stringBuilder);

        return stringBuilder.toString();
    }
}
