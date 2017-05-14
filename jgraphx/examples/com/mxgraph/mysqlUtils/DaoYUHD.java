/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.YUHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DaoYUHD {

    public static int add(YUHD model) throws SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        int count = DBUtil.getCount("select count(*) from YUHD where proid='" + model.getProid() + "' and objid='" + model.getObjid() + "'");
        if (count > 0) {
            String sql = "update YUHD set huodongmingcheng=?,ywuhuodongbianhao=?,fuguocheng=?,huodongmiaoshu=? where proid=? and objid=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, model.getHuodongmingcheng());
            ps.setObject(2, model.getYwuhuodongbianhao());

            ps.setObject(3, model.getFuguocheng());
            ps.setObject(4, model.getHuodongmiaoshu());

            ps.setObject(5, model.getProid());
            ps.setObject(6, model.getObjid());
        } else {
            ps = con.prepareStatement("insert into YUHD values(null,'" + model.getProid() + "','" + model.getObjid() + "','" + model.getHuodongmingcheng() + "','" + model.getYwuhuodongbianhao() + "','" + model.getFuguocheng() + "','" + model.getHuodongmiaoshu() + "')");
        }
        return ps.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        YUHD model = new YUHD();
        model.setProid("12321");
        model.setObjid("123");
        model.setFuguocheng("234");
        model.setHuodongmiaoshu("234234");
        model.setHuodongmingcheng("名称");
        model.setYwuhuodongbianhao("阿娇卡迪夫");
        add(model);
    }

    public static int exist(String dir, String valueOf) throws SQLException {
        int count = DBUtil.getCount("select count(*) from YUHD where proid='" + dir + "' and objid='" + valueOf + "'");

        return count;
    }

    public static YUHD getModel(String dir, String valueOf) throws SQLException {
        Connection con = DBUtil.getConnection();
        String sql = "select * from YUHD where proid='" + dir + "' and objid='" + valueOf + "'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        YUHD model = new YUHD();
        if (rs.next()) {

            String proid = rs.getString("proid");

            String objid = rs.getString("objid");
            String huodongmingcheng = rs.getString("huodongmingcheng");
            String ywuhuodongbianhao = rs.getString("ywuhuodongbianhao");
            String fuguocheng = rs.getString("fuguocheng");
            String huodongmiaoshu = rs.getString("huodongmiaoshu");
            String id = rs.getString("id");

            model.setId(Integer.parseInt(id));
            model.setFuguocheng(fuguocheng);
            model.setHuodongmiaoshu(huodongmiaoshu);
            model.setHuodongmingcheng(huodongmingcheng);
            model.setObjid(objid);
            model.setProid(proid);

            model.setYwuhuodongbianhao(ywuhuodongbianhao);
        }
        return model;
    }
}
