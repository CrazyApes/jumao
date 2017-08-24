package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zack
 *         Created on 2017/6/12.
 */

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SP_PRI_TEMPLATE")
public class SpPriTemplate extends AppPojo{
    /**
     * 价格模版名称
     */
    @Column(length = 20, nullable = false, unique = true)
    private String title;

    /**
     * 价格模版内产品集合
     */
    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "id")
    private Set<Product> books = new HashSet();

    /**
     * 是否可编辑
     * 1可编辑
     * 0不可编辑
     */
    @Column(nullable = false)
    private Byte editable = 0;

}
