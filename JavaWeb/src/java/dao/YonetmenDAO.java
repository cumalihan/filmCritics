/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Yonetmen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class YonetmenDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Yonetmen> getYonetmen(int sayfa, int sayfaBoyutu,String arananTerim) {
        List<Yonetmen> yonetmenList = new ArrayList();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from yonetmen";

            if (arananTerim != null) {
                query += " where ad like ? ";
            }

            query += " order by yonetmen_id asc limit ? offset ?";

            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
                st.setInt(2, sayfaBoyutu);
                st.setInt(3, baslangic);
            } else {
                st.setInt(1, sayfaBoyutu);
                st.setInt(2, baslangic);
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Yonetmen tmp = new Yonetmen(rs.getLong("yonetmen_id"), rs.getString("ad"), rs.getString("soyad"));
                yonetmenList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yonetmenList;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(yonetmen_id) as yonetmen_sayisi from yonetmen ";
            if (arananTerim != null) {
                query += "where ad like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi = rs.getInt("yonetmen_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void ekle(Yonetmen yonetmen) {
        try {
            PreparedStatement st = getConnection().prepareStatement("insert into yonetmen (ad,soyad) values(?,?)");
            st.setString(1, yonetmen.getAd());
            st.setString(2, yonetmen.getSoyad());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Yonetmen yonetmen) {
        try {
            PreparedStatement st = getConnection().prepareStatement("delete from yonetmen where yonetmen_id=?");
            st.setLong(1, yonetmen.getYonetmen_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Yonetmen yonetmen) {
        try {
            PreparedStatement st = getConnection().prepareStatement("update yonetmen set ad=?,soyad=? where yonetmen_id=?");
            st.setString(1, yonetmen.getAd());
            st.setString(2, yonetmen.getSoyad());
            st.setLong(3, yonetmen.getYonetmen_id());
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

    public java.sql.Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
}
