package com.cc.cloud.monitor;

import lombok.Data;

/**
 * @author changcheng
 * @since 2020-03-16 10:55
 */
@Data
public class AlarmMessage {

    private int scopeId;
    private String name;
    private int id0;
    private int id1;

    private String alarmMessage;

    private long startTime;

}
