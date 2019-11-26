package com.example.punch.contract;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zzs
 * @date 2019/11/25 23:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse defaultExceptionHandler(ConstraintViolationException ex) {
        long beginTime = System.currentTimeMillis();
        Map<String, List<String>> validationErrors = Maps.newHashMap();

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                String fieldName = node.getName();
                List<String> lst = validationErrors.get(fieldName);
                if (lst == null) {
                    lst = Lists.newArrayList();
                }
                lst.add(constraintViolation.getMessage());
                validationErrors.put(node.getName(), lst);
            }
        }

        BaseResponse baseResponse = new BaseResponse(validationErrors);
        baseResponse.calcCostTime(beginTime);
        return baseResponse;
    }

}

