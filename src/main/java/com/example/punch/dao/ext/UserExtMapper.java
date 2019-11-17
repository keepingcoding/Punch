package com.example.punch.dao.ext;

import com.example.punch.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author zzs
 * @date 2019/11/13 21:39
 */
public interface UserExtMapper {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "login_name", property = "loginName"),
            @Result(column = "password", property = "password"),
            @Result(column = "mobile", property = "mobile"),
            @Result(column = "email", property = "email"),
            @Result(column = "status", property = "status"),
            @Result(column = "is_del", property = "isDel"),
    })
    @Select(
            "	SELECT                                                                        " +
            "		id, real_name, login_name, `password`, mobile, email, `status`, is_del    " +
            "	FROM                                                                          " +
            "		`sys_user` t                                                              " +
            "	WHERE                                                                         " +
            "		t.is_del = 0                                                              " +
            "		AND t.`status` = 1                                                        " +
            "		AND t.login_name = #{loginName}                                           " +
            "		AND t.`password` = #{password}                                            "
    )
    User checkUser(@Param("loginName") String loginName, @Param("password") String password);
}
