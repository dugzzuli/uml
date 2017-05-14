package com.mxgraph.model;

   /**
    * ruanjianshebei 实体类
    * @author liangzz
    */ 

public class Ruanjianshebei{
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
	 /****/
	private int id;
	 /****/
	private String mingcheng;
	 /****/
	private String banben;
	 /****/
	private String yingyongfanwei;
	 /****/
	private String yingyongjiaocheng;
	 /****/
	private String wuliweizhi;

	public void setId(int id){
		this.id=id;
	}

	public int getId(){
		return id;
	}

	public void setMingcheng(String mingcheng){
		this.mingcheng=mingcheng;
	}

	public String getMingcheng(){
		return mingcheng;
	}

	public void setBanben(String banben){
		this.banben=banben;
	}

	public String getBanben(){
		return banben;
	}

	public void setYingyongfanwei(String yingyongfanwei){
		this.yingyongfanwei=yingyongfanwei;
	}

	public String getYingyongfanwei(){
		return yingyongfanwei;
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
}

