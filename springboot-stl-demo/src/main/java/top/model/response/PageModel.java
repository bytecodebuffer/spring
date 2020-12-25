package top.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页模型
 *
 * @author lgs
 */
@ApiModel(description = "分页模型")
public class PageModel<T> {

    @ApiModelProperty("数据")
    private List<T> data;

    @ApiModelProperty("数据总条数")
    private int total;

    @ApiModelProperty("附加数据")
    private Object attach;

    @ApiModelProperty("分页下标")
    private int index;

    @ApiModelProperty("页面大小")
    private int size;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getAttach() {
        return attach;
    }

    public void setAttach(Object attach) {
        this.attach = attach;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
