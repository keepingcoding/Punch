package com.example.punch.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVO {

    private Integer id;

    private String realName;

    private String loginName;

    private String mobile;

    private String email;

}
