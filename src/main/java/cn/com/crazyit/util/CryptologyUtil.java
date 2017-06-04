package cn.com.crazyit.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public class CryptologyUtil {

    public static String base64Encrypt(String str) {
        if (null == str) {
            return null;
        } else {
            byte[] bytes = Base64.encodeBase64(str.getBytes());
            return new String(bytes);
        }
    }

    public static String base64Decrypt(String str) {
        if (null == str) {
            return null;
        } else {
            byte[] bytes = Base64.decodeBase64(str.getBytes());
            return new String(bytes);
        }
    }

    public static String md5Encrypt(String str) {
        if (null != str) {
            return DigestUtils.md5Hex(str);
        } else {
            return null;
        }
    }
}
