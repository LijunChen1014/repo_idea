package com.chen.dao;

import com.chen.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*
    分页查询广告信息
     */
     public List<PromotionAd> findAllPromotionAdByPage();

     public PromotionAd findAllPromotionAdById(Integer id);

     /*
     广告动态上下线
      */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

    /*
    新建广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
    更新广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

}
