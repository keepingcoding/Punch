package com.example.punch.controller;

import com.alibaba.fastjson.TypeReference;
import com.example.punch.contract.BaseResponse;
import com.example.punch.contract.ServiceStatus;
import com.example.punch.contract.bo.PunchRecordBO;
import com.example.punch.contract.dto.PunchNotesDTO;
import com.example.punch.contract.vo.PunchRecordVO;
import com.example.punch.model.PunchRecord;
import com.example.punch.service.PunchService;
import com.example.punch.util.BeanConverter;
import com.example.punch.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author zzs
 * @date 2019/10/24 21:15
 */
@Slf4j
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
    public BaseResponse<Byte> getPunchType(@RequestBody String queryDate) {
        long beginTime = System.currentTimeMillis();
        BaseResponse<Byte> baseResponse = new BaseResponse();
        try {
            Byte punchType = this.punchService.getPunchType(queryDate);
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
        BaseResponse baseResponse= new BaseResponse();
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
    public BaseResponse<List<PunchRecordVO>> queryByDate(@RequestBody(required = false) String time) {
        long beginTime = System.currentTimeMillis();
        BaseResponse<List<PunchRecordVO>> baseResponse = new BaseResponse<>();
        try {
            List<PunchRecord> list = this.punchService.queryAll(time);
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
