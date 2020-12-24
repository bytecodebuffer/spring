package cn.supoman.controller;

import cn.supoman.model.entity.Emp;
import cn.supoman.service.EmpService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bz
 * @date 2020/11/18
 */
@RestController
@RequestMapping("/emp")
@Api(tags = "EmpController")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/getEmpList")
    public List<Emp> getEmpList(){
        return empService.getEmpList();
    }

    @GetMapping("/geEmpById")
    public Emp geEmpById(@RequestParam("id")Integer id){
        return empService.getEmpById(id);
    }

    @PostMapping("/save")
    public Emp save(@RequestBody Emp emp){
        return empService.save(emp);
    }

}
