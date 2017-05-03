package cn.com.crazyit.core.exception;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
}
