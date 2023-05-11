
package com.adc.eshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.adc.eshop.common.Constants;
import com.adc.eshop.common.CategoryLevelEnum;
import com.adc.eshop.common.ServiceResultEnum;
import com.adc.eshop.controller.vo.IndexCategoryVO;
import com.adc.eshop.controller.vo.SearchPageCategoryVO;
import com.adc.eshop.controller.vo.SecondLevelCategoryVO;
import com.adc.eshop.dao.GoodsCategoryMapper;
import com.adc.eshop.entity.Category;
import com.adc.eshop.service.CategoryService;
import com.adc.eshop.util.BeanUtil;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.PageResult;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public PageResult getCategorisPage(PageQueryUtil pageUtil) {
        List<Category> goodsCategories = goodsCategoryMapper.findGoodsCategoryList(pageUtil);
        int total = goodsCategoryMapper.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(goodsCategories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCategory(Category category) {
        Category temp = goodsCategoryMapper.selectByLevelAndName(category.getCategoryLevel(), category.getCategoryName());
        if (temp != null) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        if (goodsCategoryMapper.insertSelective(category) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }



    @Override
    public String updateGoodsCategory(Category category) {
        Category temp = goodsCategoryMapper.selectByPrimaryKey(category.getCategoryId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        Category temp2 = goodsCategoryMapper.selectByLevelAndName(category.getCategoryLevel(), category.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(category.getCategoryId())) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        category.setUpdateTime(new Date());
        if (goodsCategoryMapper.updateByPrimaryKeySelective(category) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Category getGoodsCategoryById(Long id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return goodsCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<IndexCategoryVO> getCategoriesForIndex() {
        List<IndexCategoryVO> newBeeMallIndexCategoryVOS = new ArrayList<>();
        List<Category> firstLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(Category::getCategoryId).collect(Collectors.toList());
            List<Category> secondLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, CategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                List<Long> secondLevelCategoryIds = secondLevelCategories.stream().map(Category::getCategoryId).collect(Collectors.toList());
                List<Category> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(secondLevelCategoryIds, CategoryLevelEnum.LEVEL_THREE.getLevel(), 0);
                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
                    List<SecondLevelCategoryVO> secondLevelCategoryVOS = new ArrayList<>();
                    for (Category secondLevelCategory : secondLevelCategories) {
                        SecondLevelCategoryVO secondLevelCategoryVO = new SecondLevelCategoryVO();
                        BeanUtil.copyProperties(secondLevelCategory, secondLevelCategoryVO);
                        secondLevelCategoryVOS.add(secondLevelCategoryVO);
                    }
                    if (!CollectionUtils.isEmpty(secondLevelCategoryVOS)) {
                        Map<Long, List<SecondLevelCategoryVO>> secondLevelCategoryVOMap = secondLevelCategoryVOS.stream().collect(groupingBy(SecondLevelCategoryVO::getParentId));
                        for (Category firstCategory : firstLevelCategories) {
                            IndexCategoryVO indexCategoryVO = new IndexCategoryVO();
                            BeanUtil.copyProperties(firstCategory, indexCategoryVO);
                            if (secondLevelCategoryVOMap.containsKey(firstCategory.getCategoryId())) {
                                List<SecondLevelCategoryVO> tempGoodsCategories = secondLevelCategoryVOMap.get(firstCategory.getCategoryId());
                                indexCategoryVO.setSecondLevelCategoryVOS(tempGoodsCategories);
                                newBeeMallIndexCategoryVOS.add(indexCategoryVO);
                            }
                        }
                    }
                }
            }
            return newBeeMallIndexCategoryVOS;
        } else {
            return null;
        }
    }

    @Override
    public SearchPageCategoryVO getCategoriesForSearch(Long categoryId) {
        SearchPageCategoryVO searchPageCategoryVO = new SearchPageCategoryVO();
        Category thirdLevelCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
        if (thirdLevelCategory != null && thirdLevelCategory.getCategoryLevel() == CategoryLevelEnum.LEVEL_THREE.getLevel()) {
            Category secondLevelCategory = goodsCategoryMapper.selectByPrimaryKey(thirdLevelCategory.getParentId());
            if (secondLevelCategory != null && secondLevelCategory.getCategoryLevel() == CategoryLevelEnum.LEVEL_TWO.getLevel()) {
                List<Category> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategory.getCategoryId()), CategoryLevelEnum.LEVEL_THREE.getLevel(), Constants.SEARCH_CATEGORY_NUMBER);
                searchPageCategoryVO.setCurrentCategoryName(thirdLevelCategory.getCategoryName());
                searchPageCategoryVO.setSecondLevelCategoryName(secondLevelCategory.getCategoryName());
                searchPageCategoryVO.setThirdLevelCategoryList(thirdLevelCategories);
                return searchPageCategoryVO;
            }
        }
        return null;
    }

    @Override
    public List<Category> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);//0代表查询所有
    }
}
