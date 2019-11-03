/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Yetki;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class YetkiDAO {
    
    private DBConnection connector;
    private Connection connection;

    public Yetki bul(Long id) {
        Yetki yt = null;
        try {

            PreparedStatement st = this.getConnection().prepareStatement("select * from yetki where yetki_id=?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();

            yt = new Yetki();
            yt.setYetki_id(rs.getLong("yetki_id"));
            yt.setGrup(rs.getString("grup"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yt;
    }

    public List<Yetki> getYetki() {
        List<Yetki> yetkiList = new ArrayList();

        try {
            PreparedStatement st = this.getConnection().prepareStatement("select * from yetki");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Yetki tmp = new Yetki();
                tmp.setYetki_id(rs.getLong("yetki_id"));
                tmp.setGrup(rs.getString("grup"));
                yetkiList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yetkiList;
    }

    public void ekle(Yetki yetki) {
        try {

            PreparedStatement st = this.getConnection().prepareStatement("insert into yetki(grup) values(?)");
            st.setString(1, yetki.getGrup());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Yetki yetki) {
        try {

            PreparedStatement st = this.getConnection().prepareStatement("delete from yetki where yetki_id=?");
            st.setLong(1, yetki.getYetki_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Yetki yetki) {
        try {

            PreparedStatement st = this.getConnection().prepareStatement("update yetki set grup=? where yetki_id=?");
            st.setString(1, yetki.getGrup());
            st.setLong(2, yetki.getYetki_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }
        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
    
}
