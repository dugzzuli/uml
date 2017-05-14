package com.mxgraph.model;

   /**
    * tuwenziliao 实体类
    * @author liangzz
    */ 

public class Tuwenziliao{
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
	private String zuozhe;
	 /****/
	private String banbenhao;
	 /****/
	private String leixing;
	 /****/
	private String keliyonglv;
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

	public void setZuozhe(String zuozhe){
		this.zuozhe=zuozhe;
	}

	public String getZuozhe(){
		return zuozhe;
	}

	public void setBanbenhao(String banbenhao){
		this.banbenhao=banbenhao;
	}

	public String getBanbenhao(){
		return banbenhao;
	}

	public void setLeixing(String leixing){
		this.leixing=leixing;
	}

	public String getLeixing(){
		return leixing;
	}

	public void setKeliyonglv(String keliyonglv){
		this.keliyonglv=keliyonglv;
	}

	public String getKeliyonglv(){
		return keliyonglv;
	}

	public void setWuliweizhi(String wuliweizhi){
		this.wuliweizhi=wuliweizhi;
	}

	public String getWuliweizhi(){
		return wuliweizhi;
	}
}

