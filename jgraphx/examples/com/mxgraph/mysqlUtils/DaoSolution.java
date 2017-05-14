/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.SolutionBuild;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DaoSolution {
    
    public static int addSolution(SolutionBuild model) throws SQLException {
        int count = exist(model.getProid(), model.getObjid());
        
        Connection con = DBUtil.getConnection();
        PreparedStatement pat;
//        
//        con.prepareStatement(null)
        if (count <= 0) {
            String sql = "insert into SolutionBuild values(null,'" + model.getProid() + "','" + model.getObjid() + "','" + model.getQingjingmingcheng() + "','" + model.getZhixingzhe() + "','" + model.getShuchuchengguo() + "','" + model.getDate() + "','" + model.getPlace() + "','" + model.getStandard() + "','" + model.getFuzhusheshi() + "','" + model.getKongqihuanjing() + "','" + model.getKongjian() + "','" + model.getZuzhijili() + "','" + model.getNeirongmiaoshu() + "')";
            pat = con.prepareStatement(sql);
        } else {
            String sql = "update SolutionBuild set qingjingmingcheng=?,zhixingzhe=?,shuchuchengguo=?,date=?,place=?,standard=?,fuzhusheshi=?,kongqihuanjing=?,kongjian=?,zuzhijili=?,neirongmiaoshu=? where proid=? and objid=? ";
            pat = con.prepareStatement(sql);
            pat.setObject(1, model.getQingjingmingcheng());
            pat.setObject(2, model.getZhixingzhe());
            pat.setObject(3, model.getShuchuchengguo());
            pat.setObject(4, model.getDate());
            pat.setObject(5, model.getPlace());
            pat.setObject(6, model.getStandard());
            pat.setObject(7, model.getFuzhusheshi());
            pat.setObject(8, model.getKongqihuanjing());
            pat.setObject(9, model.getKongjian());
            pat.setObject(10, model.getZuzhijili());
            pat.setObject(11, model.getNeirongmiaoshu());
            pat.setObject(12, model.getProid());
            pat.setObject(13, model.getObjid());
        }
        return pat.executeUpdate();
    }
    
    public static int exist(String proid, String objid) throws SQLException {
        String sql = "select count(*) from SolutionBuild where proid=? and objid=? ";
        Connection con = DBUtil.getConnection();
        PreparedStatement pat = con.prepareStatement(sql);
        pat.setObject(1, proid);
        pat.setObject(2, objid);
        ResultSet rs = pat.executeQuery();
        int rowCount = 0;
        if (rs.next()) {
            //   rowCount=rs.getInt("record_");      
            rowCount = rs.getInt(1);
        }
        return rowCount;
    }
    
    public static SolutionBuild getModel(String proid, String objid) throws SQLException {
        Connection con = DBUtil.getConnection();
        SolutionBuild model = new SolutionBuild();

//        
//        con.prepareStatement(null)
        String sql = "select * from SolutionBuild where proid=? and objid=? ";
        
        PreparedStatement pat = con.prepareStatement(sql);
        pat.setObject(1, proid);
        pat.setObject(2, objid);
        ResultSet rs = pat.executeQuery();
        
        if (rs.next()) {
            model.setProid(rs.getString("proid"));
            model.setObjid(rs.getString("objid"));
            model.setQingjingmingcheng(rs.getString("qingjingmingcheng"));
            model.setZhixingzhe(rs.getString("zhixingzhe"));
            model.setShuchuchengguo(rs.getString("shuchuchengguo"));
            model.setDate(rs.getString("date"));
            model.setPlace(rs.getString("place"));
            model.setStandard(rs.getString("standard"));
            model.setFuzhusheshi(rs.getString("fuzhusheshi"));
            model.setKongqihuanjing(rs.getString("kongqihuanjing"));
            model.setKongjian(rs.getString("kongjian"));
            model.setZuzhijili(rs.getString("zuzhijili"));
            model.setNeirongmiaoshu(rs.getString("neirongmiaoshu"));
        }
        return model;
    }
    
    public static void main(String[] args) throws SQLException {
        SolutionBuild model = new SolutionBuild();
        model.setDate("132465");
        model.setFuzhusheshi("45645");
        addSolution(model);
    }
}
