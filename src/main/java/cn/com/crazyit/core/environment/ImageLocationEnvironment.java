package cn.com.crazyit.core.environment;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString
public class ImageLocationEnvironment {

    public ImageLocationEnvironment(String bucket, String[] cdnHosts) {
        this.bucket = bucket;
        this.cdnHosts = cdnHosts;
    }

    private String bucket;
    private String[] cdnHosts;
    private String endPoint = "oss-cn-shenzhen.aliyuncs.com";
    private String accessKey = "LTAIh02xsrQukohN";
    private String accessSecret = "LnbU9OJlOaufIb8nPQd1u6FIIAp3Ec";
    private OSSClient ossClient = new OSSClient(endPoint, accessKey, accessSecret);

    // 上传图片的方法
    public String uploadImage(String fileName, ImageType type, InputStream stream) {

        String saveName = this.getFileName(fileName);

        String key = this.getFullPath(saveName, type);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        ossClient.putObject(bucket, key, stream, metadata);

        String cdnPath = this.getCdnHostByKeyHash(key);
        return cdnPath + "/" + key;
    }

    private String getFileName(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.'));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + ext;
    }

    private String getFullPath(String fileName, ImageType type) {
        String basePath = type.getValue();
        if (basePath.length() > 0 && basePath.charAt(basePath.length() - 1) != '/') {
            basePath += '/';
        }
        if (basePath.length() > 0 && basePath.charAt(0) == '/') {
            basePath = basePath.substring(1);
        }
        if (fileName.length() > 0 && fileName.charAt(0) == '/') {
            fileName = fileName.substring(1);
        }
        return basePath + fileName;
    }

    private String getCdnHostByKeyHash(String key) {
        int i = key.hashCode();
        int result = i % this.cdnHosts.length;
        return cdnHosts[result];
    }

    @Override
    protected void finalize() throws Throwable {
        this.ossClient.shutdown();
    }

    public static enum ImageType {
        EMPLOYEE_HEADER_IMAGE("/employee/header/image/"),
        ORDER_GOODS_COLOR_CARD("/order/goods/color/card/");

        private String value;

        ImageType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
