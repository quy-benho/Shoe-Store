package com.adc.eshop.entity;


import com.adc.eshop.util.DbParam;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = DbParam.TB_PRODUCT_DETAIL)
public class ProductDetail {

    @Id
    @Column(name = DbParam.ID)
    private Long productDeatailId;
    @Column(name = DbParam.COLORS)
    private String productColor;
    @Column(name = DbParam.SIZES)
    private Integer productSize;
    @Column(name = DbParam.QUANTITY)
    private Integer productQuantity;
    @Column(name = DbParam.PRODUCT_ID)
    private Long productId;
    @Column(name = DbParam.IS_DELETED)
    private boolean isDeleted;

    @Column(name = DbParam.STATE)
    private String state;

}
