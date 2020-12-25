package top.plugins;

import top.annotation.SwaggerNotes;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * swagger注解value值补丁
 *
 * @author lgs
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE - 10)
public class OperationValueReaderPlugin implements OperationBuilderPlugin {

    private final DescriptionResolver descriptions;

    public OperationValueReaderPlugin(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void apply(OperationContext context) {
        Optional<ApiOperation> methodAnnotation = context.findAnnotation(ApiOperation.class);
        StringBuffer titleBuffer = new StringBuffer("");
        if (methodAnnotation.isPresent()) {
            String apiValue = this.descriptions.resolve(methodAnnotation.get().value());
            if (!StringUtils.isEmpty(apiValue)) {
                titleBuffer.append(apiValue);
            }
        }
        Optional<SwaggerNotes> swaggerNotesOptional = context.findAnnotation(SwaggerNotes.class);
        if (swaggerNotesOptional.isPresent()) {
            String swaggerTitle = swaggerNotesOptional.get().title();
            if (!StringUtils.isEmpty(swaggerTitle)) {
                if (StringUtils.isEmpty(titleBuffer.toString())) {
                    titleBuffer.append(swaggerTitle);
                } else {
                    titleBuffer.append("--").append(swaggerTitle);
                }
            }
        }

        context.operationBuilder().summary(titleBuffer.toString());
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
