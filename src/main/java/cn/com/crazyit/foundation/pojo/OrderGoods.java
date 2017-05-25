package cn.com.crazyit.foundation.pojo;

import cn.com.crazyit.core.constant.OrderGoodsStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/23.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "ORDER_GOODS")
public class OrderGoods extends ApplicationPojo {

    // 商品名称
    @Column(length = 30, nullable = false)
    private String title;

    // 商品编码
    @Column(length = 30, nullable = false)
    private String number;

    // 生产状态
    @Column(length = 15, nullable = false)
    @Enumerated
    private OrderGoodsStatus status = OrderGoodsStatus.WAIT;

    // 高度
    @Column(nullable = false)
    private Integer height = 0;

    // 宽度
    @Column(nullable = false)
    private Integer width = 0;

    // 深度
    @Column(nullable = false)
    private Integer depth = 0;

    // 购买数量
    @Column(nullable = false)
    private Integer quantity = 0;

    // 商品单价
    @Column(nullable = false)
    private Long unitAmount = 0L;

    // 商品总价
    @Column(nullable = false)
    private Long totalAmount = 0L;

    // 折扣
    @Column(nullable = false)
    private Long discount = 0L;

    // 商品应付
    @Column(nullable = false)
    private Long payableAmount = 0L;
}
