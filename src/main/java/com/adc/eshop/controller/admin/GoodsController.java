
package com.adc.eshop.controller.admin;

import com.adc.eshop.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.adc.eshop.common.Constants;
import com.adc.eshop.common.CategoryLevelEnum;
import com.adc.eshop.common.ServiceResultEnum;
import com.adc.eshop.entity.Category;
import com.adc.eshop.service.CategoryService;
import com.adc.eshop.service.GoodsService;
import com.adc.eshop.util.PageQueryUtil;
import com.adc.eshop.util.Result;
import com.adc.eshop.util.ResultGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
@RequestMapping("/admin")
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private CategoryService categoryService;

    @GetMapping("/goods")
    public String goodsPage(HttpServletRequest request) {
        request.setAttribute("path", "goods");
        return "admin/goods";
    }

    @GetMapping("/goods/add")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        List<Category> firstLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            List<Category> secondLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), CategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                List<Category> thirdLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), CategoryLevelEnum.LEVEL_THREE.getLevel());
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                request.setAttribute("pageType", "add");
                return "admin/goods_edit";
            }
        }
        return "error/error_5xx";
    }

    @GetMapping("/goods/edit/{goodsId}")
    public String edit(HttpServletRequest request, @PathVariable("goodsId") Long goodsId) {
        request.setAttribute("path", "edit");
        Product product = goodsService.getGoodsById(goodsId);
        if (product == null) {
            return "error/error_400";
        }
        if (product.getGoodsCategoryId() > 0) {
            if (product.getGoodsCategoryId() != null || product.getGoodsCategoryId() > 0) {
                Category currentCategory = categoryService.getGoodsCategoryById(product.getGoodsCategoryId());
                if (currentCategory != null && currentCategory.getCategoryLevel() == CategoryLevelEnum.LEVEL_TWO.getLevel()) {
                    List<Category> firstLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CategoryLevelEnum.LEVEL_ONE.getLevel());
                        List<Category> secondLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentCategory.getParentId()), CategoryLevelEnum.LEVEL_TWO.getLevel());
                        Category firstCategory = categoryService.getGoodsCategoryById(currentCategory.getParentId());

                        if (firstCategory != null) {
                            request.setAttribute("firstLevelCategories", firstLevelCategories);
                            request.setAttribute("secondLevelCategories", secondLevelCategories);
                            request.setAttribute("firstLevelCategoryId", firstCategory.getCategoryId());
                            request.setAttribute("secondLevelCategoryId", currentCategory.getCategoryId());
                        }
                }
            }
        }
        if (product.getGoodsCategoryId() == 0) {
            List<Category> firstLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CategoryLevelEnum.LEVEL_ONE.getLevel());
            if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                List<Category> secondLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), CategoryLevelEnum.LEVEL_TWO.getLevel());
                if (!CollectionUtils.isEmpty(secondLevelCategories)) {
//                    List<GoodsCategory> thirdLevelCategories = categoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), CategoryLevelEnum.LEVEL_THREE.getLevel());
                    request.setAttribute("firstLevelCategories", firstLevelCategories);
                    request.setAttribute("secondLevelCategories", secondLevelCategories);
//                    request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                }
            }
        }
        request.setAttribute("goods", product);
        request.setAttribute("path", "goods-edit");
        request.setAttribute("pageType", "edit");
        return "admin/goods_edit";
    }


    @RequestMapping(value = "/goods/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("Abnormal paramete！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(goodsService.getGoodsPage(pageUtil));
    }


    @RequestMapping(value = "/goods/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Product product) {
        if (StringUtils.isEmpty(product.getGoodsName())
                || StringUtils.isEmpty(product.getGoodsIntro())
                || StringUtils.isEmpty(product.getTag())
                || Objects.isNull(product.getOriginalPrice())
                || Objects.isNull(product.getGoodsCategoryId())
                || Objects.isNull(product.getSellingPrice())
                || Objects.isNull(product.getStockNum())
                || Objects.isNull(product.getGoodsSellStatus())
                || StringUtils.isEmpty(product.getGoodsCoverImg())
                || StringUtils.isEmpty(product.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("Abnormal paramete！");
        }
        String result = goodsService.saveGoods(product);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }



    @RequestMapping(value = "/goods/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Product product) {
        if (Objects.isNull(product.getGoodsId())
                || StringUtils.isEmpty(product.getGoodsName())
                || StringUtils.isEmpty(product.getGoodsIntro())
                || StringUtils.isEmpty(product.getTag())
                || Objects.isNull(product.getOriginalPrice())
                || Objects.isNull(product.getSellingPrice())
                || Objects.isNull(product.getGoodsCategoryId())
                || Objects.isNull(product.getStockNum())
                || Objects.isNull(product.getGoodsSellStatus())
                || StringUtils.isEmpty(product.getGoodsCoverImg())
                || StringUtils.isEmpty(product.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("Abnormal paramete！");
        }
        String result = goodsService.updateGoods(product);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    @GetMapping("/goods/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        Product product = goodsService.getGoodsById(id);
        if (product == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(product);
    }


    @RequestMapping(value = "/goods/status/{sellStatus}", method = RequestMethod.PUT)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids, @PathVariable("sellStatus") int sellStatus) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Abnormal paramete！");
        }
        if (sellStatus != Constants.SELL_STATUS_UP && sellStatus != Constants.SELL_STATUS_DOWN) {
            return ResultGenerator.genFailResult("Abnormal state！");
        }
        if (goodsService.batchUpdateSellStatus(ids, sellStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("cannot edit");
        }
    }

}