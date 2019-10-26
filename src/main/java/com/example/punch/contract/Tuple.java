package com.example.punch.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zzs
 * @date 2019/10/26 11:01
 */
@Setter
@Getter
@AllArgsConstructor
public class Tuple<T> {

    private long index;

    private T result;
}
