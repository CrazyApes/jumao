package cn.com.crazyit.core.environment;

import cn.com.crazyit.util.CryptologyUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Getter
@Setter
@ToString
public class TokenEnvironment {

    public TokenEnvironment(String key) {
        this.key = CryptologyUtil.base64Encrypt(key);
    }

    private String key;

    public Date buildExpire() {
        long now = System.currentTimeMillis();
        long expire = now + 1000 * 60 * 60 * 10;
        return new Date(expire);
    }
}
