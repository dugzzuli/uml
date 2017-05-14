/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Tuwenziliao;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DaoTuwenziliao {

    public static int add(Tuwenziliao model) throws SQLException {
        Connection con = (Connection) DBUtil.getConnection();
        int count = DBUtil.getCount("select count(*) from tuwenziliao where proid='" + model.getProid() + "' and objid='" + model.getObjid() + "'");
        PreparedStatement psStat = null;
        String sql = "";
        if (count > 0) {
            sql = "update tuwenziliao set mingcheng=?,zuozhe=?,banbenhao=?,leixing=?,keliyonglv=?,wuliweizhi=? where proid=? and objid=?";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getMingcheng());
            psStat.setObject(2, model.getZuozhe());
            psStat.setObject(3, model.getBanbenhao());
            psStat.setObject(4, model.getLeixing());
            psStat.setObject(5, model.getKeliyonglv());
            psStat.setObject(6, model.getWuliweizhi());
            psStat.setObject(7, model.getProid());
            psStat.setObject(8, model.getObjid());
        } else {
            sql = "insert into tuwenziliao values(?,?,?,?,?,?,?,?,?)";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getProid());
            psStat.setObject(4, model.getMingcheng());
            psStat.setObject(5, model.getZuozhe());
            psStat.setObject(6, model.getBanbenhao());
            psStat.setObject(7, model.getLeixing());
            psStat.setObject(8, model.getKeliyonglv());
            psStat.setObject(9, model.getWuliweizhi());
        }
        return psStat.executeUpdate();
    }

    public static int exist(String proid, String objid) throws SQLException {
        String sql = "select count(*) from tuwenziliao where proid='" + proid + "' and objid='" + objid + "'";
        int count = DBUtil.getCount(sql);
        return count;
    }

    public static Tuwenziliao getModel(String proid, String objid) throws SQLException {
        String sql = "select count(*) from tuwenziliao where proid='" + proid + "' and objid='" + objid + "'";
        Connection con = (Connection) DBUtil.getConnection();
        Tuwenziliao model = null;
        PreparedStatement psStat = con.prepareStatement(sql);
        ResultSet rs = psStat.executeQuery();
        if (rs.next()) {
            model = new Tuwenziliao();
            model.setBanbenhao(rs.getString("banbenhao"));
            model.setKeliyonglv(rs.getString("keliyonglv"));
            model.setLeixing(rs.getString("leixing"));
            model.setMingcheng(rs.getString("mingcheng"));
            model.setWuliweizhi(rs.getString("wuliweizhi"));
            model.setZuozhe(rs.getString("zuozhe"));
            model.setProid(proid);
            model.setObjid(objid);
            model.setId(rs.getInt("id"));
        }
        return model;
    }
}
