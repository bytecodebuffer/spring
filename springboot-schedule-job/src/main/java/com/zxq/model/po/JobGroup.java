package com.zxq.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author zxq
 */
@Table(name = "schedule_job_group")
@Data
public class JobGroup {
    /**
     * 主键
     * @GeneratedValue insert操作后会把主键id映射到实体id上
     */
    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;

    /**
     * 分组名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}