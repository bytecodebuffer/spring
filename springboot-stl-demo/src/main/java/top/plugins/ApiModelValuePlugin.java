package top.plugins;

import top.annotation.PropertyNotes;
import top.enums.PropertyTypeEnum;
import top.validator.protocol.EnumValid;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.Annotations;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger.schema.ApiModelProperties;

/**
 * ApiProperty注解value属性插件
 *
 * @author lgs
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE - 10)
public class ApiModelValuePlugin implements ModelPropertyBuilderPlugin {
    private final DescriptionResolver descriptions;

    @Autowired
    public ApiModelValuePlugin(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        StringBuffer notes = new StringBuffer("");
        StringBuffer notesStr = new StringBuffer("");
        String enumNotes = "";
        String returnNotes = "";
        Optional<ApiModelProperty> annotation = Optional.absent();
        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }

        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(Annotations.findPropertyAnnotation(context.getBeanPropertyDefinition().get(), ApiModelProperty.class));
        }

        if (annotation.isPresent()) {
            notes.append(annotation.get().value());
            EnumValid enumValid =
                    context.getBeanPropertyDefinition().get().getField().getAnnotation(EnumValid.class);
            if (null != enumValid) {
                Class cls = enumValid.enumTypeClass();
                if (PropertyTypeEnum.class.isAssignableFrom(cls)) {
                    PropertyTypeEnum[] objects = (PropertyTypeEnum[]) cls.getEnumConstants();
                    notesStr.append("：");
                    for (PropertyTypeEnum e : objects) {
                        Integer code = e.getCode();
                        notesStr.append(code);
                        PropertyNotes propertyNotes = null;
                        try {
                            propertyNotes = e.getClass().getField(e.toString()).getAnnotation(PropertyNotes.class);
                        } catch (NoSuchFieldException ex) {
                            System.err.println(ex.getLocalizedMessage());
                        }
                        if (propertyNotes != null) {
                            notesStr.append("-").append(propertyNotes.value()).append(",");
                        }
                    }
                    enumNotes = notesStr.substring(0, notesStr.length() - 1);
                }
            }
            returnNotes = notes.append(enumNotes).toString();
            context.getBuilder().description(returnNotes);
        }

    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
