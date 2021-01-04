package top.supoman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.model.VO.SearchResult;
import top.model.entity.User;
import top.service.SolrService;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author bz
 * @date 2020/10/8
 */
@RestController
@Validated
public class SolrController {

    @Autowired
    private SolrService solrService;

    @GetMapping("/save")
    public  List<User> save() throws Exception{
        return solrService.saveUser();
    }


    @GetMapping("/query")
    public SearchResult queryFromSolr(@NotBlank @RequestParam(value = "param")String param) throws Exception{
        return solrService.searchUsers(param);
    }

    @GetMapping("/queryName")
    public List<String> queryName(@NotBlank @RequestParam(value = "name")String name) throws Exception{
        return solrService.searchName(name);
    }
}
