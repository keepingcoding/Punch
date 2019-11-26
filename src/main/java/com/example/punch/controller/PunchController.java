package com.example.punch.controller;

import com.alibaba.fastjson.TypeReference;
import com.example.punch.contract.BaseResponse;
import com.example.punch.contract.ServiceStatus;
import com.example.punch.contract.bo.PunchRecordBO;
import com.example.punch.contract.dto.PunchNotesDTO;
import com.example.punch.contract.vo.PunchRecordVO;
import com.example.punch.model.PunchRecordExp;
import com.example.punch.service.PunchService;
import com.example.punch.util.BeanConverter;
import com.example.punch.util.ValidationUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * @author zzs
 * @date 2019/10/24 21:15
 */
@Slf4j
@Validated
@RestController
public class PunchController {

    @Autowired
    private PunchService punchService;

    /**
     * 获取打卡类型
     *
     * @param queryDate
     */
    @PostMapping("/getPunchType")
    public BaseResponse<Map<String, Object>> getPunchType(@RequestBody String queryDate) {
        long beginTime = System.currentTimeMillis();
        BaseResponse<Map<String, Object>> baseResponse = new BaseResponse();
        try {
            Map<String, Object> punchType = this.punchService.getPunchType(queryDate);
            baseResponse.setResult(punchType).calcCostTime(beginTime);
        } catch (Exception e) {
            log.error("获取打卡类型出现异常", e);
            baseResponse.setSuccess(false).setResultCode(ServiceStatus.GENERAL_ERROR.getCode())
                    .setResultMsg(e.getMessage()).calcCostTime(beginTime);
        }
        return baseResponse;
    }

    /**
     * 打卡
     *
     * @param punchNotesDTO
     */
    @PostMapping("/record")
    public BaseResponse doPunch(@RequestBody PunchNotesDTO punchNotesDTO) {
        long beginTime = System.currentTimeMillis();
        Map<String, List<String>> validator = ValidationUtils.validator(punchNotesDTO);
        if (validator != null) {
            BaseResponse baseResponse = new BaseResponse(validator);
            baseResponse.calcCostTime(beginTime);
            return baseResponse;
        }
        BaseResponse baseResponse = new BaseResponse();
        try {
            PunchRecordBO punchRecordBO = BeanConverter.convert(punchNotesDTO, PunchRecordBO.class);
            this.punchService.doPunch(punchRecordBO);

            baseResponse.setResult("success").calcCostTime(beginTime);
        } catch (Exception e) {
            log.error("打卡出现异常", e);
            baseResponse.setSuccess(false).setResultCode(ServiceStatus.GENERAL_ERROR.getCode())
                    .setResultMsg(e.getMessage()).calcCostTime(beginTime);
        }
        return baseResponse;
    }

    @PostMapping("/queryByDate")
    public BaseResponse<List<PunchRecordVO>> queryByDate(
            @RequestBody(required = false) @Valid @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])$") String time,
            BindingResult bindingResult) {
        long beginTime = System.currentTimeMillis();
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> validationErrors = Maps.newHashMapWithExpectedSize(3);
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                List<String> lst = validationErrors.get(field);
                if (lst == null) {
                    lst = Lists.newArrayList();
                }
                lst.add(fieldError.getDefaultMessage());
                validationErrors.put(field, lst);
            }
            BaseResponse baseResponse = new BaseResponse(validationErrors);
            baseResponse.calcCostTime(beginTime);
            return baseResponse;
        }

        BaseResponse<List<PunchRecordVO>> baseResponse = new BaseResponse<>();
        try {
            List<PunchRecordExp> list = this.punchService.queryAll(time);
            List<PunchRecordVO> res = BeanConverter.convert(list, new TypeReference<List<PunchRecordVO>>() {});
            baseResponse.setResult(res).calcCostTime(beginTime);
        } catch (Exception e) {
            log.error("查询list出现异常", e);
            baseResponse.setSuccess(false).setResultCode(ServiceStatus.GENERAL_ERROR.getCode())
                    .setResultMsg(e.getMessage()).calcCostTime(beginTime);
        }
        return baseResponse;
    }


    @PostMapping("/record2")
    public void doPunch2(@RequestBody @Valid PunchNotesDTO punchNotesDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.err.println(fieldError.getField() + "@@" + fieldError.getDefaultMessage());
            }
        }

    }

}

