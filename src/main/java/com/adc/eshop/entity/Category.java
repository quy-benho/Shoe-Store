
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
@Entity(name = DbParam.TB_CATEGORY)
public class Category extends TableGenegic {

    @Id
    @Column(name = DbParam.ID)
    private String categoryId;

    @Column(name = DbParam.CATEGORY_LEVEL)
    private boolean categoryLevel;

    @Column(name = DbParam.PARENT_ID)
    private String parentId;

    @Column(name = DbParam.NAME)
    private String categoryName;
    @Column(name = DbParam.CATEGORY_RANK)
    private Integer categoryRank;

    @Column(name = DbParam.IS_DELETED)
    private boolean isDeleted;

    @Column(name = DbParam.CREATE_USER)
    private Integer createUser;

    @Column(name = DbParam.UPDATE_USER)
    private Integer updateUser;

}