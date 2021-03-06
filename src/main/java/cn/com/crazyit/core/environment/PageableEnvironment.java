package cn.com.crazyit.core.environment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PageableEnvironment {

    private Integer defaultPage;
    private Integer defaultSize;
    private Integer minSize;
    private Integer maxSize;
}
