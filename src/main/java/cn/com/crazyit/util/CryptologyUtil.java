package cn.com.crazyit.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public class CryptologyUtil {

    public static String md5Encrypt(String str) {
        if (null != str) {
            return DigestUtils.md5Hex(str);
        } else {
            return null;
        }
    }
}
