package priv.zcy.controller.returning_wrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import priv.zcy.pojo.data_mapping.*;
import priv.zcy.pojo.data_wrapper_utils.MemberInfoFormat;
import priv.zcy.pojo.data_wrapper_utils.TokenFormat;
import priv.zcy.pojo.data_wrapper_utils.WrapperDataFormat;

import java.lang.reflect.AnnotatedElement;
import java.util.List;


@ControllerAdvice(annotations = RestController.class)
public class DataAssignFormatWrapper implements ResponseBodyAdvice {

    @Autowired
    private WrapperDataFormat wrapperDataFormat;
    @Autowired
    private MemberInfoFormat memberInfoFormat;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement annotatedElement = returnType.getAnnotatedElement();
        Class<?> containingClass = returnType.getContainingClass();
        return AnnotationUtils.findAnnotation(containingClass, PersonalResponseBodyWrapper.class) != null || AnnotationUtils.findAnnotation(annotatedElement, PersonalResponseBodyWrapper.class) !=null;

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String name = returnType.getMethod().getReturnType().getSimpleName();
        if (("Ums_member").equals(name)){
            return DataFormatEnum.valueOf(name.toUpperCase()).wrapData(memberInfoFormat,body);

        }else {
            return DataFormatEnum.valueOf(name.toUpperCase()).wrapData(wrapperDataFormat,body);

        }
    }
}
