
package com.adc.eshop.entity;

import com.adc.eshop.util.AppContant;
import com.adc.eshop.util.DbParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.annotation.Native;
import java.util.Date;
@Data
@Entity(name = DbParam.TB_USER)
public class User {

    @Id
    @Column(name = DbParam.USER_ID)
    private Long userId;
    @Column(name = DbParam.NICKNAME)
    private String nickName;

    @Column(name = DbParam.USERNAME)
    private String loginName;

    @Column(name = DbParam.PASSWORD)
    private String passwordMd5;
    @Column(name = DbParam.INTRO_DUCE_SIGN)
    private String introduceSign;
    @Column(name = DbParam.PASSWORD)
    private String address;

    @Column(name = DbParam.IS_DELETED)
    private boolean deleted;
    @Column(name = DbParam.LOCKED)
    private boolean lockedFlag;

    @JsonFormat(pattern = AppContant.YYYYMMDDHHMMSS, timezone = "GMT+8")
    @Column(name = DbParam.CREATE_AT)
    private Date createTime;
}