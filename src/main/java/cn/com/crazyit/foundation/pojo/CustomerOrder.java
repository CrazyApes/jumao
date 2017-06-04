package cn.com.crazyit.foundation.pojo;

import cn.com.crazyit.core.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/23.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder extends ApplicationPojo {

    // 订单号
    @Column(length = 40, unique = true, nullable = false, updatable = false)
    private String orderNumber;

    // 订单状态
    @Column(length = 15, nullable = false)
    @Enumerated
    private OrderStatus orderStatus = OrderStatus.UNCHECKED;

    // 所属客户
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    // 下单人
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private Employee employee;

    // 订单商品
    @OneToMany(targetEntity = CustomerOrderGoods.class)
    @JoinColumn(name = "CUSTOMER_ORDER_ID")
    private List<CustomerOrderGoods> customerOrderGoodsList = new ArrayList<>();

    // 订单总额
    @Column(nullable = false)
    private Long totalAmount = 0L;

    // 折扣
    @Column(nullable = false)
    private Long discountAmount = 0L;

    // 订单应付
    @Column(nullable = false)
    private Long payableAmount = 0L;

    // 收货人姓名
    @Column(length = 15, nullable = false)
    private String shippingName;

    // 收货人电话
    @Column(length = 11, nullable = false)
    private String shippingPhone;

    // 收货人地址
    @Column(length = 100, nullable = false)
    private String shippingAddress;

    // 订单创建时间
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 订单确认时间
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    // 订单开始生产时间
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date produceStartTime;

    // 订单完成生产时间
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date produceEndTime;

    // 订单发货时间
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    // 物流公司标题
    @Column(length = 15)
    private String deliveryTitle;

    // 物流单号
    @Column(length = 40, unique = true)
    private String deliveryNumber;

}
