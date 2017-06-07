package cn.com.crazyit.web.controller.rest;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
public abstract class RestBaseController {

    protected Integer getPage(Integer offset, Integer size) {
        if (null == offset || null == size) {
            return 0;
        }

        if (size.equals(0)) {
            return 0;
        }

        return (offset / size);
    }
}
