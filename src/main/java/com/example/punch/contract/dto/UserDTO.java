package com.example.punch.contract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Setter
@Getter
@ToString
public class UserDTO implements Serializable {

    @NotNull
    private String loginName;

    @NotNull
    private String password;
}
