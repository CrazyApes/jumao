package cn.com.crazyit.foundation.pojo.form;

import cn.com.crazyit.foundation.pojo.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/10.
 */
@Getter
@Setter
@ToString
public class CustomerForm implements Serializable {

    private Long id;

    @NotNull(message = "客户标识名不能为空")
    @Length(min = 2, max = 40, message = "客户标识名的长度在2~40位之间")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z0-9]+$", message = "客户标识名只能输入中文、英文和数字")
    private String title;

    @NotNull(message = "所属地区不能为空")
    @Length(min = 2, max = 20, message = "所属地区的长度在2~20位之间")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "所属地区只能输入中文")
    private String region;

    @NotNull(message = "客户地址不能为空")
    @Length(min = 4, max = 100, message = "客户地址的长度在4~100之间")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z0-9]+$", message = "客户地址只能输入中文、英文和数字")
    private String address;

    @Length(max = 20, message = "常用物流的长度不能超过20位")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5A-Za-z]+$|^$", message = "常用物流只能输入中文和英文")
    private String deliveryType;

    @NotNull(message = "联系电话不能为空")
    @Length(min = 8, max = 15, message = "联系电话的长度在8~15位之间")
    @Pattern(regexp = "^[0-9]+(-[0-9]+)?$", message = "联系电话只能输入数字或横杠，且属于正确的电话号码格式")
    private String phone;

    @Length(max = 20, message = "客户传真最大不能超过20位")
    private String fax;

    private Boolean enable;

    @Length(max = 100, message = "备注信息的长度不能超过100位")
    private String remark;

    public Customer transformToCustomer() {
        Customer customer = new Customer();
        if (0 != this.id) {
            customer.setId(this.id);
        }
        customer.setTitle(this.title);
        customer.setAddress(this.address);
        customer.setPhone(this.phone);
        if (null != this.fax && this.fax.length() > 0) {
            customer.setFax(this.fax);
        }
        if (null != enable) {
            customer.setEnable(enable);
        }
        if (null != this.region && this.region.length() > 0) {
            customer.setRegion(this.region);
        }
        if (null != this.deliveryType && this.deliveryType.length() > 0) {
            customer.setDeliveryType(this.deliveryType);
        }
        if (null != this.remark && this.remark.length() > 0) {
            customer.setRemark(this.remark);
        }
        return customer;
    }

}
