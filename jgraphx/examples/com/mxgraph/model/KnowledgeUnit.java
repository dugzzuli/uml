package com.mxgraph.model;

   /**
    * KnowledgeUnit 实体类
    * @author liangzz
    */ 

public class KnowledgeUnit{
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
	private String bianhao;
	 /****/
	private String lingyu;
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

	public void setMingcheng(String mingcheng){
		this.mingcheng=mingcheng;
	}

	public String getMingcheng(){
		return mingcheng;
	}

	public void setBianhao(String bianhao){
		this.bianhao=bianhao;
	}

	public String getBianhao(){
		return bianhao;
	}

	public void setLingyu(String lingyu){
		this.lingyu=lingyu;
	}

	public String getLingyu(){
		return lingyu;
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

