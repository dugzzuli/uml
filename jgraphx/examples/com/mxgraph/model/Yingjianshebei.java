package com.mxgraph.model;

   /**
    * yingjianshebei 实体类
    * @author liangzz
    */ 

public class Yingjianshebei{
	 /****/
	private int id;
	 /****/
	private String proid;
	 /****/
	private String objid;
	 /****/
	private String mingcheng;
	 /****/
	private String xinghao;
	 /****/
	private String yingjianyaoqiu;
	 /****/
	private String yingyongjiaocheng;
	 /****/
	private String wuliweizhi;

	

	public void setProid(String proid){
		this.proid=proid;
	}

	public String getProid(){
		return proid;
	}

	public void setObjid(String objid){
		this.objid=objid;
	}

	public String getObjid(){
		return objid;
	}

	public void setMingcheng(String mingcheng){
		this.mingcheng=mingcheng;
	}

	public String getMingcheng(){
		return mingcheng;
	}

	public void setXinghao(String xinghao){
		this.xinghao=xinghao;
	}

	public String getXinghao(){
		return xinghao;
	}

	public void setYingjianyaoqiu(String yingjianyaoqiu){
		this.yingjianyaoqiu=yingjianyaoqiu;
	}

	public String getYingjianyaoqiu(){
		return yingjianyaoqiu;
	}

	public void setYingyongjiaocheng(String yingyongjiaocheng){
		this.yingyongjiaocheng=yingyongjiaocheng;
	}

	public String getYingyongjiaocheng(){
		return yingyongjiaocheng;
	}

	public void setWuliweizhi(String wuliweizhi){
		this.wuliweizhi=wuliweizhi;
	}

	public String getWuliweizhi(){
		return wuliweizhi;
	}

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

