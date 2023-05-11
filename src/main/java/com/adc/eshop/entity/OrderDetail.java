
package com.adc.eshop.entity;

import com.adc.eshop.util.DbParam;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = DbParam.TB_ORDER_DETAIL)
public class OrderDetail {
    @Id
    @Column(name = DbParam.ID)
    private Long orderItemId;
    @Column(name = DbParam.ORDER_ID)
    private Long orderId;
    @Column(name = DbParam.PRODUCT_ID)
    private Long productId;
    @Column(name = DbParam.PRODUCT_NAME)
    private String productName;
    @Column(name = DbParam.PRODUCT_COVER_IMG)
    private String productCoverImg;
    @Column(name = DbParam.SELLING_PRICE)
    private double sellingPrice;
    @Column(name = DbParam.PRODUCT_COUNT)
    private Integer productCount;
    @Column(name = DbParam.CREATE_AT)
    private Date createTime;
    @Column(name = DbParam.COLORS)
    private String productColor;
    @Column(name = DbParam.SIZES)
    private Integer productSize;

}