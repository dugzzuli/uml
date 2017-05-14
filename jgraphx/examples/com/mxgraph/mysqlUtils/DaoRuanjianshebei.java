/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Jisuanjiziyuan;
import com.mxgraph.model.Ruanjianshebei;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DaoRuanjianshebei {
    
    public static int add(Ruanjianshebei model) throws SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement psStat = null;
        int count = DBUtil.getCount("select count(*) from ruanjianshebei where proid='" + model.getProid() + "' and objid='" + model.getObjid() + "'");
        String sql = "";
        if (count > 0) {
            sql = "update ruanjianshebei set mingcheng=?,banben=?,yingyongfanwei=?,yingyongjiaocheng=?,wuliweizhi=? where proid=? and objid=?";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getMingcheng());
            psStat.setObject(2, model.getBanben());
            psStat.setObject(3, model.getYingyongfanwei());
            psStat.setObject(4, model.getYingyongjiaocheng());
            psStat.setObject(5, model.getWuliweizhi());
            psStat.setObject(6, model.getProid());
            psStat.setObject(7, model.getObjid());
            
        } else {
            sql = "insert into ruanjianshebei  values(?,?,?,?,?,?,?,?)";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getObjid());
            psStat.setObject(4, model.getMingcheng());
            psStat.setObject(5, model.getBanben());
            psStat.setObject(6, model.getYingyongfanwei());
            psStat.setObject(7, model.getYingyongjiaocheng());
            psStat.setObject(8, model.getWuliweizhi());
        }
        
        return psStat.executeUpdate();
    }
    
    public static int exist(String proid, String objid) throws SQLException {
        String sql = "select count(*) from ruanjianshebei where proid='" + proid + "' and objid='" + objid + "'";
        int count = DBUtil.getCount(sql);
        return count;
    }
    
    public static Ruanjianshebei getModel(String proid, String objid) throws SQLException {
        String sql = "select * from ruanjianshebei where proid='" + proid + "' and objid='" + objid + "'";
        Ruanjianshebei model = null;
        com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DBUtil.getConnection();
        PreparedStatement psStat = con.prepareStatement(sql);
        ResultSet rs = psStat.executeQuery();
        if (rs.next()) {
            model = new Ruanjianshebei();
            model.setId(rs.getInt("id"));
            model.setProid(proid);
            model.setObjid(objid);
            model.setMingcheng(rs.getString("mingcheng"));
            model.setWuliweizhi(rs.getString("wuliweizhi"));
            model.setYingyongfanwei(rs.getString("yingyongfanwei"));
            model.setYingyongjiaocheng(rs.getString("yingyongjiaocheng"));
            model.setBanben(rs.getString("banben"));
        }
        return model;
    }
}
