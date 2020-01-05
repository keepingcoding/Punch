package com.example.punch.dao.ext;

import com.example.punch.model.PunchRecord;
import com.example.punch.model.PunchRecordExp;
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
    @Results(id = "ExpResultMap", value = {
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
            @Result(column = "date_list", property = "dateList"),
            @Result(column = "week_name", property = "weekName"),
    })
    @Select(
            "	SELECT                                                                       "+
            "		t1.date_list,                                                            "+
            "		CASE                                                                     "+
            "			DAYOFWEEK( t1.date_list )                                            "+
            "			WHEN 1 THEN                                                          "+
            "			'星期日'                                                             "+
            "			WHEN 2 THEN                                                          "+
            "			'星期一'                                                             "+
            "			WHEN 3 THEN                                                          "+
            "			'星期二'                                                             "+
            "			WHEN 4 THEN                                                          "+
            "			'星期三'                                                             "+
            "			WHEN 5 THEN                                                          "+
            "			'星期四'                                                             "+
            "			WHEN 6 THEN                                                          "+
            "			'星期五'                                                             "+
            "			WHEN 7 THEN                                                          "+
            "			'星期六'                                                             "+
            "		END week_name,                                                           "+
            "		t2.id,                                                                   "+
            "		t2.user_id,                                                              "+
            "		t2.punch_on_time,                                                        "+
            "		t2.punch_off_time,                                                       "+
            "		t2.punch_status,                                                         "+
            "		t2.punch_on_addr,                                                        "+
            "		t2.punch_off_addr,                                                       "+
            "		t2.on_remark,                                                            "+
            "		t2.off_remark                                                            "+
            "	FROM                                                                         "+
            "		(                                                                        "+
            "		SELECT                                                                   "+
            "			date_add( #{startMonth}, INTERVAL t.help_topic_id DAY ) date_list    "+
            "		FROM                                                                     "+
            "			mysql.help_topic t                                                   "+
            "		WHERE                                                                    "+
            "			t.help_topic_id < DAY ( last_day( #{startMonth} ) )                  "+
            "		) t1                                                                     "+
            "		LEFT JOIN t_punch_record t2 ON t1.date_list = t2.punch_day               "+
            "	ORDER BY                                                                     "+
            "		t1.date_list ASC                                                         "
    )
    List<PunchRecordExp> queryRecordByMonth(String startMonth);

    @ResultMap("ExpResultMap")
    @Select(
            "<script>                                          "+
            "	SELECT                                         "+
            "		t.date_list,                               "+
            "		CASE                                       "+
            "			DAYOFWEEK( t.date_list )               "+
            "			WHEN 1 THEN                            "+
            "			'星期日'                               "+
            "			WHEN 2 THEN                            "+
            "			'星期一'                               "+
            "			WHEN 3 THEN                            "+
            "			'星期二'                               "+
            "			WHEN 4 THEN                            "+
            "			'星期三'                               "+
            "			WHEN 5 THEN                            "+
            "			'星期四'                               "+
            "			WHEN 6 THEN                            "+
            "			'星期五'                               "+
            "			WHEN 7 THEN                            "+
            "			'星期六'                               "+
            "		END week_name,                             "+
            "		t.id,                                      "+
            "		t.user_id,                                 "+
            "		t.punch_on_time,                           "+
            "		t.punch_off_time,                          "+
            "		t.punch_status,                            "+
            "		t.punch_on_addr,                           "+
            "		t.punch_off_addr,                          "+
            "		t.on_remark,                               "+
            "		t.off_remark                               "+
            "	FROM                                           "+
            "		t_punch_record t                           "+
            "	WHERE                                          "+
            "		MONTH(t.punch_day) = MONTH(#{startTime})   "+
            "		<if test='type == 0'>                      "+
            "			t.punch_on_time &gt; #{startTime}      "+
            "		</if>                                      "+
            "		<if test='type == 1'>                      "+
            "			t.punch_off_time &gt; #{startTime}     "+
            "		</if>                                      "+
            "	ORDER BY                                       "+
            "		t.date_list ASC                            "+
            "</script>                                         "

    )
    List<PunchRecordExp> queryRecordByTime(String startTime);
}
