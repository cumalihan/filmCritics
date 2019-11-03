/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Yazar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;


public class YazarDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Yazar> getYazar(int sayfa, int sayfaBoyutu, String arananTerim) {
        List<Yazar> yazarList = new ArrayList();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from yazar";

            if (arananTerim != null) {
                query += " where ad like ? ";
            }
            query += " order by yazar_id asc limit ? offset ?";

            PreparedStatement st = this.getConnection().prepareStatement(query);
            
            if(arananTerim!=null){
                st.setString(1, "%"+arananTerim+"%");
                st.setInt(2, sayfaBoyutu);
                st.setInt(3, baslangic);
            }
            else{
                st.setInt(1, sayfaBoyutu);
                st.setInt(2, baslangic);
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Yazar tmp = new Yazar();
                tmp.setYazar_id(rs.getLong("yazar_id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                yazarList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return yazarList;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(yazar_id) as yazar_sayisi from yazar ";
            if (arananTerim != null) {
                query += "where ad like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
            }

            ResultSet rs = st.executeQuery();
            rs.next();
            sayi = rs.getInt("yazar_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void ekle(Yazar yazar) {

        try {
            PreparedStatement st = getConnection().prepareStatement("insert into yazar(ad,soyad) values(?,?)");
            st.setString(1, yazar.getAd());
            st.setString(2, yazar.getSoyad());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Yazar yazar) {
        try {
            PreparedStatement st = getConnection().prepareStatement("delete from yazar where yazar_id=?");
            st.setLong(1, yazar.getYazar_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Yazar yazar) {
        try {
            PreparedStatement st = getConnection().prepareStatement("update yazar set ad=?,soyad=? where yazar_id=?");
            st.setString(1, yazar.getAd());
            st.setString(2, yazar.getSoyad());
            st.setLong(3, yazar.getYazar_id());
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
