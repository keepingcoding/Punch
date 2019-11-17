package com.example.punch.dao;

import com.example.punch.model.PunchRecord;
import com.example.punch.model.example.PunchRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PunchRecordMapper {
    long countByExample(PunchRecordExample example);

    int deleteByExample(PunchRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PunchRecord record);

    int insertSelective(PunchRecord record);

    List<PunchRecord> selectByExample(PunchRecordExample example);

    PunchRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PunchRecord record, @Param("example") PunchRecordExample example);

    int updateByExample(@Param("record") PunchRecord record, @Param("example") PunchRecordExample example);

    int updateByPrimaryKeySelective(PunchRecord record);

    int updateByPrimaryKey(PunchRecord record);
}