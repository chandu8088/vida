package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Product info bean.
 */
public class ProductInfoBean {

    /**
     * The Item name.
     */
    String item_name;

    String item_image;

    List<ImageVariantBeanModel> variantImageList = new ArrayList<>();

    /**
     * The Product variant info list.
     */
    List<ProductVariantInfoBean> productVariantInfoList = new ArrayList<>();

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public String getItem_name() {
        return item_name;
    }

    /**
     * Sets item name.
     *
     * @param item_name the item name
     */
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    /**
     * Gets product variant info list.
     *
     * @return the product variant info list
     */
    public List<ProductVariantInfoBean> getProductVariantInfoList() {
        return productVariantInfoList;
    }

    /**
     * Sets product variant info list.
     *
     * @param productVariantInfoList the product variant info list
     */
    public void setProductVariantInfoList(List<ProductVariantInfoBean> productVariantInfoList) {
        this.productVariantInfoList = productVariantInfoList;
    }

    public List<ImageVariantBeanModel> getVariantImageList() {
        return variantImageList;
    }

    public void setVariantImageList(List<ImageVariantBeanModel> variantImageList) {
        this.variantImageList = variantImageList;
    }
}
