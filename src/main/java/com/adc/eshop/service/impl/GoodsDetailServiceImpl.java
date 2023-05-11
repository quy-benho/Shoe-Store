package com.adc.eshop.service.impl;

import com.adc.eshop.common.ServiceResultEnum;
import com.adc.eshop.dao.GoodsDetailMapper;
import com.adc.eshop.entity.ProductDetail;
import com.adc.eshop.service.GoodsDetailService;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsDetailServiceImpl implements GoodsDetailService {

    @Autowired
    GoodsDetailMapper goodsDetailMapper;

    @Override
    public PageResult getGoodsDetailPage(PageQueryUtil pageUtil) {
        List<ProductDetail> productDetails = goodsDetailMapper.findGoodsDetailList(pageUtil);
        int total = goodsDetailMapper.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(productDetails, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveGoodsDetail(ProductDetail productDetail) {
        productDetail.setIsDeleted((byte) 0);
        if (goodsDetailMapper.insertSelective(productDetail) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateGoodsDetail(ProductDetail productDetail) {
        ProductDetail temp = goodsDetailMapper.selectByPrimaryKey(productDetail.getGoodsDeatailId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (goodsDetailMapper.updateByPrimaryKeySelective(productDetail) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public ProductDetail getGoodsDetailById(Long id) {
        return null;
    }

    @Override
    public List<ProductDetail> selectGooDetail(List<Long> parentIds) {
        return null;
    }

    @Override
    public List<String> getListColorById(Long goodsId) {
        return goodsDetailMapper.getListColorById(goodsId);
    }

    @Override
    public List<Integer> getListSizeByColor(String goodsColor) {
        return goodsDetailMapper.getListSizeByColor(goodsColor);
    }

	@Override
	public Integer deleteGoodsDetail(Long id) {
		return goodsDetailMapper.deleteByPrimaryKey(id);
	}
}
