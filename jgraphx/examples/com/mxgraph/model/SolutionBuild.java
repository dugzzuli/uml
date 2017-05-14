/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.model;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class SolutionBuild implements Serializable {

    private String proid;
    private String objid;

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    @Override
    public String toString() {
        return "测试输出"; //To change body of generated methods, choose Tools | Templates.
    }

    private String qingjingmingcheng;
    private String zhixingzhe;
    private String shuchuchengguo;
    private String date;
    private String place;
    private String standard;
    private String fuzhusheshi;
    private String kongqihuanjing;
    private String kongjian;
    private String zuzhijili;
    private String neirongmiaoshu;

    public String getQingjingmingcheng() {
        return qingjingmingcheng;
    }

    public void setQingjingmingcheng(String qingjingmingcheng) {
        this.qingjingmingcheng = qingjingmingcheng;
    }

    public String getZhixingzhe() {
        return zhixingzhe;
    }

    public void setZhixingzhe(String zhixingzhe) {
        this.zhixingzhe = zhixingzhe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return the shuchuchengguo
     */
    public String getShuchuchengguo() {
        return shuchuchengguo;
    }

    /**
     * @param shuchuchengguo the shuchuchengguo to set
     */
    public void setShuchuchengguo(String shuchuchengguo) {
        this.shuchuchengguo = shuchuchengguo;
    }

    /**
     * @return the standard
     */
    public String getStandard() {
        return standard;
    }

    /**
     * @param standard the standard to set
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * @return the fuzhusheshi
     */
    public String getFuzhusheshi() {
        return fuzhusheshi;
    }

    /**
     * @param fuzhusheshi the fuzhusheshi to set
     */
    public void setFuzhusheshi(String fuzhusheshi) {
        this.fuzhusheshi = fuzhusheshi;
    }

    /**
     * @return the kongqihuanjing
     */
    public String getKongqihuanjing() {
        return kongqihuanjing;
    }

    /**
     * @param kongqihuanjing the kongqihuanjing to set
     */
    public void setKongqihuanjing(String kongqihuanjing) {
        this.kongqihuanjing = kongqihuanjing;
    }

    /**
     * @return the kongjian
     */
    public String getKongjian() {
        return kongjian;
    }

    /**
     * @param kongjian the kongjian to set
     */
    public void setKongjian(String kongjian) {
        this.kongjian = kongjian;
    }

    /**
     * @return the zuzhijili
     */
    public String getZuzhijili() {
        return zuzhijili;
    }

    /**
     * @param zuzhijili the zuzhijili to set
     */
    public void setZuzhijili(String zuzhijili) {
        this.zuzhijili = zuzhijili;
    }

    /**
     * @return the neirongmiaoshu
     */
    public String getNeirongmiaoshu() {
        return neirongmiaoshu;
    }

    /**
     * @param neirongmiaoshu the neirongmiaoshu to set
     */
    public void setNeirongmiaoshu(String neirongmiaoshu) {
        this.neirongmiaoshu = neirongmiaoshu;
    }
}
