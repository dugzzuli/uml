/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Ruanjianshebei;
import com.mxgraph.model.Yingjianshebei;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DaoYingjianshebei {
    
    public static int add(Yingjianshebei model) throws SQLException {
        Connection con = (Connection) DBUtil.getConnection();
        PreparedStatement psStat;
        String sql = "";
        int count = DBUtil.getCount("select  count(*) from yingjianshebei where proid='" + model.getProid() + "' and objid='" + model.getObjid() + "'");
        if (count > 0) {
            sql = "update yingjianshebei set mingcheng=?,xinghao=?,yingjianyaoqiu=?,yingyongjiaocheng=?,wuliweizhi=? where proid=? and objid=?";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getMingcheng());
            psStat.setObject(2, model.getXinghao());
            psStat.setObject(3, model.getYingjianyaoqiu());
            psStat.setObject(4, model.getYingyongjiaocheng());
            psStat.setObject(5, model.getWuliweizhi());
            psStat.setObject(6, model.getProid());
            psStat.setObject(7, model.getObjid());
        } else {
            sql = "insert into yingjianshebei values(?,?,?,?,?,?,?,?)";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getObjid());
            psStat.setObject(4, model.getMingcheng());
            psStat.setObject(5, model.getXinghao());
            psStat.setObject(6, model.getYingjianyaoqiu());
            psStat.setObject(7, model.getYingyongjiaocheng());
            psStat.setObject(8, model.getWuliweizhi());
            
        }
        
        return psStat.executeUpdate();
    }
    
    public static void main(String ars[]) {
        try {
            int count = DBUtil.getCount("select count(*) from yingjianshebei ");
            System.out.println(count);
        } catch (SQLException ex) {
            Logger.getLogger(DaoYingjianshebei.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static int exist(String proid, String objid) throws SQLException {
        String sql = "select count(*) from yingjianshebei where proid='" + proid + "' and objid='" + objid + "'";
        int count = DBUtil.getCount(sql);
        return count;
    }
    
    public static Yingjianshebei getModel(String proid, String objid) throws SQLException {
        String sql = "select * from yingjianshebei where proid='" + proid + "' and objid='" + objid + "'";
        Yingjianshebei model = null;
        com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DBUtil.getConnection();
        PreparedStatement psStat = con.prepareStatement(sql);
        ResultSet rs = psStat.executeQuery();
        if (rs.next()) {
            model = new Yingjianshebei();
            model.setId(rs.getInt("id"));
            model.setProid(proid);
            model.setObjid(objid);
            model.setMingcheng(rs.getString("mingcheng"));
            model.setWuliweizhi(rs.getString("wuliweizhi"));
            model.setXinghao(rs.getString("xinghao"));
            model.setYingjianyaoqiu(rs.getString("yingjianyaoqiu"));
            model.setYingyongjiaocheng(rs.getString("yingyongjiaocheng"));
            
        }
        return model;
    }
}
