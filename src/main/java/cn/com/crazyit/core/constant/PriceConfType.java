package cn.com.crazyit.core.constant;

/**
 * @author Zack
 *         Created on 2017/5/24.
 */
public enum PriceConfType {


    WHOLE_PACKAGE_DOOR(1,"整套门"),
    DOUBLE_SLEEVE(2,"双面门套"),
    SINGLE_SLEEVE(3,"单面套");

    private Integer code;
    private String name;

    private PriceConfType(Integer code,String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
