package com.mxgraph.model;

/**
 * YUHD 实体类
 *
 * @author liangzz
 */
public class YUHD {

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
    /**
     * *
     */
    private int id;
    /**
     * *
     */
    private String huodongmingcheng;
    /**
     * *
     */
    private String ywuhuodongbianhao;
    /**
     * *
     */
    private String fuguocheng;
    /**
     * *
     */
    private String huodongmiaoshu;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setHuodongmingcheng(String huodongmingcheng) {
        this.huodongmingcheng = huodongmingcheng;
    }

    public String getHuodongmingcheng() {
        return huodongmingcheng;
    }

    public void setYwuhuodongbianhao(String ywuhuodongbianhao) {
        this.ywuhuodongbianhao = ywuhuodongbianhao;
    }

    public String getYwuhuodongbianhao() {
        return ywuhuodongbianhao;
    }

    public void setFuguocheng(String fuguocheng) {
        this.fuguocheng = fuguocheng;
    }

    public String getFuguocheng() {
        return fuguocheng;
    }

    public void setHuodongmiaoshu(String huodongmiaoshu) {
        this.huodongmiaoshu = huodongmiaoshu;
    }

    public String getHuodongmiaoshu() {
        return huodongmiaoshu;
    }
}
