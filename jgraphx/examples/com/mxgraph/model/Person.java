package com.mxgraph.model;

   /**
    * Person 实体类
    * @author liangzz
    */ 

public class Person{
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
	private String xingming;
	 /****/
	private String gonghao;
	 /****/
	private String lingyu;
	 /****/
	private String bumen;
	 /****/
	private String zhishineirong;
	 /****/
	private String suoshuzaiti;

	public void setId(int id){
		this.id=id;
	}

	public int getId(){
		return id;
	}

	public void setXingming(String xingming){
		this.xingming=xingming;
	}

	public String getXingming(){
		return xingming;
	}

	public void setGonghao(String gonghao){
		this.gonghao=gonghao;
	}

	public String getGonghao(){
		return gonghao;
	}

	public void setLingyu(String lingyu){
		this.lingyu=lingyu;
	}

	public String getLingyu(){
		return lingyu;
	}

	public void setBumen(String bumen){
		this.bumen=bumen;
	}

	public String getBumen(){
		return bumen;
	}

	public void setZhishineirong(String zhishineirong){
		this.zhishineirong=zhishineirong;
	}

	public String getZhishineirong(){
		return zhishineirong;
	}

	public void setSuoshuzaiti(String suoshuzaiti){
		this.suoshuzaiti=suoshuzaiti;
	}

	public String getSuoshuzaiti(){
		return suoshuzaiti;
	}
}

