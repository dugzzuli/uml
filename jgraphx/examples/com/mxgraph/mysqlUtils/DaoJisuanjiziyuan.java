/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Jisuanjiziyuan;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DaoJisuanjiziyuan {

    public static int add(Jisuanjiziyuan model) throws SQLException {
        Connection con = (Connection) DBUtil.getConnection();
        PreparedStatement psStat = null;
        int count = DBUtil.getCount("select count(*) from jisuanjiziyuan where proid='" + model.getProid() + "' and objid='" + model.getObjid() + "'");
        if (count > 0) {
            String sql = "update jisuanjiziyuan  set mingcheng=?,bianhao=?,leixing=?,laiyuan=?,cunfangweizhi=? where proid=? and objid=?";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getMingcheng());
            psStat.setObject(2, model.getBianhao());
            psStat.setObject(3, model.getLeixing());
            psStat.setObject(4, model.getLaiyuan());
            psStat.setObject(5, model.getCunfangweizhi());
            psStat.setObject(6, model.getProid());
            psStat.setObject(7, model.getObjid());
        } else {
            String sql = "insert into jisuanjiziyuan  values(?,?,?,?,?,?,?,?)";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getObjid());
            psStat.setObject(4, model.getMingcheng());
            psStat.setObject(5, model.getBianhao());
            psStat.setObject(6, model.getLeixing());
            psStat.setObject(7, model.getLaiyuan());
            psStat.setObject(8, model.getCunfangweizhi());
        }
        return psStat.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        Jisuanjiziyuan model = new Jisuanjiziyuan();
        model.setProid("proid");
        model.setObjid("objid");
        model.setMingcheng("mingcheng");
        model.setBianhao("biaohao");
        add(model);
    }

    public static int exist(String proid, String objid) throws SQLException {
        String sql = "select count(*) from jisuanjiziyuan where proid='" + proid + "' and objid='" + objid + "'";
        int count = DBUtil.getCount(sql);
        return count;
    }

    public static Jisuanjiziyuan getModel(String proid, String objid) throws SQLException {
        String sql = "select * from jisuanjiziyuan where proid='" + proid + "' and objid='" + objid + "'";
        Jisuanjiziyuan model = null;
        Connection con = (Connection) DBUtil.getConnection();
        PreparedStatement psStat = con.prepareStatement(sql);
        ResultSet rs = psStat.executeQuery();
        if(rs.next())
        {
            model=new Jisuanjiziyuan();
            model.setBianhao(rs.getString("bianhao"));
            model.setCunfangweizhi(rs.getString("cunfangweizhi"));
            model.setId(rs.getInt("id"));
            model.setLaiyuan(rs.getString("laiyuan"));
            model.setLeixing(rs.getString("leixing"));
            model.setProid(proid);
            model.setObjid(objid);
            model.setMingcheng(rs.getString("mingcheng"));
        }
        return model;
    }
}
