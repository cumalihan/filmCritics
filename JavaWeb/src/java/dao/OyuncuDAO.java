/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import entity.Oyuncu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class OyuncuDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Oyuncu> getOyuncu(int sayfa, int sayfaBoyutu,String arananTerim) {
        List<Oyuncu> oyuncuList = new ArrayList();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from oyuncu";

            if (arananTerim != null) {
                query += " where ad like ? ";
            }

            query += " order by oyuncu_id asc limit ? offset ?";

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
                Oyuncu tmp = new Oyuncu(rs.getLong("oyuncu_id"), rs.getString("ad"), rs.getString("soyad"), rs.getString("cinsiyet"));
                oyuncuList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return oyuncuList;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(oyuncu_id) as oyuncu_sayisi from oyuncu ";
            if (arananTerim != null) {
                query += "where ad like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi = rs.getInt("oyuncu_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void ekle(Oyuncu oyuncu) {

        try {
            PreparedStatement pst = getConnection().prepareStatement("inster into oyuncu(ad,soyad,cinsiyet) values (?,?,?)");
            pst.setString(1, oyuncu.getAd());
            pst.setString(2, oyuncu.getSoyad());
            pst.setString(3, oyuncu.getCinsiyet());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Oyuncu oyuncu) {
        try {
            PreparedStatement pst = getConnection().prepareStatement("delete from oyuncu where oyuncu_id=?");
            pst.setLong(1, oyuncu.getOyuncu_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Oyuncu oyuncu) {

        try {
            PreparedStatement st = getConnection().prepareStatement("update oyuncu set ad=?,soyad=?,cinsiyet=? where oyuncu_id=?");
            st.setString(1, oyuncu.getAd());
            st.setString(2, oyuncu.getSoyad());
            st.setString(3, oyuncu.getCinsiyet());
            st.setLong(4, oyuncu.getOyuncu_id());
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
