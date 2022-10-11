package com.chen.service;

import com.chen.domain.PromotionAd;
import com.chen.domain.PromotionAdVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PromotionAdService {


    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);
    /*
   广告动态上下线
    */
    public void updatePromotionAdStatus(int id,int status);
    /*
    新建广告
     */
    public void savePromotionAd(PromotionAd promotionAd);
    /*
    更新广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    public PromotionAd findAllPromotionAdById(Integer id);
}
