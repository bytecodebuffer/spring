package cn.supoman.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bz
 * @date 2020/11/18
 */
@Data
@ApiModel
public class Emp implements Serializable {

    @ApiModelProperty(hidden = true)
    private Integer id;

    private String name;

    private Integer age;

    @ApiModelProperty(hidden = true)
    private Date birthday;

    @ApiModelProperty(hidden = true)
    private Date createTime;
}
