package cn.com.crazyit.foundation.pojo;

import cn.com.crazyit.core.constant.PriceConfType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Zack
 *         Created on 2017/5/24.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "PRODUCT")
public class Product extends AppPojo {
    @Column(length = 20, nullable = false, unique = true)
    private String name;
    //高度超出加的价格
    @Column(nullable = false)
    private Long incrementPriceForH;

    //宽度超出加的价格
    @Column(nullable = false)
    private Long incrementPriceForW;

    //深度度超出加的价格
    @Column(nullable = false)
    private Long incrementPriceForD;

    /*//增加一种颜色加的价格
    @Column(nullable = false)
    private Long incrementPricePerColor;*/

    //200以内基础价格
    @Column(nullable = false)
    private Long basePriceA;

    //300以内基础价格
    @Column(nullable = false)
    private Long basePriceB;

    @Enumerated
    @Column(length = 20, nullable = false)
    private PriceConfType priceConfType;

    //基础宽度
    @Column(nullable = false)
    private Integer baseHeight;

    //基础宽度
    @Column(nullable = false)
    private Integer baseWidth;

    //基础深度
    @Column(nullable = false)
    private Integer baseDepth;



}