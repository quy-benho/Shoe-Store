
package com.adc.eshop.entity;

import com.adc.eshop.util.DbParam;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = DbParam.TB_INDEX_CONFIG)
public class IndexConfig extends TableGenegic {

    @Id                                 
    @Column(name = DbParam.ID)
    private String configId;
    @Column(name = DbParam.CONFIG_NAME)
    private String configName;
    @Column(name = DbParam.CONFIG_TYPE)
    private boolean configType;
    @Column(name = DbParam.PRODUCT_ID)
    private String productId;
    @Column(name = DbParam.REDIRECT_URL)
    private String redirectUrl;
    @Column(name = DbParam.CONFIG_RANK)
    private Integer configRank;
    @Column(name = DbParam.IS_DELETED)
    private boolean isDeleted;
    @Column(name = DbParam.CREATE_USER)
    private Integer createUser;
    @Column(name = DbParam.UPDATE_USER)
    private Integer updateUser;

}