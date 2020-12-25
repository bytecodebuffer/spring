package top.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 传统方式的配置，swagger使用原生，未加入自定义插件，校验框架也使用最简版的
 *
 * @author lgs
 */
@RestController
@RequestMapping("/traditional")
@Validated
@Api(tags = "TraditionalController",description = "传统方式的swagger文档")
public class TraditionalController {


}
