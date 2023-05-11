package com.adc.eshop.dao;

import com.adc.eshop.entity.ProductDetail;
import com.adc.eshop.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDetailMapper {
    int deleteByPrimaryKey(Long goodsDetailId);

    int insert(ProductDetail record);

    int insertSelective(ProductDetail record);

    ProductDetail selectByPrimaryKey(Long goodsDetailId);

    int updateByPrimaryKeySelective(ProductDetail record);

    int updateByPrimaryKey(ProductDetail record);

    List<ProductDetail> findGoodsDetailList(PageQueryUtil pageUtil);

    int getTotalGoodsCategories(PageQueryUtil pageUtil);

    List<String> getListColorById(@Param("goodsId") Long goodsId);

    List<Integer> getListSizeByColor(@Param("goodsColor") String goodsColor);

}
