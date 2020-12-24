package cn.supoman.dao;

import cn.supoman.model.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bz
 * @date 2020/11/18
 */
@Mapper
public interface EmpDao {

    /**
     * 获取员工列表
     * @return
     */
    List<Emp> getEmpList();


    /**
     * 根据id 查询
     * @param id
     * @return
     */
    Emp getEmpById(@Param("id")Integer id);


    /**
     * 新增
     * @param emp
     */
    void save(@Param("emp")Emp emp);

}
