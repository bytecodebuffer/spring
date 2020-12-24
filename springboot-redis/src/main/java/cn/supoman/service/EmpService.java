package cn.supoman.service;

import cn.supoman.model.entity.Emp;

import java.util.List;

/**
 * @author bz
 * @date 2020/11/18
 */
public interface EmpService {

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
    Emp getEmpById(Integer id);

    /**
     * 新增
     * @param emp
     * @return
     */
    Emp save(Emp emp);
}
