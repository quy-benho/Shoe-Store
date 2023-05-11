package com.adc.eshop.service;

import com.adc.eshop.entity.ProductDetail;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.PageResult;

import java.util.List;

public interface GoodsDetailService {

    PageResult getGoodsDetailPage(PageQueryUtil pageUtil);

    String saveGoodsDetail(ProductDetail productDetail);

    String updateGoodsDetail(ProductDetail productDetail);

    Integer deleteGoodsDetail(Long id);

    ProductDetail getGoodsDetailById(Long id);

    List<ProductDetail> selectGooDetail(List<Long> parentIds);

    List<String> getListColorById(Long goodsId);

    List<Integer> getListSizeByColor(String goodsColor);
}
