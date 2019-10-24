package com.example.punch.controller;

import com.example.punch.contract.BaseResponse;
import com.example.punch.contract.exception.ServiceException;
import com.example.punch.entity.PunchNotesDTO;
import com.example.punch.service.PunchService;
import com.example.punch.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
     * 打卡
     *
     * @param punchNotesDTO
     */
    @PostMapping("/record")
    public BaseResponse doPunch(@RequestBody PunchNotesDTO punchNotesDTO) {
        long beginTime = System.currentTimeMillis();
        Map<String, List<String>> validator = ValidationUtil.validator(punchNotesDTO);
        if (validator != null) {
            BaseResponse baseResponse = new BaseResponse(validator);
            baseResponse.calcCostTime(beginTime);
            return baseResponse;
        }
        BaseResponse baseResponse = new BaseResponse();
        try {

            baseResponse.setResult("success").calcCostTime(beginTime);
        } catch (Exception e) {
            log.error("打卡出现异常", e);
            baseResponse.setStatusCode("200")
                    .setResultCode(ServiceException.GENERAL_ERROR)
                    .setResultMsg(e.getMessage())
                    .calcCostTime(beginTime);
        }
        return baseResponse;
    }

    @PostMapping("/record2")
    public void doPunch2(@RequestBody @Valid PunchNotesDTO punchNotesDTO, BindingResult bindingResult) {
        System.err.println(bindingResult);
    }
}
