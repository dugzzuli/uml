/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mxgraph.mysqlUtils;

import com.mxgraph.model.Yewu;
import com.view.YeWu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sun.security.x509.X500Name;

/**
 *
 * @author admin
 */
public class DaoYewu {
    
    public static int add(Yewu model) throws SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement psStat;
        int count = DBUtil.getCount("select count(*) from yewu where proid='" + model.getProid() + "'");
        if (count > 0) {
            delete(model.getProid());
            String sql = "insert into yewu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getTitle());
            psStat.setObject(4, model.getZhixingzhe());
            psStat.setObject(5, model.getZhizingzheone());
            psStat.setObject(6, model.getZhixingzhetwo());
            psStat.setObject(7, model.getMubiao());
            psStat.setObject(8, model.getMubiaoone());
            psStat.setObject(9, model.getMubiaotwo());
            psStat.setObject(10, model.getZuzhi());
            psStat.setObject(11, model.getZuzhione());
            psStat.setObject(12, model.getZuzhitwo());
            psStat.setObject(13, model.getShijian());
            psStat.setObject(14, model.getShijianone());
            psStat.setObject(15, model.getShijiantwo());
            psStat.setObject(16, model.getFive());
            psStat.setObject(17, model.getFiveone());
            psStat.setObject(18, model.getFivetwo());
            psStat.setObject(19, model.getZiyuan());
            psStat.setObject(20, model.getZiyuanone());
            psStat.setObject(21, model.getZiyuantwo());
            psStat.setObject(22, model.getWeizhi());
            psStat.setObject(23, model.getWeizhione());
            psStat.setObject(24, model.getWeizhitwo());
            psStat.setObject(25, model.getQiyefenwei());
            psStat.setObject(26, model.getQiyefenweione());
            psStat.setObject(27, model.getQiyefenweitwo());
            psStat.setObject(28, model.getYuanyin());
            psStat.setObject(29, model.getYuanyinone());
            psStat.setObject(30, model.getYuanyintwo());
        } else {
            String sql = "insert into yewu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            psStat = con.prepareStatement(sql);
            psStat.setObject(1, model.getId());
            psStat.setObject(2, model.getProid());
            psStat.setObject(3, model.getTitle());
            psStat.setObject(4, model.getZhixingzhe());
            psStat.setObject(5, model.getZhizingzheone());
            psStat.setObject(6, model.getZhixingzhetwo());
            psStat.setObject(7, model.getMubiao());
            psStat.setObject(8, model.getMubiaoone());
            psStat.setObject(9, model.getMubiaotwo());
            psStat.setObject(10, model.getZuzhi());
            psStat.setObject(11, model.getZuzhione());
            psStat.setObject(12, model.getZuzhitwo());
            psStat.setObject(13, model.getShijian());
            psStat.setObject(14, model.getShijianone());
            psStat.setObject(15, model.getShijiantwo());
            psStat.setObject(16, model.getFive());
            psStat.setObject(17, model.getFiveone());
            psStat.setObject(18, model.getFivetwo());
            psStat.setObject(19, model.getZiyuan());
            psStat.setObject(20, model.getZiyuanone());
            psStat.setObject(21, model.getZiyuantwo());
            psStat.setObject(22, model.getWeizhi());
            psStat.setObject(23, model.getWeizhione());
            psStat.setObject(24, model.getWeizhitwo());
            psStat.setObject(25, model.getQiyefenwei());
            psStat.setObject(26, model.getQiyefenweione());
            psStat.setObject(27, model.getQiyefenweitwo());
            psStat.setObject(28, model.getYuanyin());
            psStat.setObject(29, model.getYuanyinone());
            psStat.setObject(30, model.getYuanyintwo());
        }
        return psStat.executeUpdate();
    }
    
    public static int delete(String proid) throws SQLException {
        Connection con = DBUtil.getConnection();
        String sql = "delete from yewu where proid='" + proid + "'";
        PreparedStatement psStat = con.prepareStatement(sql);
        
        return psStat.executeUpdate();
    }
    
    public static Yewu getModel(String proid) throws SQLException {
        Yewu model = null;
        String sql = "select * from yewu where proid=?";
        Connection con = DBUtil.getConnection();
        PreparedStatement psStat = con.prepareStatement(sql);
        psStat.setObject(1, proid);
        ResultSet rs = psStat.executeQuery();
        if (rs.next()) {
            model = new Yewu();
            model.setId(rs.getInt("id"));
            model.setProid(proid);
            model.setTitle(rs.getString("title"));
            model.setZhizingzheone(rs.getString("zhizingzheone"));
            model.setZhixingzhetwo(rs.getString("zhixingzhetwo"));
            model.setMubiaoone(rs.getString("mubiaoone"));
            model.setMubiaotwo(rs.getString("mubiaotwo"));
            model.setZuzhione(rs.getString("zuzhione"));
            model.setZuzhitwo(rs.getString("zuzhitwo"));
            model.setShijianone(rs.getString("shijianone"));
            model.setShijiantwo(rs.getString("shijiantwo"));
            model.setFiveone(rs.getString("fiveone"));
            model.setFivetwo(rs.getString("fivetwo"));
            model.setZiyuanone(rs.getString("ziyuanone"));
            model.setZiyuantwo(rs.getString("ziyuantwo"));
            model.setWeizhione(rs.getString("qiyefenweione"));
            model.setWeizhitwo(rs.getString("qiyefenweitwo"));
            model.setQiyefenweione(rs.getString("yuanyinone"));
            model.setQiyefenweitwo(rs.getString("qiyefenweitwo"));
            model.setYuanyinone(rs.getString("yuanyinone"));
            model.setYuanyintwo(rs.getString("yuanyintwo"));
        }
        return model;
        
    }
}
