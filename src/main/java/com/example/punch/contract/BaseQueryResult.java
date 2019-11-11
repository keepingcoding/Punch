package com.example.punch.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseQueryResult<T> {

    private PageInfo pageInfo;

    private List<T> list;
}
