/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DaoPerson {

    public static int add(Person model) throws SQLException {
        Connection con = DBUtil.getConnection();
        String sql = "";
        PreparedStatement psStat;
        int count = DBUtil.getCount("select count(*) from person where proid='" + model.getProid() + "' and objid='" + model.getObjid() + "' ");
        if (count > 0) {

            sql = "update person set mingcheng=?,bianhao=?,lingyu=?,zhishineirong=?,suoshuzaiti=? where proid=? and objid=?";
            psStat = con.prepareStatement(sql);
            psStat = con.prepareStatement(sql);

            psStat.setObject(1, model.getXingming());
            psStat.setObject(2, model.getGonghao());
            psStat.setObject(3, model.getLingyu());

            psStat.setObject(4, model.getZhishineirong());
            psStat.setObject(5, model.getSuoshuzaiti());
            psStat.setObject(6, model.getProid());
            psStat.setObject(7, model.getObjid());

        } else {
            sql = "insert into person values(?,?,?,?,?,?,?,?,?)";

            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getObjid());
            psStat.setObject(4, model.getXingming());
            psStat.setObject(5, model.getGonghao());
            psStat.setObject(6, model.getLingyu());

            psStat.setObject(7, model.getBumen());
            psStat.setObject(8, model.getZhishineirong());
            psStat.setObject(9, model.getSuoshuzaiti());
        }

        return psStat.executeUpdate();
    }

    //
    public static int exist(String proid, String objid) throws SQLException {

        String sql = "select count(*) from person where proid='" + proid + "' and objid='" + objid + "'";
        int count = DBUtil.getCount(sql);
        return count;
    }

    public static Person getModel(String dir, String valueOf) throws SQLException {
        String sql = "select * from person where proid=? and objid=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement psStat = con.prepareStatement(sql);
        psStat.setObject(1, dir);
        psStat.setObject(2, valueOf);
        ResultSet rs = psStat.executeQuery();
        Person model = null;
        if (rs.next()) {
            model = new Person();
            model.setXingming(rs.getString("xingming"));
            model.setGonghao(rs.getString("gonghao"));
            model.setLingyu(rs.getString("lingyu"));
            model.setBumen(rs.getString("bumen"));
            model.setZhishineirong(rs.getString("zhishineirong"));
            model.setSuoshuzaiti(rs.getString("suoshuzaiti"));
            model.setProid(dir);
            model.setObjid(valueOf);
            model.setId(rs.getInt("id"));
        }
        return model;
    }
}
