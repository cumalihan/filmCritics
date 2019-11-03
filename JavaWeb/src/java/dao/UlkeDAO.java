/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ulke;
import entity.Ulke;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class UlkeDAO {

    private DBConnection connector;
    private Connection connection;

    public List<Ulke> getUlke(int sayfa, int sayfaBoyutu,String arananTerim) {
        List<Ulke> ulkeList = new ArrayList();

        int baslangic = (sayfa - 1) * sayfaBoyutu;

        try {
            String query = "select * from ulke";

            if (arananTerim != null) {
                query += " where ulke like ? ";
            }

            query += " order by ulke_id asc limit ? offset ?";

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
                Ulke tmp = new Ulke(rs.getLong("ulke_id"), rs.getString("ulke"));
                ulkeList.add(tmp);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ulkeList;
    }

    public int sayi(String arananTerim) {
        int sayi = 0;
        try {
            String query = "select count(ulke_id) as ulke_sayisi from ulke ";
            if (arananTerim != null) {
                query += "where ulke like ?";
            }
            PreparedStatement st = getConnection().prepareStatement(query);

            if (arananTerim != null) {
                st.setString(1, "%" + arananTerim + "%");
            }
            ResultSet rs = st.executeQuery();
            rs.next();
            sayi = rs.getInt("ulke_sayisi");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sayi;
    }

    public void ekle(Ulke ulke) {

        try {
            PreparedStatement st = getConnection().prepareStatement("insert into ulke(ulke) values(?)");
            st.setString(1, ulke.getUlke());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sil(Ulke ulke) {

        try {
            PreparedStatement st = getConnection().prepareStatement("delete from ulke where ulke_id=?");
            st.setLong(1, ulke.getUlke_id());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void guncelle(Ulke ulke) {
        try {
            PreparedStatement st = getConnection().prepareStatement("update ulke set ulke=? where ulke_id=?");
            st.setString(1, ulke.getUlke());
            st.setLong(2, ulke.getUlke_id());
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
