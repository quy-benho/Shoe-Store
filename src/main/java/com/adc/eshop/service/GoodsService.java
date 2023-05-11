
package com.adc.eshop.service;

import java.util.List;

import com.adc.eshop.entity.Product;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.PageResult;

public interface GoodsService {
    
    PageResult getGoodsPage(PageQueryUtil pageUtil);

    
    String saveGoods(Product product);

    
    void batchSaveGoods(List<Product> newProductList);

    
    String updateGoods(Product product);

    
    Product getGoodsById(Long id);

    
    Boolean batchUpdateSellStatus(Long[] ids,int sellStatus);

    
    PageResult searchGoods(PageQueryUtil pageUtil);
}
