package cn.com.crazyit.foundation.pojo.temp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/6.
 */
@Getter
@Setter
@ToString
public final class GridData<Data> implements Serializable {

    private List<Data> rows;
    private Long total;

    public GridData<Data> build(List<Data> rows, Long total) {
        this.setRows(rows);
        this.setTotal(total);
        return this;
    }

    public GridData<Data> build(Page<Data> page) {
        this.setRows(page.getContent());
        this.setTotal(page.getTotalElements());
        return this;
    }
}
