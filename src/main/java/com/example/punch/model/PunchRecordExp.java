package com.example.punch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PunchRecordExp extends PunchRecord implements Serializable {

    private String dateList;

    private String weekName;
}