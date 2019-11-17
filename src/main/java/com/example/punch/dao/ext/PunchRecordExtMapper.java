package com.example.punch.dao.ext;

import com.example.punch.model.PunchRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author zzs
 * @date 2019/11/14 21:13
 */
public interface PunchRecordExtMapper {


    /** 查询指定日期的上班卡记录 **/
    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id",id = true),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "punch_day", property = "punchDay"),
            @Result(column = "punch_on_time", property = "punchOnTime"),
            @Result(column = "punch_off_time", property = "punchOffTime"),
            @Result(column = "punch_status", property = "punchStatus"),
            @Result(column = "punch_on_addr", property = "punchOnAddr"),
            @Result(column = "punch_off_addr", property = "punchOffAddr"),
            @Result(column = "on_remark", property = "onRemark"),
            @Result(column = "off_remark", property = "offRemark"),
            @Result(column = "created_id", property = "createdId"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "updated_id", property = "updatedId"),
            @Result(column = "updated_time", property = "updatedTime"),
    })
    @Select(
            "	SELECT                                            "+
            "		id,                                           "+
            "		user_id,                                      "+
            "		punch_day,                                    "+
            "		punch_on_time,                                "+
            "		punch_off_time,                               "+
            "		punch_status,                                 "+
            "		punch_on_addr,                                "+
            "		punch_off_addr,                               "+
            "		on_remark,                                    "+
            "		off_remark,                                   "+
            "		created_id,                                   "+
            "		created_time,                                 "+
            "		updated_id,                                   "+
            "		updated_time                                  "+
            "	FROM                                              "+
            "		t_punch_record t                              "+
            "	WHERE                                             "+
            "		date( t.punch_day ) = date( #{queryMonth} )   "+
            "	LIMIT 1                                           "
    )
    PunchRecord getWorkRecordByDate(Date queryDate);

    /** 新增打卡记录 **/
    @Insert(
            "	INSERT INTO t_punch_record ( user_id, punch_day, punch_on_time, punch_off_time,         "+
            "		punch_status, punch_on_addr, punch_off_addr, on_remark, off_remark,                 "+
            "       created_id, created_time, updated_id, updated_time)                                 "+
            "	VALUES                                                                                  "+
            "		(                                                                                   "+
            "			#{userId}, #{punchDay}, #{punchOnTime}, null,                                   "+
            "			#{punchStatus}, #{punchOnAddr}, null, #{onRemark}, null,                        "+
            "			#{createdId}, #{createdTime}, null, null                                        "+
            "		)                                                                                   "
    )
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int addOnWorkRecord(PunchRecord punchRecord);

    /** 更新下班卡 **/
    @Update(
            "<script>"+
            "	UPDATE t_punch_record                       "+
            "	SET punch_off_time = #{punchOffTime},       "+
            "		<if test='punchStatus != null'>         "+
            "			punch_status = #{punchStatus},      "+
            "		</if>                                   "+
            "		<if test='punchOffAddr != null'>        "+
            "			punch_off_addr = #{punchOffAddr},   "+
            "		</if>                                   "+
            "		<if test='offRemark != null'>           "+
            "			off_remark = #{offRemark},          "+
            "		</if>                                   "+
            "		updated_id = #{updatedId},              "+
            "		updated_time = #{updatedTime}           "+
            "	WHERE                                       "+
            "		id = #{id}                              "+
            "</script>"
    )
    int updateOffWorkRecord(PunchRecord punchRecord);

    /** 查询指定月份的打卡记录 **/
    @ResultMap("BaseResultMap")
    @Select(
            "	SELECT                                                    "+
            "		id,                                                   "+
            "		user_id,                                              "+
            "		punch_day,                                            "+
            "		punch_on_time,                                        "+
            "		punch_off_time,                                       "+
            "		punch_status,                                         "+
            "		punch_on_addr,                                        "+
            "		punch_off_addr,                                       "+
            "		on_remark,                                            "+
            "		off_remark                                            "+
            "	FROM                                                      "+
            "		t_punch_record t                                      "+
            "	WHERE                                                     "+
            "		DATE_FORMAT( t.punch_day, '%Y-%m' ) = #{queryMonth}   "
    )
    List<PunchRecord> queryRecordByMonth(String queryMonth);
}
