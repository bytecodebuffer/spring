package top.plugins;

import top.annotation.SwaggerNotes;
import top.enums.SubCodeEnum;
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
 * swagger注解notes值补丁
 *
 * @author lgs
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE - 10)
public class OperationNotesReaderPlugin implements OperationBuilderPlugin {

    private final DescriptionResolver descriptions;

    /**
     * subcode说明分隔符
     */
    private static final String SUBCODE_SPLIT_CHAR = "：";
    /**
     * subcode顺序分隔符
     */
    private static final String SUBCODE_SEQUENCE_SPLIT_CHAR = "、";

    public OperationNotesReaderPlugin(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void apply(OperationContext context) {
        Optional<ApiOperation> methodAnnotation = context.findAnnotation(ApiOperation.class);
        StringBuffer buffer = new StringBuffer("");
        if (methodAnnotation.isPresent()) {
            String apiNotes = this.descriptions.resolve(methodAnnotation.get().notes());
            if (!StringUtils.isEmpty(apiNotes)) {
                buffer.append(apiNotes).append("\n");
            }
        }
        String notes = "";
        Optional<SwaggerNotes> swaggerNotesOptional = context.findAnnotation(SwaggerNotes.class);
        if (swaggerNotesOptional.isPresent()) {
            SwaggerNotes swaggerNotes = swaggerNotesOptional.get();
            if (swaggerNotes.subCodeType() != null) {
                String tip = swaggerNotes.tip();
                if (!StringUtils.isEmpty(tip)) {
                    buffer.append(tip).append("\n");
                }
                Class[] classes = swaggerNotes.subCodeType();
                String[] codeTypeArr = swaggerNotes.codeType();
                for (Class clazz : classes) {
                    try {
                        SubCodeEnum[] sce = (SubCodeEnum[]) clazz.getEnumConstants();
                        for (String code : codeTypeArr) {
                            int seq = 1;
                            for (SubCodeEnum item : sce) {
                                if (item.getSubCode().startsWith(code)) {
                                    buffer.append(seq)
                                            .append(SUBCODE_SEQUENCE_SPLIT_CHAR)
                                            .append(item.getSubCode())
                                            .append(SUBCODE_SPLIT_CHAR)
                                            .append(item.getDesc())
                                            .append("\n");
                                }
                                seq++;
                            }
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            System.out.println(notes);
            notes = buffer.toString();
            context.operationBuilder().notes(notes);
        }

    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
