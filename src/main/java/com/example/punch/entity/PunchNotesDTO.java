package com.example.punch.entity;

import com.google.common.base.Converter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author zzs
 * @date 2019/10/24 21:30
 */
@Setter
@Getter
public class PunchNotesDTO {

    /** 打卡时间 **/
    @NotNull
    private String time;

    /** 打卡地点 **/
    private String location;

    /** 备注 **/
    private String remark;

    public PunchNotes covertToPunchNotes() {
        PunchNotesConverter punchNotesConverter = new PunchNotesConverter();
        return punchNotesConverter.convert(this);
    }

    private static class PunchNotesConverter extends Converter<PunchNotesDTO, PunchNotes> {
        @Override
        protected PunchNotes doForward(PunchNotesDTO punchNotesDTO) {
            PunchNotes punchNotes = new PunchNotes();
            BeanUtils.copyProperties(punchNotesDTO, punchNotes);
            return punchNotes;
        }

        @Override
        protected PunchNotesDTO doBackward(PunchNotes punchNotes) {
            throw new AssertionError("不支持逆向转化方法!");
        }
    }
}
