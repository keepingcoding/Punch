package com.example.punch.controller;

import com.example.punch.contract.BaseResponse;
import com.example.punch.contract.ServiceStatus;
import com.example.punch.model.User;
import com.example.punch.contract.dto.UserDTO;
import com.example.punch.contract.vo.UserVO;
import com.example.punch.service.LoginService;
import com.example.punch.util.BeanConverter;
import com.example.punch.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzs
 * @date 2019/10/27 18:22
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserDTO userDTO) {
        long beginTime = System.currentTimeMillis();
        Map<String, List<String>> validator = ValidationUtils.validator(userDTO);
        if (validator != null) {
            BaseResponse baseResponse = new BaseResponse(validator);
            baseResponse.calcCostTime(beginTime);
            return baseResponse;
        }
        BaseResponse baseResponse = new BaseResponse();
        try {
            User user = BeanConverter.convert(userDTO, User.class);
            User checkUser = this.loginService.checkUser(user);
            if (checkUser != null) {
                UserVO userVO = BeanConverter.convert(checkUser, UserVO.class);

                Map<String, Object> resultMap = new HashMap<>(4);
                resultMap.put("user", userVO);
                baseResponse.setResult(resultMap);
            } else {
                baseResponse.setSuccess(true).setResultCode(ServiceStatus.AUTHEN_ERROR.getCode())
                        .setResultMsg(ServiceStatus.AUTHEN_ERROR.getDescription());
            }
            baseResponse.calcCostTime(beginTime);

        } catch (Exception e) {
            log.error("用户登录验证失败", e);
            baseResponse.setSuccess(false).setResultCode(ServiceStatus.GENERAL_ERROR.getCode())
                    .setResultMsg(ServiceStatus.GENERAL_ERROR.getDescription()).calcCostTime(beginTime);
        }
        return baseResponse;
    }
}
