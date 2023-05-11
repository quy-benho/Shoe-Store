
package com.adc.eshop.service;

import java.util.List;

import com.adc.eshop.controller.vo.IndexCategoryVO;
import com.adc.eshop.controller.vo.SearchPageCategoryVO;
import com.adc.eshop.entity.Category;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.PageResult;

public interface CategoryService {
    
    PageResult getCategorisPage(PageQueryUtil pageUtil);

    String saveCategory(Category category);

    String updateGoodsCategory(Category category);

    Category getGoodsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    
    List<IndexCategoryVO> getCategoriesForIndex();

    
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);

    
    List<Category> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);
}
