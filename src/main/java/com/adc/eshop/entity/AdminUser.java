package com.adc.eshop.entity;


import com.adc.eshop.util.DbParam;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "TB_ADMIN")
public class AdminUser {

    @Id
    @Column(name = DbParam.ID)
    private Integer adminUsrId;
    @Column(name = DbParam.USERNAME)
    private String loginUserName;
    @Column(name = DbParam.PASSWORD)
    private String loginPassword;
    @Column(name = DbParam.NICKNAME)
    private String nickName;
    @Column(name = DbParam.LOCKED)
    private boolean locked;
    @Column(name = DbParam.STATE)
    private String state;
}