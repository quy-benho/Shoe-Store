
package com.adc.eshop.service.impl;

import com.adc.eshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.adc.eshop.common.ServiceResultEnum;
import com.adc.eshop.controller.vo.SearchGoodsVO;
import com.adc.eshop.dao.GoodsMapper;
import com.adc.eshop.service.GoodsService;
import com.adc.eshop.util.BeanUtil;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.PageResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageResult getGoodsPage(PageQueryUtil pageUtil) {
        List<Product> productList = goodsMapper.findGoodsList(pageUtil);
        int total = goodsMapper.getTotalGoods(pageUtil);
        PageResult pageResult = new PageResult(productList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveGoods(Product product) {
        if (goodsMapper.insertSelective(product) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveGoods(List<Product> newBeeMallProductList) {
        if (!CollectionUtils.isEmpty(newBeeMallProductList)) {
            goodsMapper.batchInsert(newBeeMallProductList);
        }
    }

    @Override
    public String updateGoods(Product product) {
        Product temp = goodsMapper.selectByPrimaryKey(product.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        product.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(product) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Product getGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchGoods(PageQueryUtil pageUtil) {
        List<Product> productList = goodsMapper.findGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalGoodsBySearch(pageUtil).size();
        List<SearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productList)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(productList, SearchGoodsVO.class);
            for (SearchGoodsVO searchGoodsVO : newBeeMallSearchGoodsVOS) {
                String goodsName = searchGoodsVO.getGoodsName();
                String goodsIntro = searchGoodsVO.getGoodsIntro();
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    searchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    searchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(newBeeMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
