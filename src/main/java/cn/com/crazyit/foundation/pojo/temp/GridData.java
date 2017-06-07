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

    public static <Data> GridData<Data> build(List<Data> rows, Long total) {
        GridData<Data> gridData = new GridData<>();
        gridData.setRows(rows);
        gridData.setTotal(total);
        return gridData;
    }

    public static <Data> GridData<Data> build(Page<Data> page) {
        GridData<Data> gridData = new GridData<>();
        gridData.setRows(page.getContent());
        gridData.setTotal(page.getTotalElements());
        return gridData;
    }
}
