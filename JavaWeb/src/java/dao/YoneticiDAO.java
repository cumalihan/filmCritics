/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Yonetici;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class YoneticiDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Yonetici> getYonetici() {
        List<Yonetici> yoneticiList = new ArrayList();
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from yonetici ");

            while (rs.next()) {
                Yonetici tmp = new Yonetici(rs.getLong("yonetici_id"), rs.getString("kullanici_adi"), rs.getString("sifre"));
                yoneticiList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yoneticiList;
    }

    public void ekle(Yonetici yonetici) {
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate("insert into yonetici (kullanici_adi,sifre) values ('" + yonetici.getKullanici_adi() + "','" + yonetici.getSifre() + "')");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Yonetici yonetici) {
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate("delete from yonetici where yonetici_id=" + yonetici.getYonetici_id());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Yonetici yonetici) {
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate("update yonetici set kullanici_adi='" + yonetici.getKullanici_adi() + "' where yonetici_id= " + yonetici.getYonetici_id());
            st.executeUpdate("update yonetici set sifre='" + yonetici.getSifre() + "' where yonetici_id= " + yonetici.getYonetici_id());
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

    public java.sql.Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
}
