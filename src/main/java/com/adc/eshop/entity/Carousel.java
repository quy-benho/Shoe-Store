
package com.adc.eshop.entity;

import com.adc.eshop.util.AppContant;
import com.adc.eshop.util.DbParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity(name = DbParam.TB_CAROUSEL)
public class Carousel extends TableGenegic{
    @Id
    @Column(name = DbParam.ID)
    private Integer carouselId;
    @Column(name = DbParam.CAROUSEL_URL)
    private String carouselUrl;
    @Column(name = DbParam.REDIRECT_URL)
    private String redirectUrl;
    @Column(name = DbParam.CAROUSEL_RANK)
    private Integer carouselRank;
    @Column(name = DbParam.IS_DELETED)
    private boolean deleted;
    @Column(name = DbParam.CREATE_USER)
    private Integer createUser;
    @Column(name = DbParam.UPDATE_USER)
    private Integer updateUser;
    @Column(name = DbParam.STATE)
    private String state;

}