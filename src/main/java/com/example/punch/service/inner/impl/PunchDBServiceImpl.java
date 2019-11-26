package com.example.punch.service.inner.impl;

import com.example.punch.contract.vo.PunchRecordVO;
import com.example.punch.dao.ext.PunchRecordExtMapper;
import com.example.punch.model.PunchRecord;
import com.example.punch.model.PunchRecordExp;
import com.example.punch.service.inner.PunchDBService;
import com.example.punch.util.BeanConverter;
import com.example.punch.util.DateUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.punch.contract.CommonConstant.PunchType;

/**
 * @author zzs
 * @date 2019/11/15 22:34
 */
@Slf4j
@Service
public class PunchDBServiceImpl implements PunchDBService {

    @Autowired
    private PunchRecordExtMapper punchRecordExtMapper;

    /** 获取打卡类型 **/
    @Override
    public Map<String,Object> getPunchType(String date) {
        /*
            有上班卡才有下班卡
            1.第一次打卡是上班卡，第二次打下班卡，后面的每次都是更新下班卡，直到第二天6点钟
            2.判断上下班：
            首先判断今天有没有上班打卡，
                有则是更新下班卡
                没有
                    首先当前打卡时间是否在7点之后，
                        是直接当作上班卡，
                        否则查询前一天有没有上班记录，
                            有则更新前一天的下班卡
                            没有则是今天的上班卡

            准备两个接口
                一个用于查询要打上班卡还是下班卡
                一个用于记录打卡
         */
        Map<String,Object> resultMap = Maps.newHashMapWithExpectedSize(4);

        // 0.判断今天有没有上班打卡
        LocalDate curDate = LocalDate.now();
        Date today = Date.from(curDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        PunchRecord todayPunchRecord = this.punchRecordExtMapper.getWorkRecordByDate(today);
        if (Objects.nonNull(todayPunchRecord)) {
            PunchRecordVO convert = BeanConverter.convert(todayPunchRecord, PunchRecordVO.class);
            resultMap.put("data", convert);
            resultMap.put("type", PunchType.OFF_WORK.getCode());
            return resultMap;
        }

        // 获取上班时间
        LocalDateTime localDateTime = curDate.atTime(6, 0);
        long workStartTime = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        // 1.以6点做分界线
        long queryDate = DateUtils.parseDateTime(date, "yyyy-MM-dd HH:mm:ss").getTime();
        if (queryDate > workStartTime) {
            resultMap.put("data", null);
            resultMap.put("type", PunchType.ON_WORK.getCode());
            return resultMap;
        }

        // 2.查询昨天有没有上班记录
        Date yesterday = Date.from(curDate.minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        PunchRecord onWorkRecord = this.punchRecordExtMapper.getWorkRecordByDate(yesterday);
        // 3.没有上班记录则是上班卡
        if (Objects.isNull(onWorkRecord)) {
            resultMap.put("data", null);
            resultMap.put("type", PunchType.ON_WORK.getCode());
            return resultMap;
        }

        // 4.否则便是下班卡
        PunchRecordVO convert = BeanConverter.convert(onWorkRecord, PunchRecordVO.class);
        resultMap.put("data", convert);
        resultMap.put("type", PunchType.OFF_WORK.getCode());
        return resultMap;
    }

    /**
     * 写到数据库
     */
    @Override
    public void writeToDb(PunchRecord punchRecord, Byte punchType) throws Exception {
        switch (punchType) {
            case 0:
                doOnWork(punchRecord);
                break;
            case 1:
                doOffWork(punchRecord);
                break;
            default:
                log.error(">>> Wrong parameter [punchType:{}].", punchType);
        }
    }

    /** 上班卡 **/
    private void doOnWork(PunchRecord punchRecord) throws Exception {
        // 判断今天是否已存在该打卡记录
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PunchRecord onWorkRecord = this.punchRecordExtMapper.getWorkRecordByDate(today);
        if (Objects.nonNull(onWorkRecord)) {
            log.error(">>> You have already punch on work card today.");
            throw new Exception("今天已经打过上班卡");
        }

        punchRecord.setUserId(1);
        punchRecord.setPunchDay(today);
        punchRecord.setCreatedId(1);
        punchRecord.setCreatedTime(System.currentTimeMillis());

        this.punchRecordExtMapper.addOnWorkRecord(punchRecord);
        log.info(">>> Add a new record: [{}].", punchRecord);
    }

    /** 下班卡 **/
    private void doOffWork(PunchRecord punchRecord) throws Exception {
        //查询今天的下班卡记录
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PunchRecord offWorkRecord = this.punchRecordExtMapper.getWorkRecordByDate(today);
        if (offWorkRecord == null) {
            log.error(">>> Query today`s off record error.");
            throw new Exception("未查询到今天的下班卡记录");
        }

        offWorkRecord.setPunchOffTime(punchRecord.getPunchOffTime());
        offWorkRecord.setUpdatedId(1);
        offWorkRecord.setUpdatedTime(System.currentTimeMillis());

        this.punchRecordExtMapper.updateOffWorkRecord(offWorkRecord);
        log.info(">>> Update an existing record: [{}].", offWorkRecord);
    }


    /** 从数据库读取 **/
    @Override
    public List<PunchRecordExp> readFromDb(String time) {
        time = time + "-01";
        return this.punchRecordExtMapper.queryRecordByMonth(time);
    }
}
