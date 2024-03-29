
package com.adc.eshop.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ShoppingCartItem {
    private Long cartItemId;

    private Long userId;

    private Long goodsId;

    private Integer goodsCount;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private String goodsColor;

    private Integer goodsSize;
}