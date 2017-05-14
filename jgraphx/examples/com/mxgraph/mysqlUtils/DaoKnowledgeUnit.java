/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Jisuanjiziyuan;
import com.mxgraph.model.KnowledgeUnit;
import com.mxgraph.model.Yingjianshebei;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DaoKnowledgeUnit {

    public static int add(KnowledgeUnit model) throws SQLException {
        Connection con = (Connection) DBUtil.getConnection();
        int count = DBUtil.getCount("select  count(*) from knowledgeunit where proid='" + model.getProid() + "' and '" + model.getObjid() + "'");
        PreparedStatement psStat;
        if (count > 0) {
            String sql = "update knowledgeunit  set mingcheng=?,bianhao=?,lingyu=?,zhishineirong=?,suoshuzaiti=? where proid=? and objid=?";
            psStat = con.prepareStatement(sql);

            psStat.setObject(1, model.getMingcheng());
            psStat.setObject(2, model.getBianhao());
            psStat.setObject(3, model.getLingyu());
            psStat.setObject(4, model.getZhishineirong());
            psStat.setObject(5, model.getSuoshuzaiti());
            psStat.setObject(6, model.getProid());
            psStat.setObject(7, model.getObjid());
        } else {
            String sql = "insert into knowledgeunit  values(?,?,?,?,?,?,?,?)";
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getObjid());
            psStat.setObject(4, model.getMingcheng());
            psStat.setObject(5, model.getBianhao());
            psStat.setObject(6, model.getLingyu());
            psStat.setObject(7, model.getZhishineirong());
            psStat.setObject(8, model.getSuoshuzaiti());
        }
        return psStat.executeUpdate();
    }

    public static int exist(String proid, String objid) throws SQLException {
        String sql = "select count(*) from knowledgeunit where proid='" + proid + "' and objid='" + objid + "'";
        int count = DBUtil.getCount(sql);
        return count;
    }

    public static KnowledgeUnit getModel(String proid, String objid) throws SQLException {
        String sql = "select * from knowledgeunit where proid='" + proid + "' and objid='" + objid + "'";
        KnowledgeUnit model = null;
        Connection con = (Connection) DBUtil.getConnection();
        PreparedStatement psStat = con.prepareStatement(sql);
        ResultSet rs = psStat.executeQuery();
        if (rs.next()) {
            model = new KnowledgeUnit();

            model.setId(rs.getInt("id"));
            model.setLingyu(rs.getString("lingyu"));
            model.setSuoshuzaiti(rs.getString("suoshuzaiti"));
            model.setZhishineirong(rs.getString("zhishineirong"));
            model.setProid(proid);
            model.setObjid(objid);
            model.setBianhao(rs.getString("bianhao"));
            model.setMingcheng(rs.getString("mingcheng"));
        }
        return model;
    }

}
