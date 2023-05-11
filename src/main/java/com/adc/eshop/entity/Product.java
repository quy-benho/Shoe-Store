
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
@Entity(name = DbParam.PRODUCT)
public class Product {
    
    @Id
    @Column(name = DbParam.ID)
    private Long productId;
    @Column(name = DbParam.PRODUCT_NAME)
    private String productName;
    @Column(name = DbParam.PRODUCT_INTRO)
    private String productIntro;
    @Column(name = DbParam.CATEGORY_ID)
    private Long productCategoryId;
    @Column(name = DbParam.PRODUCT_COVER_IMG)
    private String productCoverImg;
    @Column(name = DbParam.PRODUCT_CAROUSEL)
    private String productCarousel;
    @Column(name = DbParam.ORIGINAL_PRICE)
    private Integer originalPrice;
    @Column(name = DbParam.SELLING_PRICE)
    private Integer sellingPrice;
    @Column(name = DbParam.STOCK)
    private Integer stockNum;
    @Column(name = DbParam.TAG)
    private String tag;
    @Column(name = DbParam.PRODUCT_SELL_STATUS)
    private Byte productSellStatus;
    @Column(name = DbParam.CREATE_USER)
    private Integer createUser;
    @Column(name = DbParam.CREATE_AT)
    @JsonFormat(pattern = AppContant.YYYYMMDDHHMMSS, timezone = "GMT+8")
    private Date createTime;
    @Column(name = DbParam.UPDATE_USER)
    private Integer updateUser;
    @JsonFormat(pattern = AppContant.YYYYMMDDHHMMSS, timezone = "GMT+8")
    @Column(name = DbParam.UPDATE_AT)
    private Date updateTime;
    @Column(name = DbParam.PRODUCT_DETAIL_CONTENT)
    private String productDetailContent;


}