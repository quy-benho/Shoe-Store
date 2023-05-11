package com.adc.eshop.entity;

import com.adc.eshop.util.AppContant;
import com.adc.eshop.util.DbParam;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

public class TableGenegic {
    @JsonFormat(pattern = AppContant.YYYYMMDDHHMMSS, timezone = "GMT+8")
    @Column(name = DbParam.UPDATE_AT)
    private Date updateTime;
    @JsonFormat(pattern = AppContant.YYYYMMDDHHMMSS, timezone = "GMT+8")
    @Column(name = DbParam.CREATE_AT)
    private Date createTime;
}
