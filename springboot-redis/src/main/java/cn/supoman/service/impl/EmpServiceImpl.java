package cn.supoman.service.impl;

import cn.supoman.dao.EmpDao;
import cn.supoman.model.entity.Emp;
import cn.supoman.service.EmpService;
import cn.supoman.utils.RedisKeyUtil;
import cn.supoman.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bz
 * @date 2020/11/18
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private RedisUtil redisUtil;

    private static final String GLOBAL = RedisKeyUtil.globalKey();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

   // @Cacheable(value = "emp",key="'all'")
    @Override
    public List<Emp> getEmpList() {
        if(redisUtil.hasKey(GLOBAL)){
            return redisUtil.get(GLOBAL,List.class);
        }
        List<Emp> empList = empDao.getEmpList();
        redisUtil.set(RedisKeyUtil.globalKey(),empList);
        return empList;
    }

    /**
     * 注解会存储所有的查询，不建议使用
     * @param id
     * @return
     */
   // @Cacheable(value = "emp",key="#id")
    @Override
    public Emp getEmpById(Integer id) {
        String key = GLOBAL+id;
        if(redisUtil.hasKey(key)){
            return redisUtil.get(key,Emp.class);
        }
        Emp emp = empDao.getEmpById(id);
        redisUtil.set(key,emp);
        return emp;
    }

   // @CacheEvict(value = "emp",key = "'all'")
    @Override
    public Emp save(Emp emp) {
        redisUtil.delete(GLOBAL);
        empDao.save(emp);
        return emp;
    }

}
