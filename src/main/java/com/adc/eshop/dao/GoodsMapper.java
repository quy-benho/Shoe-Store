package com.adc.eshop.dao;

import com.adc.eshop.entity.Product;
import org.apache.ibatis.annotations.Param;

import com.adc.eshop.entity.StockNumDTO;
import com.adc.eshop.util.PageQueryUtil;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findGoodsList(PageQueryUtil pageUtil);

    int getTotalGoods(PageQueryUtil pageUtil);

    List<Product> selectByPrimaryKeys(List<Long> goodsIds);

    List<Product> findGoodsListBySearch(PageQueryUtil pageUtil);

    List<Product> getTotalGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("GoodsList") List<Product> newBeeMallProductList);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);

    int batchUpdateSellStatus(@Param("orderIds")Long[] orderIds,@Param("sellStatus") int sellStatus);
}
