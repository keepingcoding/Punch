package com.example.punch.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zzs
 * @date 2019/10/27 18:50
 */
@Setter
@Getter
public class UserDTO implements Serializable {

    @NotNull
    private String loginName;

    @NotNull
    private String password;
}
