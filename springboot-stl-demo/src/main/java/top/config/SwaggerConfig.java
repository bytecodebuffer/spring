package top.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动Swagger2配置
 *
 * @author lgs
 * @Configuration 声明该类为配置类
 * @EnableSwagger2 声明启动swagger2
 * @ConditionalOnProperty 是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //全局字段配置
        ticketPar.name("locale").description("国际化请求头。\n简体中文：zh_CN;\n美式英语：en_US;\n马来西亚语：ms_MY;\n泰国语：th_TH;\n繁体中文：zh_TW;\n越南语：vi_VN;\n新加坡华语：zh_SG")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .hidden(false)
                .required(false)
                .defaultValue("zh_CN")
                .build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.bailun.demo"))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档说明
                .title("示例项目API接口文档")
                //文档版本说明
                .version("V1.0.0")
                .build();
    }
}